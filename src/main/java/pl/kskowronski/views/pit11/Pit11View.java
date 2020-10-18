package pl.kskowronski.views.pit11;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.service.egeria.ek.UserService;
import pl.kskowronski.views.main.MainView;

import java.math.BigDecimal;
import java.util.Optional;

@Route(value = "Pit11", layout = MainView.class)
@PageTitle("Pit11")
public class Pit11View extends HorizontalLayout {


    private TextField name;
    private Button sayHello;

    private UserService pracownikVoService;

    public Pit11View(@Autowired UserService pracownikVoService) {

        this.pracownikVoService = pracownikVoService;

        Optional<User> worker = pracownikVoService.findById(BigDecimal.valueOf(103208L));

        setId("pit11-view");
        name = new TextField("Your pesel is " + worker.get().getPassword() + " tell me your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener( e-> {
            Notification.show("Hello " + name.getValue() + " thank you worker number" + worker.get().getUsername() );

        });


    }
}




