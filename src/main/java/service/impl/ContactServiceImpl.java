package service.impl;

import com.google.inject.Inject;
import dataaccess.ContactDao;
import dataaccess.domain.Contact;
import service.ContactService;

import java.util.List;

public class ContactServiceImpl implements ContactService {

    @Inject
    private ContactDao contactDao;

    public void create(Contact contact) {
        contactDao.create(contact);
    }
    public Contact get(int id) {
        return contactDao.read(id);
    }
    public List<Contact> getAll() {
        return contactDao.read();
    }
    public void update(Contact contact) {
        contactDao.update(contact);
    }
    public void delete(int id) {
        contactDao.delete(id);
    }
    public void delete(Contact contact) {
        delete(contact.getId());
    }
}
