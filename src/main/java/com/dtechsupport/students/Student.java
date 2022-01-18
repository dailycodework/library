package com.dtechsupport.students;

import com.dtechsupport.books.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Embedded
    private StudentName fullName;
    @NaturalId
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "book_readers", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    public Student(StudentName fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public void addBooks(List<Book> books) {
        if (this.books == null) {
            this.books = new ArrayList<>();
            this.books.addAll(books);
        }
        this.setBooks(books);
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName=" + fullName +
                ", email='" + email + '\'' +
                ", books=" + books +
                '}';
    }
}
