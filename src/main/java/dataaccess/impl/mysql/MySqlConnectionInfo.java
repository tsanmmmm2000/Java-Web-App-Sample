package dataaccess.impl.mysql;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class MySqlConnectionInfo {

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://%s:%s/%s?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

    private String host = "localhost";
    private String port = "3306";
    private String schema = "person";
    private String user = "root";
    private String password = "root";

    public MySqlConnectionInfo() { }

    @Inject
    public MySqlConnectionInfo(@Named("host") String host, @Named("port") String port,
                               @Named("schema") String schema, @Named("user") String user,
                               @Named("password") String password) {
        this.host = host;
        this.port = port;
        this.schema = schema;
        this.user = user;
        this.password = password;
    }

    public String getHost() {
        return this.host;
    }

    public String getPort() {
        return this.port;
    }

    public String getSchema() {
        return this.schema;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUrl() {
        return String.format(url, host, port, schema);
    }

    public String getDriver() {
        return this.driver;
    }
}
