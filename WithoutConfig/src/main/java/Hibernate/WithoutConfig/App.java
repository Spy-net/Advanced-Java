package Hibernate.WithoutConfig;

import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Configuration config = new Configuration();

        Properties propertiesSetting = new Properties();
        propertiesSetting.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        propertiesSetting.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/javaadv");
        propertiesSetting.put("hibernate.connection.username", "root");
        propertiesSetting.put("hibernate.connection.password", "SPYBOT");
        propertiesSetting.put("hibernate.show_sql", true);
        propertiesSetting.put("hibernate.hbm2ddl.auto", "update");
        config.setProperties(propertiesSetting);
        
        config.addClass(Students.class);

        SessionFactory factory = config.buildSessionFactory();
        Session session = factory.openSession();

        Transaction tx = session.beginTransaction();

        Query<Students> query = session.createQuery("from Students", Students.class);
        List<Students> students = query.list();
        for (Students s : students) {
            System.out.println(s);
        }

        tx.commit();

        session.close();

        factory.close();
    }
}
