package pl.kskowronski.views.pit11;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kskowronski.data.entity.egeria.ek.PracownikVo;
import pl.kskowronski.data.service.PersonService;
import pl.kskowronski.data.service.egeria.ek.PracownikVoService;
import pl.kskowronski.views.main.MainView;

import java.math.BigDecimal;
import java.util.Optional;

@Route(value = "Pit11", layout = MainView.class)
@PageTitle("Pit11")
public class Pit11View extends HorizontalLayout {


    private TextField name;
    private Button sayHello;

    private PracownikVoService pracownikVoService;

    public Pit11View(@Autowired PracownikVoService pracownikVoService) {

        this.pracownikVoService = pracownikVoService;

        Optional<PracownikVo> worker = pracownikVoService.findById(BigDecimal.valueOf(103208L));

        setId("pit11-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener( e-> {
            Notification.show("Hello " + name.getValue() + worker.get().getPrcPesel());
        });


    }
}




