package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.PostService;
import com.douzone.mysite.vo.PostVo;

@Controller
@RequestMapping("/blog/{id}/admin/post")
public class PostController {

	@Autowired
	private PostService postService;

	@RequestMapping("/insert")
	public String insert(@PathVariable("id") String id,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "category", required = true) Long no,
			@RequestParam(value = "contents", required = true) String contents) {		
		PostVo postVo = new PostVo();
		postVo.setTitle(title);
		postVo.setCategoryNo(no);
		postVo.setContents(contents);
		postService.insert(postVo);

		return "redirect:/blog/" + id + "/" + no;
	}
}
