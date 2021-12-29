package pl.kskowronski.views.pit11list;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.kskowronski.data.MapperDate;
import pl.kskowronski.data.entity.egeria.css.SK;
import pl.kskowronski.data.entity.egeria.eDek.EdktDeklaracjeDTO;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.service.egeria.css.SKService;
import pl.kskowronski.data.service.egeria.edek.EdktDeklaracjeService;
import pl.kskowronski.data.service.egeria.ek.UserService;
import pl.kskowronski.views.main.MainView;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Route(value = "Pit11list", layout = MainView.class)
@PageTitle("Pit11list")
@CssImport("./styles/views/pit11/pit11-view.css")
public class Pit11listView extends VerticalLayout {

    MapperDate mapperDate = new MapperDate();
    private UserService userService;
    private EdktDeklaracjeService edktDeklaracjeService;
    private SKService skService;
    private Grid<EdktDeklaracjeDTO> grid;
    private Optional<User> worker;
    private NumberField yearField = new NumberField();
    private Select<SK> selectSK = new Select<>();

    private HorizontalLayout hTop = new HorizontalLayout();


    @Autowired
    public Pit11listView(UserService userService, EdktDeklaracjeService edktDeklaracjeService, SKService skService) throws ParseException {
        this.userService = userService;
        this.edktDeklaracjeService = edktDeklaracjeService;
        this.skService = skService;

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        worker = userService.findByUsername(userDetails.getUsername());
        hTop.setClassName("hTop");
        addYearField();
        addSelectSK();

        add(hTop);
        Button buttonRun = new Button("Pobierz");
        buttonRun.addClickListener(clickEvent -> {
            onUserChangedYear(String.valueOf(yearField.getValue().intValue()));
        });
        hTop.add(buttonRun);
    }


    private void onUserChangedYear(String year)  {
        try {
            Optional<List<EdktDeklaracjeDTO>> listEDeklaracje =  edktDeklaracjeService.getListPit11ForSupervisor(year, selectSK.getValue().getSkId());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void addYearField() {
        yearField.setLabel("Rok");
        yearField.getElement().setProperty("title", "Test");
        yearField.setHasControls(true);
        yearField.setValue(Double.parseDouble(Long.parseLong(mapperDate.getCurrentlyYear())-1L + ""));
        yearField.addValueChangeListener( e-> {
            try {
                onUserChangedYear( String.valueOf(e.getValue().intValue()));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        hTop.add(yearField);
    }

    private void addSelectSK() {
        List<SK> listSK = skService.findAll();
        selectSK.setItems(listSK);
        selectSK.setItemLabelGenerator(SK::getSkKod);
        selectSK.setEmptySelectionCaption(listSK.get(0).getSkKod());
        selectSK.setLabel(listSK.get(0).getSkKod());
        selectSK.setValue(listSK.get(0));
        hTop.add(selectSK);
    }


}
