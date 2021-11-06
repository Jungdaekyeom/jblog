package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BasicService;
import com.douzone.mysite.service.CategoryService;
import com.douzone.mysite.vo.BasicVo;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BasicService basicService;

	@RequestMapping( {"","/main/{id}"} )
	public String blogMain(
			@PathVariable("id") String id,
			Model model) {
		BasicVo basicVo = new BasicVo();
		basicVo = basicService.find(id);
		model.addAttribute("basic", basicVo);
		return "blog/blog-main";
	}

	@RequestMapping( "/basic/{id}" )
	public String blogAdminBasic(
			@PathVariable("id") String id,
			Model model) {
		BasicVo basicVo = new BasicVo();
		basicVo = basicService.find(id);
		model.addAttribute("basic", basicVo);
		return "blog/blog-admin-basic";
	}

	@RequestMapping( "/category" )
	public String blogAdminCategory(Model model) {
		model.addAttribute("list", categoryService.findAll());
		System.out.println(categoryService.findAll());
		return "blog/blog-admin-category";
	}

	@RequestMapping( "/write" )
	public String blogAdminWrite(Model model) {
		return "blog/blog-admin-write";
	}
	
	
}
