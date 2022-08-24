package com.example.library_mysql.controller;

import com.example.library_mysql.domain.Author;
import com.example.library_mysql.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @RequestMapping("/searchAuthor")
    public String show() {
        return "searchAuthor";
    }

    @RequestMapping("/showAuthor")
    public String getAuthor(Integer authorId, Model model) throws UnsupportedEncodingException {
        Author author = authorService.selectAuthorById(authorId);
        model.addAttribute("authors",author);
        if(author!=null) {
            String str = new String(author.toString().getBytes(),"GBK");
            System.out.println("authorSearch "+authorId+" success.\n"+str);
            return "showAuthor";
        } else {
            System.out.println("authorSearch "+authorId+" error.");
            return "error";
        }
    }
}
