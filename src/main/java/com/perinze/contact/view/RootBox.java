package com.perinze.contact.view;

import com.perinze.contact.service.ContactService;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class RootBox extends VBox {
    ContactService contactService;
    ListBox listBox;
    InfoBox infoBox;
    Button add;
    Button edit;
    Button done;
    Button cancel;
    public RootBox(ContactService contactService) {
        this.contactService = contactService;

        listBox = new ListBox();
        infoBox = new InfoBox();

        HBox main = new HBox();
        main.getChildren().addAll(listBox, infoBox);

        add = new Button("new");
        edit = new Button("edit");
        done = new Button("done");
        cancel = new Button("cancel");
        setEditing(false);

        HBox buttons = new HBox();
        buttons.getChildren().addAll(add, edit, done, cancel);

        this.getChildren().addAll(buttons, main);


        refreshList();
        listBox.setSelectionAction((observableValue, old, contact) -> {
            setEditing(false);
            infoBox.set(contact);
        });

        add.setOnAction(event -> {
            setEditing(true);
            infoBox.set(null);
        });
        edit.setOnAction(event -> {
            setEditing(true);
        });
        done.setOnAction(event -> {
            setEditing(false);
            contactService.updateOrInsert(infoBox.get());
            refreshList();
            infoBox.set(listBox.getSelectedItem());
        });
        cancel.setOnAction(event -> {
            setEditing(false);
            infoBox.set(listBox.getSelectedItem());
        });
    }

    private void setEditing(boolean editing) {
        add.setDisable(editing);
        edit.setDisable(editing);
        done.setDisable(!editing);
        cancel.setDisable(!editing);
        infoBox.setEditable(editing);
    };

    void refreshList() {
        listBox.setItems(contactService.getAll());
    }
}
