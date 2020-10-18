package pl.kskowronski.views.pit11;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.vaadin.artur.helpers.CrudServiceDataProvider;
import pl.kskowronski.data.entity.Person;
import pl.kskowronski.data.entity.egeria.eDek.EdktDeklaracje;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.service.egeria.eDek.EdktDeklaracjeService;
import pl.kskowronski.data.service.egeria.ek.UserService;
import pl.kskowronski.views.main.MainView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Route(value = "Pit11", layout = MainView.class)
@PageTitle("Pit11")
public class Pit11View extends HorizontalLayout {

    private Grid<EdktDeklaracje> grid;

    private UserService userService;
    private EdktDeklaracjeService edktDeklaracjeService;

    public Pit11View(@Autowired UserService userService, @Autowired EdktDeklaracjeService edktDeklaracjeService) {

        this.userService = userService;
        this.edktDeklaracjeService = edktDeklaracjeService;

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> worker = userService.findByPassword(userDetails.getPassword());

        Optional<EdktDeklaracje> dek = edktDeklaracjeService.findByDklId(BigDecimal.valueOf(686L));
        System.out.println(dek.get().getDklXmlVisual());
        generatDateInGrid(dek.get());
//        Optional<List<EdktDeklaracje>> listEDeklaracje = edktDeklaracjeService.findAllByDklPrcId(worker.get().getPrcId());
//        listEDeklaracje.get().stream().forEach( item -> {
//            generatDateInGrid(item);
//        });


        setId("pit11-view");

        grid = new Grid<>(EdktDeklaracje.class);
        grid.setColumns("firstName", "lastName", "email", "phone", "dateOfBirth", "occupation");

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);

        add(splitLayout);


    }

    private void generatDateInGrid(EdktDeklaracje item){
        grid.setItems(item);
    }


    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }
}




