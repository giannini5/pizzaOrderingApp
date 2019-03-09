package pizza;

import pizza.orm.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pizza.service.database.Database;
import pizza.service.database.TestHelper;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
            primeDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void primeDatabase() throws Exception {
        TestHelper.primeDatabase();
        Database.getInstance().setAutoCommit(false);
    }
}
