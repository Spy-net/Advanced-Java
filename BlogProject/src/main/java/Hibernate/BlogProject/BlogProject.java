package Hibernate.BlogProject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BlogProject {

    private static final SessionFactory sessionFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    public static void main(String[] args) {
        System.out.println("------------------ Hello Blogger ------------------");

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create a new blog post");
            System.out.println("2. Update an existing blog post");
            System.out.println("3. Delete a blog post");
            System.out.println("4. Select all blog posts");
            System.out.println("0. Exit");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    createBlogPost(scanner);
                    break;
                case 2:
                    updateBlogPost(scanner);
                    break;
                case 3:
                    deleteBlogPost(scanner);
                    break;
                case 4:
                    selectAllBlogPosts();
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        } while (option != 0);

        sessionFactory.close();
        scanner.close();
    }

    private static void createBlogPost(Scanner scanner) {
        System.out.println("\nEnter username:");
        String username = scanner.nextLine();

        System.out.println("Enter title:");
        String title = scanner.nextLine();

        System.out.println("Enter content:");
        String content = scanner.nextLine();

        BlogEntity post = new BlogEntity(username, title, content, new Date());

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(post);
        session.getTransaction().commit();

        System.out.println("Blog post created successfully!");
    }

    private static void updateBlogPost(Scanner scanner) {
        System.out.println("\nEnter BlogID of the blog post to update:");
        int blogId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        BlogEntity post = session.get(BlogEntity.class, blogId);

        if (post != null) {
            System.out.println("Enter new content:");
            String newContent = scanner.nextLine();
            post.setContent(newContent);
            session.getTransaction().commit();
            System.out.println("Blog post updated successfully!");
        } else {
            System.out.println("Blog post with BlogID " + blogId + " not found.");
        }
    }

    private static void deleteBlogPost(Scanner scanner) {
        System.out.println("\nEnter BlogID of the blog post to delete:");
        int blogId = scanner.nextInt();
        scanner.nextLine();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        BlogEntity post = session.get(BlogEntity.class, blogId);

        if (post != null) {
            session.delete(post);
            session.getTransaction().commit();
            System.out.println("Blog post deleted successfully!");
        } else {
            System.out.println("Blog post with BlogID " + blogId + " not found.");
        }
    }

    private static void selectAllBlogPosts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<BlogEntity> posts = session.createQuery("FROM BlogEntity", BlogEntity.class).getResultList();
        session.getTransaction().commit();
        session.close(); // Close the session

        System.out.println("--------------------------------- All blog posts ---------------------------------");
        for (BlogEntity post : posts) {
            System.out.println("Post ID: " + post.getBlogId());
            System.out.println("Username: " + post.getUsername());
            System.out.println("Title: " + post.getTitle());
            System.out.println("Content: " + post.getContent());
            System.out.println("Timestamp: " + post.getTimestamp());
            System.out.println("---------------------------------");
        }
    }
}
