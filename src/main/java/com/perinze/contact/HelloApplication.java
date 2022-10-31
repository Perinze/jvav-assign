package com.perinze.contact;

import com.perinze.contact.view.AddBox;
import com.perinze.contact.view.InfoBox;
import com.perinze.contact.view.ListBox;
import com.perinze.contact.view.MainBox;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.perinze.contact.model.AbstractModel;
import com.perinze.contact.model.Contact;
import com.perinze.contact.orm.DbTemplate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    DataSource ds;
    JdbcTemplate jdbcTemplate;
    DbTemplate db;

    @Override
    public void start(Stage stage) throws IOException {
        setDb();

        ListBox listBox = new ListBox();
        InfoBox infoBox = new InfoBox();
        AddBox addBox = new AddBox();
        MainBox mainBox = new MainBox(infoBox, addBox);
        HBox root = new HBox();

        listBox.setItems(db.from(Contact.class).list());
        listBox.setSelectionAction((observableValue, old, contact) -> infoBox.set(contact));

        addBox.setDoneAction((actionEvent, contact) -> {
            db.insert(contact);
            listBox.setItems(db.from(Contact.class).list());
            mainBox.switchTo(infoBox);
        });

        root.getChildren().addAll(listBox, mainBox);

        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    void setDb() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/contact");
        config.setUsername("user");
        config.setPassword("password");
        ds = new HikariDataSource(config);
        jdbcTemplate = new JdbcTemplate(ds);
        db = new DbTemplate(jdbcTemplate, AbstractModel.class.getPackageName());
    }
}