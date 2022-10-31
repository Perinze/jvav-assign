package com.perinze.contact.view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MainBox extends VBox {
    final Button addButton = new Button("New");
    List<Node> nodes = new ArrayList<>();

    public MainBox(VBox infoBox, VBox addBox) {
        this.getChildren().addAll(addButton, infoBox, addBox);
        nodes.add(infoBox);
        nodes.add(addBox);
        switchTo(infoBox);
        addButton.setOnAction(actionEvent -> switchTo(addBox));
    }

    public void switchTo(Node node) {
        nodes.forEach(n -> n.setVisible(false));
        node.setVisible(true);
    }
}
