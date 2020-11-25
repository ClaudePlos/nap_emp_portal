package pl.kskowronski.views.payslipsJS;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import pl.kskowronski.views.main.MainView;

@Route(value = "payslipsJS", layout = MainView.class)
@PageTitle("payslipsJS")
@JsModule("./src/views/payslips/payslips-view.js")
@Tag("payslips-view")
public class PayslipsJsView extends PolymerTemplate<PayslipsJsView.PayslipsJsViewModel>{

    public static interface PayslipsJsViewModel extends TemplateModel {
    }

    public PayslipsJsView() {
    }

}
