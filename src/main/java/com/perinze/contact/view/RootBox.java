package com.perinze.contact.view;

import com.perinze.contact.service.ContactService;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class RootBox extends ListBox {
    ContactService contactService;
    ListBox listBox;
    InfoBox infoBox;
    Button add;
    Button edit;
    Button remove;
    Button done;
    Button cancel;
    public RootBox(ContactService contactService) {
        this.contactService = contactService;

        listBox = new ListBox();
        infoBox = new InfoBox();

        HBox main = new HBox();
        main.prefWidthProperty().set(300);
        main.setSpacing(20);
        main.getChildren().addAll(listBox, infoBox);

        add = new Button("new");
        edit = new Button("edit");
        remove = new Button("remove");
        done = new Button("done");
        cancel = new Button("cancel");
        setEditing(false);

        HBox buttons = new HBox();
        buttons.setSpacing(7);
        buttons.getChildren().addAll(add, edit, remove, done, cancel);

        this.setSpacing(12);
        this.setPadding(new Insets(10, 15, 15, 10));
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
        remove.setOnAction(event -> {
            contactService.remove(infoBox.get());
            refreshList();
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
        remove.setDisable(editing);
        done.setDisable(!editing);
        cancel.setDisable(!editing);
        infoBox.setEditable(editing);
    };

    void refreshList() {
        listBox.setItems(contactService.getAll());
    }
}
