package com.douzone.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BasicService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BasicVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

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
	@RequestMapping(value = {"/{id}/{categoryNo}/{postNo}"})
	public String blogFirstMain(
			@PathVariable("id") String id,
			@PathVariable("categoryNo") Long categoryNo,
			@PathVariable("postNo") Long postNo,
			Model model) {
				
		BasicVo basicVo = new BasicVo();
		List<CategoryVo> categoryVo = null;
		List<PostVo> postVo = null;
		// 해당 최소 카테고리 중 가장 상단에 위치시켜야 할 것.
		PostVo postMain = new PostVo();
		
		basicVo = basicService.find(id);
		categoryVo = categoryService.findAll(id);
		
		// 메인 화면에서 들어올 때
		if(categoryNo == 0) {
			System.out.println(id);
			// 카테고리 최소값
			categoryNo = categoryService.findMin(id);
			System.out.println(categoryNo);

			// 하단 게시글에 나열해야 할 부분
			postVo = postService.findAllByCategoryNo(categoryNo);
			System.out.println(postVo);

		}
		
		// 우측하단 카테고리 창에서 들어올 때
		 else {
			postVo = postService.findAllByCategoryNo(categoryNo);
		
		}
		
		if(postNo == 0) {
			postMain = postService.findNewMainContents(categoryNo);
			postMain = postService.findMainContents(postMain);
		} else {
			postMain.setNo(postNo);
			postMain.setCategoryNo(categoryNo);
			postMain = postService.findMainContents(postMain);
		}
		

		model.addAttribute("basic", basicVo);
		model.addAttribute("category", categoryVo);
		model.addAttribute("post", postVo);
		model.addAttribute("postMain", postMain);

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
