package pl.kskowronski.views.mainpage;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.kskowronski.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "empty", layout = MainView.class)
@PageTitle("Main Page")
@CssImport("./styles/views/mainpage/main-page-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class MainPageView extends Div {

    public MainPageView() {
        setId("main-page-view");
        add(new Label("Content placeholder"));
    }

}
