package com.perinze.contact.view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import java.awt.event.PaintEvent;

public class Dashboard extends TableView {
    public Dashboard() {
        TableColumn<Pair<String, String>, String> key = new TableColumn<>("name");
        TableColumn<Pair<String, String>, String> value = new TableColumn<>("value");
        key.setCellValueFactory(new PropertyValueFactory<>("key"));
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        this.getColumns().addAll(key, value);
    }

    void update() {
        this.getItems().add(new Pair<>("total", ""));
        this.getItems().add(new Pair<>("total view time", ""));
        this.getItems().add(new Pair<>("most viewed", ""));
        this.getItems().add(new Pair<>("most viewed time", ""));
        this.getItems().add(new Pair<>("least viewed", ""));
        this.getItems().add(new Pair<>("least viewed time", ""));
    }
}
