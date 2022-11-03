package com.perinze.contact.view;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DetailBlock extends VBox {
    Label label;
    TextField value;
    boolean editable;

    public DetailBlock(String labelName) {
        label = new Label(labelName);
        value = new TextField();
        value.setEditable(false);
        editable = false;
        this.getChildren().addAll(label, value);
    }

    public String get() {
        return value.getText();
    }

    public void set(String v) {
        value.setText(v);
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        value.setEditable(editable);
    }

}
