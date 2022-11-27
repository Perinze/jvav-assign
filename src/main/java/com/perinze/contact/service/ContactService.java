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

    public void updateOrInsert(Contact contact) {
        if (null == contact.getId()) {
            db.insert(contact);
        } else {
            db.update(contact);
        }
    }

    public void remove(Contact contact) {
        if (null != contact.getId()) {
            db.delete(contact);
        }
    }

    public void increaseView(Contact contact) {
        if (null != contact.getId()) {
            contact.setViewed(contact.getViewed() + 1);
            db.update(contact);
        }
    }

    public void resetView(Contact contact) {
        if (null != contact.getId()) {
            contact.setViewed(0);
            db.update(contact);
        }
    }

    public void resetViewAll() {
        getAll().forEach(contact -> {
            contact.setViewed(0);
            db.update(contact);
        });
    }
}
