package com.example.library_mysql.controller;

import com.example.library_mysql.domain.Book;
import com.example.library_mysql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/searchBook")
    public String show() {
        return "searchBook";
    }

    @RequestMapping("/showBook")
    public String getBook(Integer bookId, Model model) throws UnsupportedEncodingException {
        Book book = bookService.selectBookById(bookId);
        model.addAttribute("books",book);
        if(book!=null) {
            String str = new String(book.toString().getBytes(),"GBK");
            System.out.println("showBook "+bookId+" success.\n"+str);
            return "showBook";
        } else {
            System.out.println("showBook "+bookId+" error.\n");
            return "error";
        }
    }
}
