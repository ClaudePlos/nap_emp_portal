package pl.kskowronski.views.mainpage;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.service.egeria.ek.UserService;
import pl.kskowronski.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

import java.math.BigDecimal;
import java.util.Optional;

@Route(value = "empty", layout = MainView.class)
@PageTitle("Strona główna")
@CssImport("./styles/views/mainpage/main-page-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class MainPageView extends Div {

    private UserService userService;

    public MainPageView(@Autowired UserService userService) {
        setId("main-page-view");
        this.userService = userService;

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> worker = userService.findByUsername(userDetails.getUsername());

        add(new Label("Witaj " + worker.get().getPrcImie() + " " + worker.get().getPrcNazwisko() + ". " ));
        add(new Label("To jest strona przeznaczona dla Ciebie z dostępem do Twoich danych kadrowych. Wkrótce zostanie dodana instrukcja obsługi."));
    }

}
