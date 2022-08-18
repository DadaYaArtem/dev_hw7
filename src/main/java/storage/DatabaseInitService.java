package storage;

import org.flywaydb.core.Flyway;

public class DatabaseInitService {
    public void initDB(String connectionUrl) {
        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();

        flyway.migrate();
    }
}
