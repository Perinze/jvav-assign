package com.perinze.contact;

import com.perinze.contact.service.ContactService;
import com.perinze.contact.view.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.perinze.contact.model.AbstractModel;
import com.perinze.contact.orm.DbTemplate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;

public class HelloApplication extends Application {
    DataSource ds;
    JdbcTemplate jdbcTemplate;
    DbTemplate db;
    ContactService contactService;

    @Override
    public void start(Stage stage) throws IOException {
        setDb();
        contactService = new ContactService(db);

        GroupBox root = new GroupBox(contactService);

        TableBox wdnmd = new TableBox(contactService);

        TabBox tabs = new TabBox(contactService);

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