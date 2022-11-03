package com.perinze.contact.view;

import com.perinze.contact.model.Contact;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoBox extends VBox {
    private DetailBlock name;
    private DetailBlock phone;
    private DetailBlock email;
    private boolean editable;
    private Contact contact;

    public InfoBox() {
        name = new DetailBlock("name");
        phone = new DetailBlock("phone");
        email = new DetailBlock("email");
        editable = false;
        contact = null;

        this.getChildren().addAll(name, phone, email);
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        name.setEditable(editable);
        phone.setEditable(editable);
        email.setEditable(editable);
    }

    public Contact get() {
        if (null == contact) {
            contact = new Contact();
        }
        contact.setName(name.get());
        contact.setPhone(phone.get());
        contact.setEmail(email.get());
        return contact;
    }

    public void set(Contact contact) {
        this.contact = contact;
        if (null != contact) {
            name.set(contact.getName());
            phone.set(contact.getPhone());
            email.set(contact.getEmail());
        }
    }

}
