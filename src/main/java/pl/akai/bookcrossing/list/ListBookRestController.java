package pl.akai.bookcrossing.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.akai.bookcrossing.model.Book;
import pl.akai.bookcrossing.opinion.OpinionDao;

@Controller
public class ListBookRestController {

    private final BookDao bookDao;
    private final OpinionDao opinionDao;
    private BookInsertBean bookInsertBean;

    @Autowired
    public ListBookRestController(BookDao bookDao, OpinionDao opinionDao, BookInsertBean bookInsertBean) {
        this.bookDao = bookDao;
        this.opinionDao = opinionDao;
        this.bookInsertBean = bookInsertBean;
    }

    @GetMapping("/")
    public String list(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        model.addAttribute("name", currentPrincipalName);
        model.addAttribute("books", bookDao.findAllBooks());
        return "index";
    }

    @GetMapping("/book/add")
    public String bookForm(Model model) {
        model.addAttribute("book", new Book());
        return "form";
    }

    @PostMapping("/book/add")
    public String bookSubmit(@ModelAttribute Book book, Model model) {
        model.addAttribute("book", book);
        bookInsertBean.bookInsertion(book);
        return "formResult";
    }

    @GetMapping("/book/details")
    public String bookDetails(Model model, Integer bookId) {
        model.addAttribute("book", bookDao.findBookById(bookId));
        model.addAttribute("opinions", opinionDao.getOpinionsByBookId(bookId));
        return "details";
    }

}
