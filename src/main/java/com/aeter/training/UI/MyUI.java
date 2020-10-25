package com.aeter.training.UI;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Theme(value = Lumo.class)
public class MyUI<event> extends UI {
    @Override
    public Component getActiveDragSourceComponent() {
        return super.getActiveDragSourceComponent();
    }


}
