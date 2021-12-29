package pl.kskowronski.views.pit11list;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ResourceUtils;
import pl.kskowronski.data.MapperDate;
import pl.kskowronski.data.entity.egeria.css.SK;
import pl.kskowronski.data.entity.egeria.eDek.EdktDeklaracjeDTO;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.entity.log.LogEvent;
import pl.kskowronski.data.entity.log.LogPit11;
import pl.kskowronski.data.reports.Pit11Service;
import pl.kskowronski.data.service.admin.PdfService;
import pl.kskowronski.data.service.egeria.css.SKService;
import pl.kskowronski.data.service.egeria.edek.EdktDeklaracjeService;
import pl.kskowronski.data.service.egeria.ek.UserService;
import pl.kskowronski.data.service.log.LogPit11Service;
import pl.kskowronski.views.main.MainView;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Route(value = "Pit11list", layout = MainView.class)
@PageTitle("Pit11 lista")
@CssImport("./styles/views/pit11/pit11-view.css")
public class Pit11listView extends VerticalLayout {

    MapperDate mapperDate = new MapperDate();
    private UserService userService;
    private EdktDeklaracjeService edktDeklaracjeService;
    private SKService skService;
    SimpleDateFormat dtYYYY = new SimpleDateFormat("yyyy");
    private Pit11Service pit11Service;
    private PdfService pdfService;

    private Grid<EdktDeklaracjeDTO> grid;
    private Optional<User> worker;
    private NumberField yearField = new NumberField();
    private Select<SK> selectSK = new Select<>();

    private HorizontalLayout hTop = new HorizontalLayout();
    private LogPit11Service logPit11Service;


    @Autowired
    public Pit11listView(UserService userService, EdktDeklaracjeService edktDeklaracjeService, SKService skService
            , Pit11Service pit11Service, PdfService pdfService, LogPit11Service logPit11Service) throws ParseException {
        this.userService = userService;
        this.edktDeklaracjeService = edktDeklaracjeService;
        this.skService = skService;
        this.pit11Service = pit11Service;
        this.pdfService = pdfService;
        this.logPit11Service = logPit11Service;

        setHeight("85%");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        worker = userService.findByUsername(userDetails.getUsername());
        hTop.setClassName("hTop");
        addYearField();
        addSelectSK();

        add(hTop);
        Button buttonRun = new Button("Pobierz");
        buttonRun.addClickListener(clickEvent -> {
            onUserChangedYear(String.valueOf(yearField.getValue().intValue()));
        });
        hTop.add(buttonRun);

        this.grid = new Grid<>(EdktDeklaracjeDTO.class);
        grid.setColumns("dklTdlKod", "dklFrmNazwa", "dklPrcNazwisko", "dklPrcImie", "dklYear"); //, "dklXmlVisual"
        grid.getColumnByKey("dklTdlKod").setWidth("100px").setHeader("Dek");
        grid.getColumnByKey("dklYear").setWidth("100px").setHeader("Rok");
        //grid.getColumnByKey("dklXmlVisual").setWidth("100px").setHeader("Xml");
        grid.getColumnByKey("dklFrmNazwa").setWidth("300px").setHeader("Firma");
        grid.setHeightFull();
        // run generate pit pdf
        grid.addColumn(new NativeButtonRenderer<EdktDeklaracjeDTO>("Pit11",
                item -> {
                    try {
                        String path = pit11Service.exportPit11Report("pdf", worker.get().getPassword(), dtYYYY.format(item.getDklDataOd()),  item.getDklXmlVisual());
                        displayPitPDFonBrowser(path, "Company:" + item.getDklFrmNazwa());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (JRException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ));

        add(grid);

    }


    private void onUserChangedYear(String year)  {
        try {
            Optional<List<EdktDeklaracjeDTO>> listEDeklaracje =  edktDeklaracjeService.getListPit11ForSupervisor(year, selectSK.getValue().getSkId());
            grid.setItems(listEDeklaracje.get());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void addYearField() {
        yearField.setLabel("Rok");
        yearField.getElement().setProperty("title", "Test");
        yearField.setHasControls(true);
        yearField.setValue(Double.parseDouble(Long.parseLong(mapperDate.getCurrentlyYear())-1L + ""));
        yearField.addValueChangeListener( e-> {
            try {
                onUserChangedYear( String.valueOf(e.getValue().intValue()));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        hTop.add(yearField);
    }

    private void addSelectSK() {
        List<SK> listSK = skService.findAll();
        selectSK.setItems(listSK);
        selectSK.setItemLabelGenerator(SK::getSkKod);
        selectSK.setEmptySelectionCaption(listSK.get(0).getSkKod());
        selectSK.setLabel("Obiekt");
        selectSK.setValue(listSK.get(0));
        hTop.add(selectSK);
    }



    private void displayPitPDFonBrowser(String path, String description) throws FileNotFoundException, IOException {

        File filePdf = ResourceUtils.getFile(path);

        byte[] pdfBytes = FileUtils.readFileToByteArray(filePdf);

        Dialog dialog = new Dialog();
        dialog.setWidth("300px");
        dialog.setHeight("150px");

        StreamResource res = new StreamResource("file.pdf", () -> new ByteArrayInputStream(pdfBytes));
        String reportName = "reportPit11";
        Anchor a = new Anchor(res, "kliknij tu by pobrać pit11");
        a.setId(reportName);
        a.getElement().getStyle().set("display", "none");
        a.setTarget( "_blank" );
        a.getElement().addEventListener("click", event -> {
            new Thread(() -> { // asynchronous
                try {
                    pdfService.removeFileFromDisk(path);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            dialog.close();
        });

        Page page = UI.getCurrent().getPage();
        page.executeJavaScript("document.getElementById('"+reportName+"').click();");

        dialog.add(a, new Html("<div><br><div>"), new Button("Zamknij", e -> dialog.close()));
        add(dialog);
        dialog.open();

        saveLog(description);
    }

    private void saveLog(String description){
        LogPit11 logPit11 = new LogPit11();
        logPit11.setPrcId(worker.get().getPrcId());
        logPit11.setEvent(LogEvent.DOWNLOAD_THE_DECLARATION_PIT11.toString());
        logPit11.setYear(BigDecimal.valueOf( Long.parseLong(yearField.getValue().toString().substring(0,4))) );
        logPit11.setAuditDc(new Date());
        logPit11.setDescription(description);
        logPit11Service.save(logPit11);
    }

}
