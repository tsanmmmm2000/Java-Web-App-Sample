package service;

import com.google.inject.Inject;
import dataaccess.ContactDao;
import dataaccess.domain.Contact;

public class ContactService {

    @Inject
    private ContactDao contactDao;

    public void create(Contact contact) {
        contactDao.create(contact);
    }
    public Contact get(int id) {
        return contactDao.read(id);
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
