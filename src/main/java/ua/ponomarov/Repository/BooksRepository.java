package ua.ponomarov.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ponomarov.Model.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
