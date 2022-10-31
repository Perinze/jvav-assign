module com.perinze.contact {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.zaxxer.hikari;
    requires org.controlsfx.controls;
    requires java.persistence;
    requires java.desktop;
    requires org.slf4j;
    requires spring.beans;
    requires spring.context;
    requires spring.core;
    requires spring.jdbc;
    requires java.sql;

    opens com.perinze.contact to javafx.fxml;
    exports com.perinze.contact;
    exports com.perinze.contact.model to spring.beans;
}