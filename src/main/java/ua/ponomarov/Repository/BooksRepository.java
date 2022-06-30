package ua.ponomarov.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ponomarov.Model.Book;

public interface BooksRepository extends JpaRepository<Book, Integer> {
}
