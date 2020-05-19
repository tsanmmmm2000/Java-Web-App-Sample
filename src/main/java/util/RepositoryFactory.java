package util;

import com.google.inject.Inject;
import service.ContactService;

public class RepositoryFactory {

    @Inject
    private ContactService contactService;

    public ContactService getContactService() {
        return contactService;
    }
}
