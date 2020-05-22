package dataaccess.impl.mysql;

import com.google.inject.Inject;
import dataaccess.ContactDao;
import dataaccess.domain.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MySqlContactDao implements ContactDao {

    @Inject
    private MySqlConnectionHelper connectionHelper;

    public void create(Contact contact) {
        String sql = "INSERT INTO contact (first_name, last_name, address) VALUES (?, ?, ?)";
        List<Object> values = new ArrayList<Object>();
        values.add(contact.getFirstName());
        values.add(contact.getLastName());
        values.add(contact.getAddress());
        connectionHelper.execute(sql, prepareParameters(values));
    }
    public Contact read(int id) {
        String sql = "SELECT * FROM contact WHERE id = ?";
        List<Object> values = new ArrayList<Object>();
        values.add(id);
        List<Contact> contacts = connectionHelper.execute(sql, prepareParameters(values));
        return (contacts.size() > 0) ? contacts.get(0) : new Contact();
    }
    public List<Contact> read() {
        String sql = "SELECT * FROM contact";
        return connectionHelper.execute(sql, new HashMap<Integer, Object>());
    }
    public void update(Contact contact) {
        String sql = "UPDATE contact first_name = ?, last_name = ?, address = ? WHERE id = ?";
        List<Object> values = new ArrayList<Object>();
        values.add(contact.getFirstName());
        values.add(contact.getLastName());
        values.add(contact.getAddress());
        values.add(contact.getId());
        connectionHelper.execute(sql, prepareParameters(values));
    }
    public void delete(int id) {
        String sql = "DELETE FROM contact WHERE id = ?";
        List<Object> values = new ArrayList<Object>();
        values.add(id);
        connectionHelper.execute(sql, prepareParameters(values));
    }

    private HashMap<Integer, Object> prepareParameters(List<Object> values) {
        HashMap<Integer, Object> parameters = new HashMap<Integer, Object>();
        int parameterIndex = 1;
        for (Object value : values) {
            parameters.put(parameterIndex++, value);
        }
        return parameters;
    }
}
