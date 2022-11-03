package com.perinze.contact.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DetailBlock extends VBox {
    Label label;
    TextField value;

    public DetailBlock(String labelName) {
        label = new Label(labelName);
        value = new TextField();
        this.getChildren().addAll(label, value);
    }

    public String get() {
        return value.getText();
    }

    public void set(String v) {
        value.setText(v);
    }
}
