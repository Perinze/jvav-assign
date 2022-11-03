package com.perinze.contact.view;

import com.perinze.contact.model.Contact;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.Collectors;

public class ListBox extends VBox {
    ListView<Contact> list = new ListView<>();

    public ListBox() {
        this.getChildren().addAll(list);
        list.setCellFactory(contact -> new ChoiceBoxListCell<>());
    }

    public void setItems(List<Contact> contacts) {
        list.setItems(FXCollections.observableList(contacts));
    }

    public void setSelectionAction(ChangeListener<Contact> listener) {
        list.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    public Contact getSelectedItem() {
        return list.getSelectionModel().getSelectedItem();
    }
}
