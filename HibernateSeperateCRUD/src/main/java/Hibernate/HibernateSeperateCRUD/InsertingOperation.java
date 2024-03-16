package Hibernate.HibernateSeperateCRUD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class InsertingOperation {
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			ReportCard reportCard = new ReportCard();
			reportCard.setStudentName("Amritanshu");
			reportCard.setMarks(410);
			reportCard.setStudentClass("10th Grade");
			reportCard.setResult("Passed");
			session.save(reportCard);

			transaction.commit();
			System.out.println("Data inserted successfully.");
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
