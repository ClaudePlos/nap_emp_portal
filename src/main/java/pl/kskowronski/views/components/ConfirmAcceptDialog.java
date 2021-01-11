package pl.kskowronski.views.components;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.kskowronski.data.entity.log.LogConfirmAcceptation;
import pl.kskowronski.data.service.log.LogConfirmAcceptationService;

import java.math.BigDecimal;
import java.util.Date;

@Component
@UIScope
public class ConfirmAcceptDialog extends Dialog {

    private LogConfirmAcceptationService logConfirmAcceptationService;

    public ConfirmAcceptDialog(LogConfirmAcceptationService logConfirmAcceptationService) {
         this.logConfirmAcceptationService = logConfirmAcceptationService;
         this.setCloseOnEsc(false);
         this.setCloseOnOutsideClick(false);
    }

    public void openConfirmDialog(BigDecimal prcId){
        Html text = new Html("<div> Tekst </div>");
        HorizontalLayout h1 = new HorizontalLayout();
        Button butAccept = new Button("Akceptuję");
        butAccept.addClickListener(e -> {
            confirmedAcceptation(prcId);
            close();
        });
        Button butReject = new Button("Odrzucam");
        butReject.addClickListener(e ->{
            butReject.getUI().ifPresent(ui ->
                    ui.navigate("logout"));
        });
        h1.add(butAccept,butReject);
        add(text,h1);
        open();
    }

    private void confirmedAcceptation(BigDecimal prcId){
        LogConfirmAcceptation logConfirmAcceptation =  new LogConfirmAcceptation();
        logConfirmAcceptation.setPrcId(prcId);
        logConfirmAcceptation.setAuditDc(new Date());
        logConfirmAcceptation.setDescription("Worker confirm accept to use app to get declaration");
        logConfirmAcceptationService.save(logConfirmAcceptation);
    }


}
