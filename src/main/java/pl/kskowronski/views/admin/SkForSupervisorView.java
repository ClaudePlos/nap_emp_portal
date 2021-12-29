package pl.kskowronski.views.admin;

import com.vaadin.flow.component.crud.BinderCrudEditor;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.crud.CrudEditor;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kskowronski.component.SkForSupervisorDataProvider;
import pl.kskowronski.data.entity.admin.NppSkForSupervisor;
import pl.kskowronski.data.entity.egeria.css.SK;
import pl.kskowronski.data.service.admin.NppSkForSupervisorService;
import pl.kskowronski.data.service.egeria.css.SKService;
import pl.kskowronski.views.main.MainView;

import java.util.Arrays;
import java.util.List;


@Route(value = "sk-for-supervisor", layout = MainView.class)
@PageTitle("SkForSupervisorView")
public class SkForSupervisorView extends Div {


    private NppSkForSupervisorService nppSkForSupervisorService;
    private SKService skService;

    private Crud<NppSkForSupervisor> crud;

    private String PRC_NAZWISKO_IMIE = "prcNazwiskoImie";
    private String SK_KOD = "skKod";
    private String EDIT_COLUMN = "vaadin-crud-edit-column";

    @Autowired
    public SkForSupervisorView(NppSkForSupervisorService nppSkForSupervisorService, SKService skService) {
        this.nppSkForSupervisorService = nppSkForSupervisorService;
        this.skService = skService;
        setHeightFull();
        crud = new Crud<>(
                NppSkForSupervisor.class,
                createEditor()
        );



        setupGrid();
        setupDataProvider();

        add(crud);
    }

    private CrudEditor<NppSkForSupervisor> createEditor() {
        TextField supervisor = new TextField("Kierownik");

        Select<SK> selectSkKod = getSelectSK();

        FormLayout form = new FormLayout(supervisor, selectSkKod);

        Binder<NppSkForSupervisor> binder = new Binder<>(NppSkForSupervisor.class);
        binder.forField(supervisor).asRequired().bind(NppSkForSupervisor::getPrcNazwiskoImie, NppSkForSupervisor::setPrcNazwiskoImie);
        binder.forField(selectSkKod).asRequired().bind(NppSkForSupervisor::getSk, NppSkForSupervisor::setSk);


        return new BinderCrudEditor<>(binder, form);
    }

    private void setupGrid() {
        Grid<NppSkForSupervisor> grid = crud.getGrid();

        // Only show these columns (all columns shown by default):
        List<String> visibleColumns = Arrays.asList(
                PRC_NAZWISKO_IMIE,
                SK_KOD,
                EDIT_COLUMN
        );
        grid.getColumns().forEach(column -> {
            String key = column.getKey();
            if (!visibleColumns.contains(key)) {
                grid.removeColumn(column);
            }
        });

        // Reorder the columns (alphabetical by default)
        grid.setColumnOrder(
                grid.getColumnByKey(PRC_NAZWISKO_IMIE),
                grid.getColumnByKey(SK_KOD),
                grid.getColumnByKey(EDIT_COLUMN)
        );
    }

    private void setupDataProvider() {
        SkForSupervisorDataProvider dataProvider = new SkForSupervisorDataProvider(nppSkForSupervisorService, skService);
        crud.setDataProvider(dataProvider);
        crud.addDeleteListener(deleteEvent ->
                dataProvider.delete(deleteEvent.getItem())
        );
        crud.addSaveListener(saveEvent ->
                dataProvider.persist(saveEvent.getItem())
        );
    }





    private Select<SK> getSelectSK() {
        Select<SK> selectSK = new Select<>();
        List<SK> listSK = skService.findAll();
        selectSK.setItems(listSK);
        selectSK.setItemLabelGenerator(SK::getSkKod);
        //selectSK.setEmptySelectionCaption(listSK.get(0).getSkKod());
        selectSK.setLabel("Obiekt");
        return selectSK;
    }

}
