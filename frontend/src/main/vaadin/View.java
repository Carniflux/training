package main.vaadin;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Route("label")
public class View extends AppLayout {
    VerticalLayout layout;
    //RouterLink linkCreate;

    public View() {
        layout = new VerticalLayout();

    }
}
