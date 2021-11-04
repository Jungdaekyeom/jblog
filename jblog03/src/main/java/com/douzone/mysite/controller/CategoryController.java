package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.CategoryService;
import com.douzone.mysite.vo.CategoryVo;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping({ "/insert" })
	public String insert(
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "desc", required = true) String desc,
			@RequestParam(value = "blogId", required = true) String blogId,
			Model model) {
		
		CategoryVo categoryVo = new CategoryVo();
		
		categoryVo.setName(name);
		categoryVo.setDesc(desc);
		categoryVo.setBlogId(blogId);
		
		categoryService.insert(categoryVo);

		return "redirect:/blog/blog-admin-category";
	}

}
