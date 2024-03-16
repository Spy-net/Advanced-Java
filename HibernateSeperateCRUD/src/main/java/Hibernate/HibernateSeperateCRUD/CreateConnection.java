package Hibernate.HibernateSeperateCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class CreateConnection {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE ReportCard (id BIGINT AUTO_INCREMENT, studentName VARCHAR(255), marks INT, studentClass VARCHAR(50), result VARCHAR(50), PRIMARY KEY (id))";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Table created successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
