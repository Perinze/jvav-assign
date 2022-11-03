package com.perinze.contact.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DetailBlock extends VBox {
    Label label = new Label();
    TextField value = new TextField();

    public DetailBlock(String detail, boolean editable) {
        label.setText(detail);
        value.setEditable(editable);
        value.setText(detail);
        this.getChildren().addAll(label, value);
    }

    public String get() {
        return value.getText();
    }
}
