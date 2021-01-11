package pl.kskowronski.views.main;

import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.kskowronski.data.entity.egeria.ek.User;
import pl.kskowronski.data.entity.log.LogConfirmAcceptation;
import pl.kskowronski.data.service.egeria.ek.UserService;
import pl.kskowronski.data.service.egeria.global.NapUserService;
import pl.kskowronski.data.service.log.LogConfirmAcceptationService;
import pl.kskowronski.views.components.ConfirmAcceptDialog;
import pl.kskowronski.views.mainpage.MainPageView;
import pl.kskowronski.views.absences.AllAboutAbsencesView;
import pl.kskowronski.views.about.AboutView;
import pl.kskowronski.views.payslips.PayslipsView;
import pl.kskowronski.views.payslipsJS.PayslipsJsView;
import pl.kskowronski.views.pit11.Pit11View;

/**
 * The main view is a top-level placeholder for other views.
 */
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/views/main/main-view.css")
public class MainView extends AppLayout {

    private final Tabs menu;
    private H1 viewTitle;



    @Autowired
    public MainView(UserService userService, NapUserService napUserService, LogConfirmAcceptationService logConfirmAcceptationService) {
        //UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //System.out.println(userDetails.getUsername());
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> worker = userService.findByUsername(userDetails.getUsername());
        VaadinSession session = VaadinSession.getCurrent();
        session.setAttribute(User.class, worker.get());

        //check user accept to get data, if not then open confirm Dialog
        Optional<LogConfirmAcceptation> logConfirmAcceptation = logConfirmAcceptationService.findByPrcId(worker.get().getPrcId());
        if (!logConfirmAcceptation.isPresent()) {
            ConfirmAcceptDialog confirmAcceptDialog = new ConfirmAcceptDialog(logConfirmAcceptationService);
            confirmAcceptDialog.openConfirmDialog(worker.get().getPrcId());
        }
    }

    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new DrawerToggle());
        viewTitle = new H1();
        layout.add(viewTitle);

        Anchor logout = new Anchor("/logout","Wyloguj ");
        NativeButton buttonLogOut = new NativeButton(
                "LogOut");
//        buttonLogOut.addClickListener(e ->
//                buttonLogOut.getUI().ifPresent(ui ->
//                        ui.navigate("logout"))
//        );



        layout.add(new Image("images/user.svg", "Avatar"), logout);
        return layout;
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new Image("images/logo.png", "nap_emp_portal logo"));
        logoLayout.add(new H1("Portal"));
        layout.add(logoLayout, menu);
        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());
        return tabs;
    }

    private Component[] createMenuItems() {
        return new Tab[] {
            createTab("Strona główna", MainPageView.class),
            createTab("Twój urlop", AllAboutAbsencesView.class),
            createTab("Pit11", Pit11View.class),
            createTab("Paski", PayslipsView.class),
            //createTab("Ocena 360", CardListView.class),
            //createTab("Master-Detail", MasterDetailView.class)
            createTab("PaskiJS", PayslipsJsView.class),
            createTab("About", AboutView.class)
        };
    }

    private static Tab createTab(String text, Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
        viewTitle.setText(getCurrentPageTitle());
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren()
                .filter(tab -> ComponentUtil.getData(tab, Class.class)
                        .equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    private String getCurrentPageTitle() {
        return getContent().getClass().getAnnotation(PageTitle.class).value();
    }
}
