package com.perinze.contact.view;

import com.perinze.contact.service.ContactService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;
import java.util.Optional;

public class TabBox extends TabPane {
    ContactService contactService;

    public TabBox(ContactService contactService) {
        this.contactService = contactService;

        GroupBox primary = new GroupBox(contactService);
        Tab tabPrimary = new Tab("primary", primary);
        Tab tabAdd = new Tab("+");
        tabPrimary.setClosable(false);
        tabAdd.setClosable(false);
        this.getTabs().addAll(tabPrimary, tabAdd);

        this.getSelectionModel().selectedItemProperty().addListener((observableValue, oldTab, newTab) -> {
            if (newTab.textProperty().get().equals("+")) {
                System.out.println("+ selected");
                TextInputDialog inputDialog = new TextInputDialog();
                inputDialog.setHeaderText(null);
                Optional<String> result = inputDialog.showAndWait();
                if (result.isEmpty()) {
                    this.getSelectionModel().select(oldTab);
                    return;
                }
                newTab.setText(result.get());
                newTab.contentProperty().set(new GroupBox(contactService));
                newTab.setClosable(true);
                Tab tabPlus = new Tab("+");
                tabPlus.setClosable(false);
                this.getTabs().add(tabPlus);
            }
        });
    }
}
