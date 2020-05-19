package dataaccess;

import dataaccess.domain.Contact;

public interface ContactDao {
    void create(Contact contact);
    Contact read(int id);
    void update(Contact contact);
    void delete(int id);
}
