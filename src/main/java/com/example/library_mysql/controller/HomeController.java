package com.example.library_mysql.controller;

import com.example.library_mysql.domain.*;
import com.example.library_mysql.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Controller
public class HomeController {
    @RequestMapping({"/index","/home","/"})
    public String home() {
        return "index";
    }

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;
    @Autowired
    private PublishingCompanyService publishingCompanyService;
    @Autowired
    private ReaderService readerService;
    @Autowired
    private TagService tagService;

//    @RequestMapping("/search")
//    @ResponseBody
//    public Author showAuthor(Integer authorId) throws UnsupportedEncodingException {
//        Author author = authorService.selectAuthorById(authorId);
//        if(author!=null) {
//            String str = new String(author.toString().getBytes(),"GBK");
//            System.out.println("authorSearch "+authorId+" success.\n"+str);
//        } else {
//            System.out.println("authorSearch "+authorId+" error.");
//        }
//        return author;
//    }searchValue

    @RequestMapping("/search")
    @ResponseBody
    public List<Object> show(String searchValue) throws UnsupportedEncodingException {
        int id = parseInt(searchValue);
        List<Object> result = new ArrayList<>();
        Author author = authorService.selectAuthorById(id);
        Book book = bookService.selectBookById(id);
        PublishingCompany publishingCompany = publishingCompanyService.selectPublishingCompanyById(id);
        Reader reader = readerService.selectReaderById(id);
        Tag tag = tagService.selectTagById(id);

        result.add(author);
        result.add(book);
        result.add(publishingCompany);
        result.add(reader);
        result.add(tag);

        if(searchValue!=null) {
            String str = new String(result.toString().getBytes(),"GBK");
            System.out.println("searchValue "+searchValue+" success.\n"+str);
        } else {
            System.out.println("searchValue "+searchValue+" error.");
        }

        return result;
    }
}
