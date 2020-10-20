package pl.kskowronski.views.pit11;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.frontend.installer.DefaultFileDownloader;
import net.sf.jasperreports.engine.JRException;
import org.apache.catalina.webresources.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.kskowronski.data.entity.egeria.eDek.EdktDeklaracje;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.reaports.Pit11Service;
import pl.kskowronski.data.service.egeria.eDek.EdktDeklaracjeService;
import pl.kskowronski.data.service.egeria.ek.UserService;
import pl.kskowronski.views.main.MainView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Route(value = "Pit11", layout = MainView.class)
@PageTitle("Pit11")
public class Pit11View extends HorizontalLayout {

    private Grid<EdktDeklaracje> grid;

    private UserService userService;
    private EdktDeklaracjeService edktDeklaracjeService;


    @Autowired
    Pit11Service pit11Service;

    SimpleDateFormat dtYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dtYYYY = new SimpleDateFormat("yyyy");

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

        List<EdktDeklaracje> listEDeklaracje = edktDeklaracjeService.findAllByDklPrcId(worker.get().getPrcId())
                .get().stream()
                .filter( i -> i.getDklStatus().equals(BigDecimal.valueOf(50L))) // status have UPO
                .sorted(Comparator.comparing(EdktDeklaracje::getDklDataOd).reversed())
                .collect(Collectors.toList());
        //System.out.println(listEDeklaracje.size());
        grid.setItems(listEDeklaracje);

        // run generate pit pdf
        grid.addColumn(new NativeButtonRenderer<EdktDeklaracje>("Pit11",
                item -> {
                    try {
                        String path = pit11Service.exportPit11Report("pdf", worker.get().getPassword(), dtYYYY.format(item.getDklDataOd()),  item.getDklXmlVisual());
                        displayPitPDFonBrowser(path);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (JRException e) {
                        e.printStackTrace();
                    }
                }
        ));

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);

        add(splitLayout);

        refreshGrid();
    }

    private void generatDateInGrid(EdktDeklaracje item){
        grid.setItems(item);
    }

    private void displayPitPDFonBrowser(String path) throws FileNotFoundException {
        //UI.getCurrent().getPage().executeJavaScript("window.open('"+ path + "','_blank')");

        StreamResource resource = new StreamResource(path, (out, session) -> System.out.println(out.toString()));
        new Anchor(resource, "Download!");
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }
}




