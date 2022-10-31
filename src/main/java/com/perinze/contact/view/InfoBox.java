package com.perinze.contact.view;

import com.perinze.contact.model.Contact;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoBox extends VBox {
    Label nameLabel = new Label();
    Label noteLabel = new Label();

    public InfoBox() {
        this.getChildren().addAll(nameLabel, noteLabel);
    }

    public void set(Contact contact) {
        nameLabel.setText(contact.getName());
        noteLabel.setText(contact.getNote());
    }
}
