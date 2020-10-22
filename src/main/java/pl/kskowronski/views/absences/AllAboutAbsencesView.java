package pl.kskowronski.views.absences;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
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

@Route(value = "hello", layout = MainView.class)
@PageTitle("Hello World")
@CssImport("./styles/views/helloworld/hello-world-view.css")
public class AllAboutAbsencesView extends HorizontalLayout {

    NumberField yearField = new NumberField();
    MapperDate dataMapper = new MapperDate();

    public AllAboutAbsencesView(@Autowired AbsenceService absenceService) throws Exception {
        setId("all-about-absences-view");
        yearField.setLabel("Rok");
        yearField.setHasControls(true);
        yearField.setValue(Double.parseDouble(dataMapper.getCurrentlyYear()));
        add(yearField);
        setVerticalComponentAlignment(Alignment.END, yearField);

        VaadinSession session = VaadinSession.getCurrent();
        User worker = session.getAttribute(User.class);

        List<AbsenceDTO> listAbsences = absenceService.findAllByAbPrcIdForYear(worker.getPrcId(), String.valueOf(yearField.getValue().intValue()));
        System.out.println(listAbsences.size());


//        sayHello.addClickListener( e-> {
//            Notification.show("Hello " + name.getValue());
//        });

        //TODO add eligible days of holiday
    }



}
