package org.example.userservice.repository;
import org.example.userservice.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepo extends CrudRepository<Book,Integer> {
    @Query(value = "SELECT books.* FROM book_formular \n" +
            "JOIN books ON book_formular.book_code=books.code \n" +
            "WHERE user_id= ?1 ", nativeQuery = true)
    List<Book> getBooksByUserId(@Param("user_id")int user_id);
}
