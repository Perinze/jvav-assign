package com.perinze.contact.service;

import com.perinze.contact.model.Contact;
import com.perinze.contact.orm.DbTemplate;

import java.util.List;

public class ContactService {
    final private DbTemplate db;

    public ContactService(DbTemplate db) {
        this.db = db;
    }

    public List<Contact> getAll() {
        return db.from(Contact.class).list();
    }

    public void insert(Contact contact) {
        db.insert(contact);
    }
}