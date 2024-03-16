package Hibernate.HibernateSeperateCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class SelectOperation {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            List<ReportCard> reportCards = session.createQuery("FROM ReportCard", ReportCard.class).getResultList();
            
            System.out.printf("%-5s | %-20s | %-5s | %-10s | %-10s%n", "ID", "Student Name", "Marks", "Class", "Result");
            System.out.println("------------------------------------------------------------");
            for (ReportCard reportCard : reportCards) {
                System.out.printf("%-5s | %-20s | %-5d | %-10s | %-10s%n", reportCard.getId(), reportCard.getStudentName(),
                        reportCard.getMarks(), reportCard.getStudentClass(), reportCard.getResult());
            }

            transaction.commit();
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
