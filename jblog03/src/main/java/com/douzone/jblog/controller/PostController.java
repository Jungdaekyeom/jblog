package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/blog/{id}/admin/post")
public class PostController {

	@Autowired
	private PostService postService;

	@RequestMapping("/insert")
	public String insert(@PathVariable("id") String id,
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "category", required = true) Long categoryNo,
			@RequestParam(value = "contents", required = true) String contents) {		
		PostVo postVo = new PostVo();
		postVo.setTitle(title);
		postVo.setCategoryNo(categoryNo);
		postVo.setContents(contents);
		postService.insert(postVo);
		
		// 썼던 블로그를 바로 확인할 수 있게끔 도와줌
		// 내가 쓴 시점에서, 최신글이 되므로 0
		return "redirect:/blog/" + id + "/" + categoryNo + "/0";
	}

	@RequestMapping("/delete/{categoryNo}/{no}")
	public String delete(@PathVariable("id") String id,
			@PathVariable("categoryNo") Long categoryNo,
			@PathVariable("no") Long no) {
		System.out.println(no);
		System.out.println(id);
		System.out.println(categoryNo);
		
		// 여기서 에러
		postService.delete(no);

		return "redirect:/blog/" + id + "/" + categoryNo + "/0";
	}
}
