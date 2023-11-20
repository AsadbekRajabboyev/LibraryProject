package uz.spring.mvc.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.spring.mvc.model.Book;
import uz.spring.mvc.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Book> index() {
        List<Book> book = new ArrayList<>();
        return jdbcTemplate.query("Select * from book", new BookMapper());
    }

    public Book show(int id) {
        return jdbcTemplate.query("Select * from book where book_id =?", new Object[]{id},
                        new BookMapper())
                .stream().findAny().orElse(null);
    }

    public void newBookAdd(Book book) {
        jdbcTemplate.update("Insert into book (book_name, book_author, date) values(?,?,?)",
                book.getBookName(), book.getBookAuthor(), book.getDate());
    }


    public void updateBook(int id, Book newBook) {
        jdbcTemplate.update("Update book set book_name=?,  book_author=?, date=? Where book_id=?",
                newBook.getBookName(), newBook.getBookAuthor(), newBook.getDate(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("Delete from book Where book_id =?", id);
    }

    public Optional<Person> getOwnerBook(int id) {
        return jdbcTemplate.query("SELECT * from book join person on book.person_id = person.id where book.book_id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }


    public void giveBook(int id, Person person) {
        jdbcTemplate.update("UPDATE book SET person_id = ? WHERE book_id = ?;", person.getId(), id);
    }

    public void setNull(int id) {
        jdbcTemplate.update("UPDATE book SET person_id = NULL;");
    }
}
