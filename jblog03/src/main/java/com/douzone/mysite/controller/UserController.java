package com.douzone.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.CategoryService;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.CategoryVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	// 요청 파라미터를 객채에 담을 때 사용
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}

	// @Valid : 검증하는 것
	// 바인딩하면서 바로 발리데이션 함
	// BindingResult result : 바인딩하다가 조건이 맞지 않으면, 에러를 출력해줌.
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		// 바인딩->검증해서 에러가 발생되지 않으면, 서비스 소환!
		userService.join(vo);
		// 계정 생성 시, 카테고리의 미분류 항목이 바로 만들어질 수 있도록!
		return "redirect:/user/joinsuccess";
		
		
		// 회원가입 시
		// 1. id / title / logo
		// 2. 1번의 id를 기반으로 미분류 항목 카테고리를 제작
		// 3. 
	}
	
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		UserVo userVo = userService.getUser(authUser.getId());
		model.addAttribute("userVo", userVo);
		return "user/update";
	}	

	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo userVo) {
		userVo.setId(authUser.getId());

		userService.updateUser(userVo);
		authUser.setName(userVo.getName());
		
		return "redirect:/user/update";
	}	
}