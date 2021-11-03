package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.SiteService;

@Controller
public class MainController {

	@Autowired
	ServletContext servletContext;

	@RequestMapping({ "" })
	public String index(Model model) {

		return "main/index";
	}

}