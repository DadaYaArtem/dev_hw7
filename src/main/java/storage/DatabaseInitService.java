package storage;

import org.flywaydb.core.Flyway;

import javax.servlet.ServletContextListener;

public class DatabaseInitService implements ServletContextListener {
    public void initDB(String connectionUrl) {
        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();

        flyway.migrate();
    }
}
