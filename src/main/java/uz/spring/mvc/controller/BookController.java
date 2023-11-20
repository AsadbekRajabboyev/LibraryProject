package uz.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.spring.mvc.DAO.BookDAO;
import uz.spring.mvc.DAO.PersonDAO;
import uz.spring.mvc.model.Book;
import uz.spring.mvc.model.Person;

import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("kitob",bookDAO.index());
        return "book/index";
    }
    @GetMapping("/new")
    public String newBook(Model model){
        model.addAttribute("kitob", new Book());
        return "book/new";
    }
    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model,@ModelAttribute("person")Person person){
        model.addAttribute("kitob", bookDAO.show(id));

        Optional<Person> ownerBook = bookDAO.getOwnerBook(id);
        if (ownerBook.isPresent()){
            model.addAttribute("owner",ownerBook.get());
        }else{
            model.addAttribute("people",personDAO.index());
        }
        return "book/show";
    }
    @PostMapping
    public String createBook(@ModelAttribute("book") Book book){
        bookDAO.newBookAdd(book);
        return "redirect:/book";
    }
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("kitob", bookDAO.show(id));
        return "book/edit";
    }
    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book, @PathVariable("id")int id){
        bookDAO.updateBook(id, book);
    return "redirect:/book";
    }
    @DeleteMapping("{id}/delete")
    public String deletebook( @PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/book";
    }
    @PatchMapping("{id}/giveBook")
    public String giveBook(@ModelAttribute("person")Person person,@PathVariable("id")int id){
        bookDAO.giveBook(id,person);
        return "redirect:/book";
    }
    @PatchMapping("{id}/setNull")
    public String setNull(@PathVariable("id") int id){
        bookDAO.setNull(id);
        return "redirect:/book/{id}";
    }
}
