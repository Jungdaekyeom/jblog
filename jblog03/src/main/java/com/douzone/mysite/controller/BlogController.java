package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@RequestMapping({ "/main" })
	public String blogMain(Model model) {

		return "blog/blog-main";
	}

	@RequestMapping({ "/basic" })
	public String blogAdminBasic(Model model) {

		return "blog/blog-admin-basic";
	}

	@RequestMapping({ "/category" })
	public String blogAdminCategory(Model model) {

		return "blog/blog-admin-category";
	}

	@RequestMapping({ "/write" })
	public String blogAdminWrite(Model model) {

		return "blog/blog-admin-write";
	}
}
