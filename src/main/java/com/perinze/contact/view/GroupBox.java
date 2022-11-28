package com.perinze.contact.view;

import com.perinze.contact.model.Contact;
import com.perinze.contact.service.ContactService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GroupBox extends VBox {
    Predicate<Contact> predicate;
    ContactService contactService;
    ListBox listBox;
    InfoBox infoBox;
    Button add;
    Button edit;
    Button remove;
    Button done;
    Button cancel;
    public GroupBox(ContactService contactService) {// create a menu
        this.contactService = contactService;

        // 创建列表栏和详细信息栏
        listBox = new ListBox();
        infoBox = new InfoBox();

        // 设置主框体样式和子对象
        HBox main = new HBox();
        main.prefWidthProperty().set(300);
        main.setSpacing(20);
        main.getChildren().addAll(listBox, infoBox);

        // 设置菜单
        MenuItem dataEntry = new MenuItem("Data Table");
        MenuItem dashboardEntry = new MenuItem("Dashboard");
        MenuButton menuButton = new MenuButton("more", null, dataEntry, dashboardEntry);

        // 创建按钮
        add = new Button("new");
        edit = new Button("edit");
        remove = new Button("remove");
        done = new Button("done");
        cancel = new Button("cancel");
        setEditing(false);

        // 创建按钮栏并包含以上按钮
        HBox buttons = new HBox();
        buttons.setSpacing(7);
        buttons.getChildren().addAll(add, edit, remove, done, cancel, menuButton);

        // 搜索框
        TextField search = new TextField();
        search.setOnAction(event -> {
           setPredicate(search.getText());
        });
        setPredicate("");

        // 设置样式
        this.setSpacing(12);
        this.setPadding(new Insets(10, 15, 15, 10));
        this.getChildren().addAll(buttons, main, search);

        // 刷新列表
        refreshList();

        // 设置列表中选中联系人时行为
        listBox.setSelectionAction((observableValue, old, contact) -> {
            setEditing(false); // 设置为查看状态（不可编辑）
            infoBox.set(contact); // 详细信息栏设置选中联系人
            contactService.increaseView(contact);
        });

        // 添加按钮行为
        add.setOnAction(event -> {
            setEditing(true); // 编辑状态
            infoBox.set(null); // 传入null使详细信息栏显示新建空联系人
        });

        // 删除按钮行为
        remove.setOnAction(event -> {
            contactService.remove(infoBox.get()); // 从详细信息栏获取联系人并删除
            refreshList(); // 刷新列表
        });

        // 修改按钮行为
        edit.setOnAction(event -> {
            setEditing(true); // 编辑状态
        });

        // 确认按钮行为
        done.setOnAction(event -> {
            setEditing(false); // 查看状态
            contactService.updateOrInsert(infoBox.get()); // 更新或插入该联系人
            refreshList(); // 刷新列表
            infoBox.set(listBox.getSelectedItem()); // 设置选中联系人
        });

        // 取消按钮行为
        cancel.setOnAction(event -> {
            setEditing(false); // 查看状态
            infoBox.set(listBox.getSelectedItem()); // 设置选中联系人
        });

        // 设置菜单行为
        dataEntry.setOnAction(event -> {
            TableBox tableBox = new TableBox(contactService);

            Scene scene = new Scene(tableBox);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });
        dashboardEntry.setOnAction(event -> {
            Dashboard dashboard = new Dashboard(contactService);

            Scene scene = new Scene(dashboard);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });
    }

    private void setEditing(boolean editing) {
        // 根据状态设置按钮是否可用，以及详细信息是否可编辑
        add.setDisable(editing);
        edit.setDisable(editing);
        remove.setDisable(editing);
        done.setDisable(!editing);
        cancel.setDisable(!editing);
        infoBox.setEditable(editing);
    };

    void refreshList() {
        listBox.setItems(contactService.getAll().stream().filter(this.predicate).collect(Collectors.toList()));
    }

    void setPredicate(String prefix) {
        this.predicate = contact -> contact.getName().toLowerCase().startsWith(prefix.toLowerCase());
        refreshList();
    }
}
