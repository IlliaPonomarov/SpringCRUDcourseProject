package ua.ponomarov.Model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int book_id;


    @Column(name = "name")
    @NotEmpty(message = "This field must not be empty")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "This field must not be empty")
    private String author;

    @Column(name = "year")
    @Positive
    private int year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "person_id",
            referencedColumnName = "person_id")
    private Person person;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_take_of_book")
    private Date dateTakeOfBook;



    public Book(){}

    public Book(int book_id, String name, String author, int year) {
        this.book_id = book_id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getDateTakeOfBook() {
        return dateTakeOfBook;
    }

    public void setDateTakeOfBook(Date dateTakeOfBook) {
        this.dateTakeOfBook = dateTakeOfBook;
    }
}
