package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.BasicService;
import com.douzone.mysite.vo.BasicVo;

@Controller
@RequestMapping("/blog/{id}/basic")
public class BasicController {
	private static final Log LOGGER = LogFactory.getLog(BasicController.class);

	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BasicService basicService;

	@RequestMapping("/update")	
	public String update(BasicVo basicVo, @RequestParam("file") MultipartFile file) {
		
		try {
			String profile = basicService.changeImage(file);
			basicVo.setLogo(profile);
		} catch (FileUploadException ex) {
			LOGGER.info("Admin Main Update:" + ex);
		}

		basicService.update(basicVo);
		servletContext.setAttribute("basic", basicVo);

		return "redirect:/blog/" + basicVo.getId() + "/admin/basic";
	}
}
