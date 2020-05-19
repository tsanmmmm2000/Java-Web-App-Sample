import com.google.inject.Inject;
import service.ContactService;

public class Factory {

    @Inject
    private ContactService contactService;

    public ContactService getContactService() {
        return contactService;
    }
}
