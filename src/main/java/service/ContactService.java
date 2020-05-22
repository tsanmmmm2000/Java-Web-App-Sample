package service;

import dataaccess.domain.Contact;

import java.util.List;

public interface ContactService {
    void create(Contact contact);
    Contact get(int id);
    List<Contact> getAll();
    void update(Contact contact);
    void delete(int id);
    void delete(Contact contact);
}
