package pl.kskowronski.views.about;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.kskowronski.views.main.MainView;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
@CssImport("./styles/views/about/about-view.css")
@JavaScript("./js/test.js")
public class AboutView extends Div {

    public AboutView() {
        setId("about-view");
        add(new Label("Content made by k.skowronski"));

        String initFunction = "createHandsontable($0, $1);";
        UI.getCurrent().getPage().executeJs(initFunction, this, "ks-test");


        logElementSize("klaud");
    }

    public static void logElementSize(String name) {
        Page page = UI.getCurrent().getPage();

        page.executeJs(
                //"alert('Hello! I am an alert box!!' + $0);" +
                         "console.log('Test');"
                , name);
    }

}




