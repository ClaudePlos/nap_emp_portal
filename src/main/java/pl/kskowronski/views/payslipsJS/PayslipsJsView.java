package pl.kskowronski.views.payslipsJS;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kskowronski.data.MapperDate;
import pl.kskowronski.data.entity.egeria.ckk.Client;
import pl.kskowronski.data.entity.egeria.ek.ListaPlacDTO;
import pl.kskowronski.data.entity.egeria.ek.ListaPlacWartKolumnyDTO;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.entity.egeria.ek.Zatrudnienie;
import pl.kskowronski.data.entity.egeria.global.EatFirma;
import pl.kskowronski.data.reports.PayslipisService;
import pl.kskowronski.data.service.egeria.ckk.ClientService;
import pl.kskowronski.data.service.egeria.ek.ZatrudnienieService;
import pl.kskowronski.data.service.egeria.global.EatFirmaService;
import pl.kskowronski.views.main.MainView;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;


@Route(value = "payslipsJS", layout = MainView.class)
@PageTitle("payslipsJS")
@CssImport("./styles/views/about/about-view.css")
@JavaScript("./js/payslips.js")
public class PayslipsJsView extends VerticalLayout {

    private ZatrudnienieService zatrudnienieService;
    private ClientService clientService;
    private EatFirmaService eatFirmaService;
    private PayslipisService payslipisService;
    private MapperDate mapperDate = new MapperDate();

    private Grid<Zatrudnienie> gridContracts;

    private Button butPlus = new Button("+");
    private Button butMinus = new Button("-");
    private TextField textPeriod = new TextField("Okres");

    private User worker;




