package pl.kskowronski.views.pit11;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.vaadin.artur.helpers.CrudServiceDataProvider;
import pl.kskowronski.data.entity.Person;
import pl.kskowronski.data.entity.egeria.eDek.EdktDeklaracje;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.reaports.Pit11Service;
import pl.kskowronski.data.service.egeria.eDek.EdktDeklaracjeService;
import pl.kskowronski.data.service.egeria.ek.UserService;
import pl.kskowronski.views.main.MainView;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Route(value = "Pit11", layout = MainView.class)
@PageTitle("Pit11")
public class Pit11View extends HorizontalLayout {

    private Grid<EdktDeklaracje> grid;

    private UserService userService;
    private EdktDeklaracjeService edktDeklaracjeService;


    @Autowired
    Pit11Service pit11Service;

    public Pit11View(@Autowired UserService userService, @Autowired EdktDeklaracjeService edktDeklaracjeService) {

        this.userService = userService;
        this.edktDeklaracjeService = edktDeklaracjeService;

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> worker = userService.findByPassword(userDetails.getPassword());

        //Optional<EdktDeklaracje> dek = edktDeklaracjeService.findByDklId(BigDecimal.valueOf(686L));
        //System.out.println(dek.get().getDklId());
        //generatDateInGrid(dek.get());

        setId("pit11-view");

        grid = new Grid<>(EdktDeklaracje.class);
        grid.setColumns("dklId", "dklTdlKod", "dklPrcId", "dklDataOd", "dklDataDo", "dklXmlVisual", "dklFrmId");
        //grid.setDataProvider(dataProvider);
        //grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        //rid.setHeightFull();

        Optional<List<EdktDeklaracje>> listEDeklaracje = edktDeklaracjeService.findAllByDklPrcId(worker.get().getPrcId());
//        listEDeklaracje.get().stream().forEach( item -> {
//            generatDateInGrid(item);
//        });
        grid.setItems(listEDeklaracje.get());

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);

        add(splitLayout);

        refreshGrid();
    }

    private void generatDateInGrid(EdktDeklaracje item){
        grid.setItems(item);
    }


    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        Button btnTestReport = new Button("TestReport", event -> {
            String repReturn = null;
            try {
                repReturn = pit11Service.exportPit11Report("pdf");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (JRException e) {
                e.printStackTrace();
            }
            System.out.println(repReturn);
        });
        wrapper.add(grid, btnTestReport);



    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }
}




