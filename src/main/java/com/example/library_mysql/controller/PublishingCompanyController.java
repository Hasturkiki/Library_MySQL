package com.example.library_mysql.controller;

import com.example.library_mysql.domain.PublishingCompany;
import com.example.library_mysql.service.PublishingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/publishingCompany")
public class PublishingCompanyController {
    @Autowired
    private PublishingCompanyService publishingCompanyService;

    @RequestMapping("/searchPublishingCompany")
    public String show() {
        return "searchPublishingCompany";
    }

    @RequestMapping("/showPublishingCompany")
    public String getPublishingCompany(Integer publishingCompanyId, Model model) throws UnsupportedEncodingException {
        PublishingCompany publishingCompany = publishingCompanyService.selectPublishingCompanyById(publishingCompanyId);
        model.addAttribute("publishingCompanys",publishingCompany);
        if(publishingCompany!=null) {
            String str = new String(publishingCompany.toString().getBytes(),"GBK");
            System.out.println("showPublishingCompany "+publishingCompanyId+" success.\n"+str);
            return "showPublishingCompany";
        } else {
            System.out.println("showPublishingCompany "+publishingCompanyId+" error.\n");
            return "error";
        }
    }
}
