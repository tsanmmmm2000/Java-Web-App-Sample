package util;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import dataaccess.ContactDao;
import dataaccess.impl.mysql.MySqlConnectionHelper;
import dataaccess.impl.mysql.MySqlConnectionInfo;
import dataaccess.impl.mysql.MySqlContactDao;
import service.ContactService;
import service.impl.ContactServiceImpl;

public class Configuration extends AbstractModule {

    @Override
    public void configure() {

        // MySqlConnectionInfo
        bind(String.class).annotatedWith(Names.named("host")).toInstance("localhost");
        bind(String.class).annotatedWith(Names.named("port")).toInstance("3306");
        bind(String.class).annotatedWith(Names.named("schema")).toInstance("person");
        bind(String.class).annotatedWith(Names.named("user")).toInstance("root");
        bind(String.class).annotatedWith(Names.named("password")).toInstance("root");
        bind(MySqlConnectionInfo.class);

        // MySqlConnectionHelper
        try {
            bind(MySqlConnectionHelper.class)
                .toConstructor(MySqlConnectionHelper.class.getConstructor(MySqlConnectionInfo.class));
        } catch(NoSuchMethodException ex) {
            ex.printStackTrace();
        }

        // ContactDao
        bind(ContactDao.class).to(MySqlContactDao.class);

        // ContactService
        bind(ContactService.class).to(ContactServiceImpl.class);
    }
}
