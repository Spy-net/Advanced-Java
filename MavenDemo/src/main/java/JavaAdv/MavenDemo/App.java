package JavaAdv.MavenDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

//import java.util.List;

public class App {
	public static void main(String[] args) {
//        System.out.println("Hello World!");

		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");

		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();

		// Insert/update the table
		Employee s1 = new Employee();
		s1.setE_Name("Himanshu");
		s1.setSALARY(2400);
		s1.setAddress("Danapur");

		Transaction tx = session.beginTransaction();
		session.save(s1);

		// Fetch
		/*
		 * student fetchedStudent = session.get(student.class, 1);
		 * System.out.println(fetchedStudent);
		 */

		// Create Query
//        List<student> students = session.createQuery("from student").list();
//        for (student s1 : students) {
//            System.out.println(s1);
//        }

		// Edit or modify the data (transaction commit)
//        Student sToUpdate = session.get(Student.class, 1);
//        sToUpdate.setName("Amritanshu");
//        sToUpdate.setAge(21);
//        session.saveOrUpdate(sToUpdate);

		// Delete (transaction commit)
//        Student sToDelete = session.get(Student.class, 1);
//        session.delete(sToDelete);

		tx.commit();
		session.close();
		sf.close();
	}
}
