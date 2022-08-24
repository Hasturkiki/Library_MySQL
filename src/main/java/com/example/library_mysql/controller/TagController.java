package com.example.library_mysql.controller;

import com.example.library_mysql.domain.Tag;
import com.example.library_mysql.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping("/searchTag")
    public String show() {
        return "searchTag";
    }

    @RequestMapping("/showTag")
    public String getTag(Integer tagId, Model model) throws UnsupportedEncodingException {
        Tag tag = tagService.selectTagById(tagId);
        model.addAttribute("tags",tag);
        if(tag!=null) {
            String str = new String(tag.toString().getBytes(),"GBK");
            System.out.println("showTag "+tagId+" success.\n"+str);
            return "showTag";
        } else {
            System.out.println("showTag "+tagId+" error.\n");
            return "error";
        }
    }
}
