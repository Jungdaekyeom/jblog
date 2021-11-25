package com.douzone.jblog.service;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.controller.BasicController;
import com.douzone.jblog.repository.BasicRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.BasicVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	private static final Log LOGGER = LogFactory.getLog(BasicController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BasicRepository basicRepository;
	
	@Autowired
	private BasicService basicService;

	// 데이터베이스의 상태를 변경하는 작업 또는 한번에 수행되어야 하는 연산들을 의미한다.
	// begin, commit 을 자동으로 수행해준다.
	// 예외 발생 시 rollback 처리를 자동으로 수행해준다.
	@Transactional(rollbackFor = Exception.class) 
	public void join(UserVo vo) {
		
		// 회원 만들기
		userRepository.insert(vo);
		
		// 블로그 만들기
		BasicVo basicVo = new BasicVo();
		basicVo.setId(vo.getId());
		basicVo.setTitle(vo.getId() + "의 블로그");
		// basicVo.setLogo(profile);
		basicRepository.insert(basicVo);
		
		// 미분류 카테고리 만들기
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName("미분류");
		categoryVo.setDescription("미분류 항목");
		categoryVo.setBlogId(vo.getId());
		categoryRepository.insert(categoryVo);
	}

	public UserVo getUser(String id) {
		return userRepository.findById(id);
	}

	public UserVo getUser(Long no) {
		return userRepository.findByNo(no);
	}

	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}

	public void updateUser(UserVo userVo) {
		userRepository.update(userVo);
	}

}