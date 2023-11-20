package uz.spring.mvc.model;

public class Book {
    private int bookId;
    private String bookName;
    private String bookAuthor;
    private String date;
    private int personId;
    public Book(String bookName, String bookAuthor, String date,int personId) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.date = date;


    }

    public Book(int bookId, String bookName, String bookAuthor, String date,int personId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.date = date;
        this.personId = personId;
    }


    public Book() {
    }

    public Book(int personId) {
        this.personId = personId;
    }


    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
