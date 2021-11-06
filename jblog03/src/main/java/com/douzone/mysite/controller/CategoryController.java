package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.CategoryService;
import com.douzone.mysite.vo.CategoryVo;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/insert")
	public String insert(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "desc", required = true) String desc,
			@RequestParam(value = "blogid", required = true) String blogid, Model model) {

		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName(name);
		categoryVo.setDescription(desc);
		categoryVo.setBlogId(blogid);
		System.out.println(categoryVo);

		categoryService.insert(categoryVo);

		return "redirect:/blog/category";
	}

	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		System.out.println("삭제할 번호:" + no);
		categoryService.delete(no);
		return "redirect:/blog/category";
	}
}
