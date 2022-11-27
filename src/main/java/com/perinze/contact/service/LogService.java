package com.perinze.contact.service;

import com.perinze.contact.model.Contact;
import com.perinze.contact.orm.DbTemplate;

public class LogService {
    final private DbTemplate db;

    public LogService(DbTemplate db) {
        this.db = db;
    }

}
