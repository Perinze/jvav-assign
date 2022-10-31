package com.perinze.contact.view;

import com.perinze.contact.model.Contact;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class AddBox extends VBox {
    final TextField nameTextField = new TextField();
    final TextField noteTextField = new TextField();
    final Button doneButton = new Button("Done");

    public AddBox() {
        this.getChildren().addAll(nameTextField, noteTextField, doneButton);
    }

    public void setDoneAction(BiConsumer<ActionEvent, Contact> handler) {
        this.doneButton.setOnAction(action -> {
            Contact contact = new Contact(nameTextField.getText(), noteTextField.getText());
            handler.accept(action, contact);
        });
    }
}
