package client;

import dataaccess.domain.Contact;
import service.ContactService;
import util.RepositoryFactory;
import util.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContactServlet extends HttpServlet {

    private final String create = "create";
    private final String get = "get";
    private final String update = "update";
    private final String delete = "delete";

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ContactService contactService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        request = req;
        response = resp;

        RepositoryFactory factory = Utility.getRepository();
        contactService = factory.getContactService();

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
