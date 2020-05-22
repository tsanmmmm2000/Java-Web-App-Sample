package dataaccess;

import dataaccess.domain.Contact;

import java.util.List;

public interface ContactDao {
    void create(Contact contact);
    Contact read(int id);
    List<Contact> read();
    void update(Contact contact);
    void delete(int id);
}
