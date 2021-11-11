package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.mysite.service.BasicService;
import com.douzone.mysite.service.CategoryService;
import com.douzone.mysite.service.PostService;
import com.douzone.mysite.vo.BasicVo;
import com.douzone.mysite.vo.CategoryVo;
import com.douzone.mysite.vo.PostVo;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BasicService basicService;

	@Autowired
	private PostService postService;
	
	// 블로그 메인을 띄우는 함수
	@RequestMapping("/{id}/{no}")
	public String blogMain(
			@PathVariable("id") String id,
			@PathVariable("no") Long no,
			Model model) {
		
		BasicVo basicVo = new BasicVo();
		List<CategoryVo> categoryVo = null;
		List<PostVo> postVo = null;
		
		basicVo = basicService.find(id);
		categoryVo = categoryService.findAll(id);
		postVo = postService.findAllByCategoryNo(no);
		
		model.addAttribute("basic", basicVo);
		model.addAttribute("category", categoryVo);
		model.addAttribute("post", postVo);
		
		return "blog/blog-main";
	}

	@RequestMapping( "/{id}/admin/basic" )
	public String blogAdminBasic(
			@PathVariable("id") String id,
			Model model) {
		
		BasicVo basicVo = new BasicVo();
		basicVo = basicService.find(id);
		model.addAttribute("basic", basicVo);
		return "blog/blog-admin-basic";
	}

	@RequestMapping( "/{id}/admin/category" )
	public String blogAdminCategory(@PathVariable("id") String id,
			Model model) {
		
		BasicVo basicVo = new BasicVo();
		List<CategoryVo> categoryVo = null;
		basicVo = basicService.find(id);
		categoryVo = categoryService.findAll(id);
		model.addAttribute("basic", basicVo);
		model.addAttribute("category", categoryVo);

		return "blog/blog-admin-category";
	}

	@RequestMapping( "/{id}/admin/write" )
	public String blogAdminWrite(@PathVariable("id") String id,
			Model model) {
		
		BasicVo basicVo = new BasicVo();
		basicVo = basicService.find(id);
		model.addAttribute("basic", basicVo);
		model.addAttribute("category", categoryService.findAll(id));

		return "blog/blog-admin-write";
	}
	
}
