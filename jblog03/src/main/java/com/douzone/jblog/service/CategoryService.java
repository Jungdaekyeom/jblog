package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PostRepository postRepository;

	public List<CategoryVo> findAll(String id) {
		return categoryRepository.findAll(id);
	}
	
	public Long findMin(String id) {
		return categoryRepository.findMin(id);
	}
	
	public CategoryVo insert(CategoryVo categoryVo) {
		return categoryRepository.insert(categoryVo);
	}	
	
	@Transactional(rollbackFor = Exception.class) 
	public int delete(Long no) {
		postRepository.deleteAllCategoryNo(no);
		return categoryRepository.delete(no);
	}
}