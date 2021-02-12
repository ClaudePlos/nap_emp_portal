package pl.kskowronski.views.login;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("/logout")
@PageTitle("Logout | Rekeep")
public class LogoutView extends VerticalLayout {

    public LogoutView() {
        add(new H1("Rekeep logout"));

        //UI.getCurrent().getPage().executeJs("window.location.href='logout'");
        // Close the sesWebApplicationContextsion
        UI.getCurrent().getSession().close();
        UI.getCurrent().close();
        VaadinSession.getCurrent().getSession().invalidate();
        UI.getCurrent().setPollInterval(3000);
    }
}
