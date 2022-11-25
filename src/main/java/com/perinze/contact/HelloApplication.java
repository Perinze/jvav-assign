package com.perinze.contact;

import com.perinze.contact.service.ContactService;
import com.perinze.contact.view.*;
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
import javafx.scene.text.Text;
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
    ContactService contactService;

    @Override
    public void start(Stage stage) throws IOException {
        setDb();
        contactService = new ContactService(db);

        RootBox root = new RootBox(contactService);

        TabPane tabs = new TabPane();
        Tab tabPrimary = new Tab("primary", root);
        Tab tabAdd = new Tab("+");
        tabPrimary.setClosable(false);
        tabAdd.setClosable(false);
        tabs.getTabs().addAll(tabPrimary, tabAdd);

        tabAdd.setOnSelectionChanged(event -> {
            if (tabs.getSelectionModel().getSelectedItem() == tabAdd) {
                System.out.println("plus selected");
            }
        });

        TableBox wdnmd = new TableBox(contactService);

        Scene scene = new Scene(tabs, 400, 300);
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