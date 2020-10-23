package pl.kskowronski.views.absences;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kskowronski.data.MapperDate;
import pl.kskowronski.data.entity.egeria.ek.AbsenceDTO;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.reaports.Pit11Service;
import pl.kskowronski.data.service.egeria.ek.AbsenceService;
import pl.kskowronski.views.main.MainView;

import java.util.List;
import java.util.Optional;

@Route(value = "absences", layout = MainView.class)
@PageTitle("Twój urlop")
@CssImport("./styles/views/helloworld/hello-world-view.css")
public class AllAboutAbsencesView extends VerticalLayout {

    private Grid<AbsenceDTO> grid;
    NumberField yearField = new NumberField();
    MapperDate dataMapper = new MapperDate();
    private User worker;
    Notification notification = new Notification();

    AbsenceService absenceService;

    public AllAboutAbsencesView(@Autowired AbsenceService absenceService) throws Exception {
        this.absenceService = absenceService;
        setId("all-about-absences-view");
        yearField.setLabel("Rok");
        yearField.setHasControls(true);
        yearField.setValue(Double.parseDouble(dataMapper.getCurrentlyYear()));
        yearField.addValueChangeListener( e-> {
            try {
                onAbsenceChangeYear( String.valueOf(e.getValue().intValue()));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        add(yearField);
        setHorizontalComponentAlignment(Alignment.START, yearField);
        //setVerticalComponentAlignment(Alignment.END, yearField);

        VaadinSession session = VaadinSession.getCurrent();
        worker = session.getAttribute(User.class);

        this.grid = new Grid<>(AbsenceDTO.class);
        grid.setColumns("abTypeOfAbsence", "abDataOd", "abDataDo", "abDniWykorzystane", "abGodzinyWykorzystane", "abFrmName");
        grid.getColumnByKey("abTypeOfAbsence").setWidth("200px").setHeader("Rodzaj");
        grid.getColumnByKey("abDataOd").setWidth("130px").setHeader("Data Od");
        grid.getColumnByKey("abDataDo").setWidth("130px").setHeader("Data Do");
        grid.getColumnByKey("abDniWykorzystane").setWidth("80px").setHeader("Dni");
        grid.getColumnByKey("abGodzinyWykorzystane").setWidth("80px").setHeader("Godz.");
        grid.getColumnByKey("abFrmName").setWidth("250px").setHeader("Firma");
        onAbsenceChangeYear(String.valueOf(yearField.getValue().intValue()));
        add(grid);
//        sayHello.addClickListener( e-> {
//            Notification.show("Hello " + name.getValue());
//        });

        //TODO add eligible days of holiday
    }

    private void onAbsenceChangeYear(String year) throws Exception {
        Optional<List<AbsenceDTO>> listAbsences = absenceService.findAllByAbPrcIdForYear(worker.getPrcId(), year);
        if (listAbsences.get().size() == 0){
            Notification.show("Brak absencji w roku: " + year, 3000, Notification.Position.TOP_CENTER);
        }
        grid.setItems(listAbsences.get());
        grid.getDataProvider().refreshAll();
    }



}
