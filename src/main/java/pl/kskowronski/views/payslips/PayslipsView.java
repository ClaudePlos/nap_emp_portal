package pl.kskowronski.views.payslips;


import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kskowronski.data.MapperDate;
import pl.kskowronski.data.entity.egeria.eDek.EdktDeklaracjeDTO;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.entity.egeria.ek.Zatrudnienie;
import pl.kskowronski.data.reaports.PayslipisService;
import pl.kskowronski.data.service.egeria.ek.ZatrudnienieService;
import pl.kskowronski.views.main.MainView;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Route(value = "payslips", layout = MainView.class)
@PageTitle("Paski")
@CssImport("./styles/views/payslips/payslips-view.css")
public class PayslipsView extends VerticalLayout {

    private ZatrudnienieService zatrudnienieService;
    private PayslipisService payslipisServicel;
    private MapperDate mapperDate = new MapperDate();

    private Grid<Zatrudnienie> gridContracts;
    private TextField textPeriod = new TextField("Okres");

    private User worker;



    public PayslipsView(@Autowired ZatrudnienieService zatrudnienieService, @Autowired PayslipisService payslipisService) throws ParseException {
        setId("payslips-view");
        this.zatrudnienieService = zatrudnienieService;
        this.payslipisServicel = payslipisService;
        VaadinSession session = VaadinSession.getCurrent();
        worker = session.getAttribute(User.class);

        this.gridContracts = new Grid<>(Zatrudnienie.class);
        gridContracts.setColumns("zatDataPrzyj", "zatDataZmiany", "zatDataDo", "frmId");

        gridContracts.addColumn(new NativeButtonRenderer<Zatrudnienie>("Pasek",
                item -> {
                    try {
                        String path = payslipisService.przygotujPaski(null,item.getZatPrcId(), textPeriod.getValue(), item.getFrmId(), Long.parseLong("0")); //0 - full time job, 2 - contract
                        System.out.printf(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));

        Date now = new Date();
        textPeriod.setValue(mapperDate.dtYYYYMM.format(now));
        add(textPeriod);

        Optional<List<Zatrudnienie>> contracts = zatrudnienieService.getActualContractForWorker(worker.getPrcId(), textPeriod.getValue());
        if (!contracts.isPresent()){
            Notification.show("Brak umów w danym okresie", 3000, Notification.Position.MIDDLE);
        }
        gridContracts.setItems(contracts.get());
        add(gridContracts);

    }


}
