package pl.kskowronski.views.payslips;


import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kskowronski.data.MapperDate;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.entity.egeria.ek.Zatrudnienie;
import pl.kskowronski.data.service.egeria.ek.ZatrudnienieService;
import pl.kskowronski.views.main.MainView;

import java.util.Date;

@Route(value = "payslips", layout = MainView.class)
@PageTitle("Paski")
@CssImport("./styles/views/payslips/payslips-view.css")
public class PayslipsView extends VerticalLayout {

    private ZatrudnienieService zatrudnienieService;
    private MapperDate mapperDate = new MapperDate();

    private Grid<Zatrudnienie> gridContracts;
    private TextField textPeriod = new TextField("Okres");

    private User worker;

    public PayslipsView(@Autowired ZatrudnienieService zatrudnienieService){
        setId("payslips-view");
        this.zatrudnienieService = zatrudnienieService;
        VaadinSession session = VaadinSession.getCurrent();
        worker = session.getAttribute(User.class);

        this.gridContracts = new Grid<>(Zatrudnienie.class);

        Date now = new Date();

        textPeriod.setValue(mapperDate.dtYYYYMM.format(now));
        add(textPeriod);


    }


}
