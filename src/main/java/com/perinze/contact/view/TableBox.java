package com.perinze.contact.view;

import com.perinze.contact.model.Contact;
import com.perinze.contact.service.ContactService;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class TableBox extends VBox {
    ContactService contactService;
    TableView tableView;

    public TableBox(ContactService contactService) {
        this.contactService = contactService;
        tableView = new TableView();

        TableColumn<Contact, String> columnId = new TableColumn<>("id");
        TableColumn<Contact, String> columnName = new TableColumn<>("name");
        TableColumn<Contact, String> columnPhone = new TableColumn<>("phone");
        TableColumn<Contact, String> columnEmail = new TableColumn<>("email");

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.getColumns().addAll(columnId, columnName, columnPhone, columnEmail);
        this.getChildren().add(tableView);

        refresh();
    }

    void refresh() {
        contactService.getAll().forEach(tableView.getItems()::add);
    }
}
