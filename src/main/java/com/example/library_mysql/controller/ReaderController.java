package com.example.library_mysql.controller;

import com.example.library_mysql.domain.Reader;
import com.example.library_mysql.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/reader")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @RequestMapping("/searchReader")
    public String show() {
        return "searchReader";
    }

    @RequestMapping("/showReader")
    public String getReader(Integer readerId, Model model) throws UnsupportedEncodingException {
        Reader reader = readerService.selectReaderById(readerId);
        model.addAttribute("readers",reader);
        if(reader!=null) {
            String str = new String(reader.toString().getBytes(),"GBK");
            System.out.println("showReader "+readerId+" success.\n"+str);
            return "showReader";
        } else {
            System.out.println("showReader "+readerId+" error.\n");
            return "error";
        }
    }
}