    public PayslipsJsView(@Autowired ZatrudnienieService zatrudnienieService, @Autowired PayslipisService payslipisService, @Autowired ClientService clientService,
                          @Autowired EatFirmaService eatFirmaService) throws ParseException {
        setId("payslips-view");
        setHeight("80%");
        this.zatrudnienieService = zatrudnienieService;
        this.clientService = clientService;
        this.eatFirmaService = eatFirmaService;
        this.payslipisService = payslipisService;
        VaadinSession session = VaadinSession.getCurrent();
        worker = session.getAttribute(User.class);

        this.gridContracts = new Grid<>(Zatrudnienie.class);
        gridContracts.setColumns("frmNazwa","zatDataPrzyj", "zatDataZmiany", "zatDataDo");
        gridContracts.setHeightFull();

        gridContracts.addColumn(new NativeButtonRenderer<Zatrudnienie>("Pasek",
                item -> {
                    Date periodNow  = null;
                    Date periodParam = null;
                    try {
                        periodNow = Date.from(LocalDate.now().minus(1, ChronoUnit.MONTHS).atStartOfDay(ZoneId.systemDefault()).toInstant());
                        periodParam = mapperDate.dtYYYYMM.parse(textPeriod.getValue());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if ( periodParam.before(periodNow) ) {

                        if (mapperDate.dtYYYYMM.format(periodNow).equals(textPeriod.getValue()) && Long.parseLong(mapperDate.dtDD.format(periodNow)) < 10L){
                            Notification.show("Pasek jeszcze niedostępny. Pasek za ostatni miesiąc będzie dostępny po 10 danego miesiąca.", 5000, Notification.Position.MIDDLE);
                            return;
                        }

                        try {
                            GeneratePayslipiPDF(item.getZatPrcId(), item.getFrmId(), item.getFrmNazwa(), periodParam);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Notification.show("Pasek jeszcze niedostępny. Pasek za ostatni miesiąc będzie dostępny po 10 danego miesiąca.", 5000, Notification.Position.MIDDLE);
                    }

                }));




        Date now =  Date.from(LocalDate.now().minus(1, ChronoUnit.MONTHS).atStartOfDay(ZoneId.systemDefault()).toInstant());
        textPeriod.setValue(mapperDate.dtYYYYMM.format(now));
        textPeriod.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        butPlus.setWidth("5px");
        butPlus.addClickListener(event ->{
            Long mc = Long.parseLong(textPeriod.getValue().substring(textPeriod.getValue().length()-2));
            if ( mc < 12 ){
                mc++; String mcS = "0" + mc;
                textPeriod.setValue(textPeriod.getValue().substring(0,5) + mcS.substring(mcS.length()-2) );
            } else {
                Long year = Long.parseLong(textPeriod.getValue().substring(0,4));
                year++;
                textPeriod.setValue(year + "-01" );
            }
            try {
                getCotractForPeriod();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        butMinus.addClickListener(event ->{
            Long mc = Long.parseLong(textPeriod.getValue().substring(textPeriod.getValue().length()-2));
            if ( mc > 1 ){
                mc--; String mcS = "0" + mc;
                textPeriod.setValue(textPeriod.getValue().substring(0,5) + mcS.substring(mcS.length()-2) );
            } else {
                Long year = Long.parseLong(textPeriod.getValue().substring(0,4));
                year--;
                textPeriod.setValue(year + "-12" );
            }
            try {
                getCotractForPeriod();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        add(butMinus, textPeriod, butPlus);


        add(gridContracts);
        getCotractForPeriod();

    }

    private void getCotractForPeriod() throws ParseException {
        Optional<List<Zatrudnienie>> contracts = zatrudnienieService.getActualContractForWorker(worker.getPrcId(), textPeriod.getValue());
        if (!contracts.isPresent()){
            Notification.show("Brak umów w danym okresie", 3000, Notification.Position.MIDDLE);
        }
        gridContracts.setItems(contracts.get());
    }

    private void GeneratePayslipiPDF(BigDecimal prcId, BigDecimal frmId, String frmNazwa, Date zaOkres) throws IOException {
        Gson gson = new Gson();
        //1.getListPlac
        Optional<EatFirma> eatFirma = eatFirmaService.findById(frmId);

        Optional<Client> client = clientService.getClientByKlKod(eatFirma.get().getFrmKlId());

        String frmNip = client.get().getKldNip();

        ListaPlacDTO lNaglowek = zatrudnienieService.getlPlacNaglowek(prcId, zaOkres, frmId);
        //2.getListPlacWartKol

        String[] tab_kol = new String[] {"KOL_1","KOL_2","KOL_3","KOL_4","KOL_5","KOL_6","KOL_7","KOL_B","KOL_N","KOL_P"};
        String kol = "";

        List<ListaPlacWartKolumnyDTO> lPlacKol = new ArrayList<>();
        List<ListaPlacWartKolumnyDTO> All = new ArrayList<>();
        for(int i=0;i<tab_kol.length;i++){
            kol = tab_kol[i];
            List<ListaPlacWartKolumnyDTO> lColumn = zatrudnienieService.getLPlacWartKol(prcId, zaOkres, kol, frmId);
            lPlacKol.addAll(lColumn);

            if ( kol.equals("KOL7")){
                List<ListaPlacWartKolumnyDTO> lColumn8 = zatrudnienieService.getListPlacWartKol8(prcId, zaOkres, frmId);
                lPlacKol.addAll(lColumn8);
                List<ListaPlacWartKolumnyDTO> lColumn8a = zatrudnienieService.getListPlacWartKol8PrzelKasa(prcId, zaOkres, frmId);
                lPlacKol.addAll(lColumn8a);
            }
        }

        String imieNazwisko = worker.getPrcImie() + " " + worker.getPrcNazwisko();
        String prcNumber = worker.getPrcNumer().toString();


        //Wywolanie js
        String initFunction = "generatePaySlipPDF($0, $1, $2, $3, $4, $5, $6, $7);";
        UI.getCurrent().getPage().executeJs(initFunction, this,
                gson.toJson(lNaglowek), gson.toJson(lPlacKol), mapperDate.dtYYYYMM.format(zaOkres),
                imieNazwisko, prcNumber, frmNip, frmNazwa);


    }





}
