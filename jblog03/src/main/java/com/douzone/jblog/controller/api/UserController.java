package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

// 1. join.jsp에서 넘어옴
@Controller("userApiController")
@RequestMapping("/user/api")
public class UserController {
	
	// 의존성 주입
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("/checkid")
	public JsonResult checkid(@RequestParam(value = "id", required = true, defaultValue = "") String id) {
		UserVo userVo = userService.getUser(id);
		return JsonResult.success(userVo != null);
		
	}
}
