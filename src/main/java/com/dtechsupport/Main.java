package com.dtechsupport;

import com.dtechsupport.books.Book;
import com.dtechsupport.students.Student;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Configuration config = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Book.class);
        ServiceRegistry reg = new ServiceRegistryBuilder()
                .applySettings(config.getProperties()).buildServiceRegistry();

        SessionFactory sf = config.buildSessionFactory(reg);
        Session session = sf.openSession();

        SQLQuery books = session.createSQLQuery("select * from book b where b.book_id IN(1,2,3,4)");
        books.addEntity(Book.class);
        List<Book> bookList = books.list();

        Student s = (Student) session.get(Student.class, 1L);
        System.out.println(bookList);
        s.addBooks(bookList);
        System.out.println(s);

        try {
            System.out.println("\nSaving the student");
            session.beginTransaction();
            session.save(s);
            session.getTransaction().commit();
            System.out.println("Done saving student");
        } finally {
            session.close();
        }
    }
}
