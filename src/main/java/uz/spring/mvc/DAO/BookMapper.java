package uz.spring.mvc.DAO;

import org.springframework.jdbc.core.RowMapper;
import uz.spring.mvc.model.Book;
import uz.spring.mvc.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setBookId(resultSet.getInt("book_id"));
        book.setBookName(resultSet.getString("book_name"));
        book.setBookAuthor(resultSet.getString("book_author"));
        book.setDate(resultSet.getString("date"));
        return book;
    }
}
