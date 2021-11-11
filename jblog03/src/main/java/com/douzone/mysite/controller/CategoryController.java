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
@RequestMapping("/blog/{id}/admin/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/insert")
	public String insert(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "desc", required = true) String desc,
			@RequestParam(value = "blogid", required = true) String blogid,
			@PathVariable("id") String id,
			Model model) {

		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName(name);
		categoryVo.setDescription(desc);
		categoryVo.setBlogId(blogid);
		categoryService.insert(categoryVo);

		return "redirect:/blog/" + id + "/admin/category";
	}

	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no, 
			@PathVariable(value = "id", required = true) String id) {
		
		System.out.println("삭제할 번호:" + no + "삭제하는 아이디:" + id);
		categoryService.delete(no);
		return "redirect:/blog/" + id + "/admin/category";
	}
}
