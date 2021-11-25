package com.douzone.jblog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.vo.CategoryVo;

@Controller("categoryApiController")
@RequestMapping("/category/api")
public class CategoryController {
	
	// 의존성 주입
	@Autowired
	private CategoryService categoryService;
	
	@ResponseBody
	@RequestMapping("/list")
	public JsonResult list(
		@RequestParam("id") String id) {
		List<CategoryVo> categoryVo = null;
		categoryVo = categoryService.findAll(id);
		return JsonResult.success(categoryVo);
	}
	
//	@ResponseBody
//	@RequestMapping("/delete/{no}")
//	public String delete(@PathVariable("no") Long no) {
//		
//		categoryService.delete(no);
//	
//		return JsonResult.success();
//	}
	
}