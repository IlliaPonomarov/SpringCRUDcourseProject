package ua.ponomarov.Model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.*;

public class Person {


    private int id;

    @NotEmpty(message = "Your name should be have a this format: Illia Ponomarov Maximovich")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+",
            message = "Your should be entered your name in this format: Jhon Jackson Molack")
    private String fullName;

    @NotEmpty(message = "This field shouldn\'t be empty.")
    @Size(min = 10, max = 11)
    @Pattern(regexp = "[1-2][0-9][0-9][0-9]-[0-1][0-9]-[0-1][0-9]",
            message = "Your should be entered your your years of birth in this format: 1999-09-09")
    private String yearOfBirth;

    private List<Book> bookList = new ArrayList<>();



    public Person(int id, String fullName, String yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public Person(){}


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
