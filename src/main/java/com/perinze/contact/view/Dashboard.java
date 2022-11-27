package com.perinze.contact.view;

import com.perinze.contact.model.Contact;
import com.perinze.contact.service.ContactService;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.Comparator;

public class Dashboard extends VBox {
    ContactService contactService;
    Label labelTotalContact;
    Label labelTotalView;
    Label labelMostContact;
    Label labelMostView;
    Label labelLeastContact;
    Label labelLeastView;

    public Dashboard(ContactService contactService) {
        this.contactService = contactService;
        labelTotalContact = new Label();
        labelTotalView = new Label();
        labelMostContact = new Label();
        labelMostView = new Label();
        labelLeastContact = new Label();
        labelLeastView = new Label();
        var resetButton = new Button("Reset");
        resetButton.setOnAction(event -> {
            contactService.resetViewAll();
            update();
        });
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setSpacing(2);
        this.getChildren().addAll(
                labelTotalContact, labelTotalView,
                labelMostContact, labelMostView,
                labelLeastContact, labelLeastView,
                resetButton);
        update();
    }

    void update() {
        var list = contactService.getAll();
        var mostViewed = list.stream().max(Comparator.comparingInt(Contact::getViewed)).get();
        var leastViewed = list.stream().min(Comparator.comparingInt(Contact::getViewed)).get();
        labelTotalContact.setText(labelKV("total contact", list.size()));
        labelTotalView.setText(labelKV("total viewed", list.stream().mapToInt(Contact::getViewed).sum()));
        labelMostContact.setText(labelKV("most viewed contact", mostViewed.getName()));
        labelMostView.setText(labelKV("most viewed times", mostViewed.getViewed()));
        labelLeastContact.setText(labelKV("least viewed contact", leastViewed.getName()));
        labelLeastView.setText(labelKV("least viewed times", leastViewed.getViewed()));
    }

    String labelKV(String k, String v) {
        return k + ": " + v;
    }

    String labelKV(String k, int v) {
        return k + ": " + v;
    }
}
