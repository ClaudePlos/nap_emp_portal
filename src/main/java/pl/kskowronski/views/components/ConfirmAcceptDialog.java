package pl.kskowronski.views.components;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.stereotype.Component;

@Component
@UIScope
public class ConfirmAcceptDialog extends Dialog {

    public ConfirmAcceptDialog() {
         this.setCloseOnEsc(false);
         this.setCloseOnOutsideClick(false);
    }

    public void openConfirmDialog(){
        Html text = new Html("<div> Tekst </div>");
        HorizontalLayout h1 = new HorizontalLayout();
        Button butAccept = new Button("Akceptuję");
        Button butReject = new Button("Odrzucam");
        butReject.addClickListener(e ->{
            butReject.getUI().ifPresent(ui ->
                    ui.navigate("logout"));
        });
        h1.add(butAccept,butReject);
        add(text,h1);
        open();
    }

    private void logOutFromPage(){

    }


}
