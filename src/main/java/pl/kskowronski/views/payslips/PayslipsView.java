package pl.kskowronski.views.payslips;


import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.kskowronski.views.main.MainView;

@Route(value = "payslips", layout = MainView.class)
@PageTitle("Paski")
@CssImport("./styles/views/payslips/payslips-view.css")
public class PayslipsView extends VerticalLayout {


}
