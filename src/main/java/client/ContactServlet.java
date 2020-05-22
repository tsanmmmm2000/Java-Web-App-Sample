package client;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dataaccess.domain.Contact;
import service.ContactService;
import util.Configuration;
import util.RepositoryFactory;
import util.Utility;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="ContactServlet", urlPatterns={"/contact"})
public class ContactServlet extends HttpServlet {

    private final String create = "create";
    private final String get = "get";
    private final String getAll = "getall";
    private final String update = "update";
    private final String delete = "delete";

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RepositoryFactory factory;
    private ContactService contactService;
    private Injector injector;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        request = req;
        response = resp;

        if (injector == null) {
            injector = Guice.createInjector(new Configuration());
            factory = injector.getInstance(RepositoryFactory.class);
            contactService = factory.getContactService();
        }

        String command = request.getParameter("cmd");
        processCommand(command);
    }

    private void processCommand(String command) throws IOException {
        switch(command) {
            case create:
                create();
                break;
            case get:
                get();
                break;
            case getAll:
                getAll();
                break;
            case update:
                update();
                break;
            case delete:
                delete();
                break;
            default:
                break;
        }
    }

    private void create() throws IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String address = request.getParameter("address");

        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setAddress(address);

        contactService.create(contact);

        Utility.writeAjaxResult(response, true, "", null);
    }

    private void get() throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Contact contact = contactService.get(id);

        Utility.writeAjaxResult(response, true, "", contact);
    }

    private void getAll() throws IOException {
        List<Contact> contacts = contactService.getAll();

        Utility.writeAjaxResult(response, true, "", contacts);
    }

    private void update() throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String address = request.getParameter("address");

        Contact contact = contactService.get(id);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setAddress(address);

        contactService.update(contact);

        Utility.writeAjaxResult(response, true, "", null);
    }

    private void delete() throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        contactService.delete(id);

        Utility.writeAjaxResult(response, true, "", null);
    }
}
