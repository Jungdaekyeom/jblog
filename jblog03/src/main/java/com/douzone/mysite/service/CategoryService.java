package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.CategoryRepository;
import com.douzone.mysite.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryVo> findAll(String id) {
		return categoryRepository.findAll(id);
	}
	
	public Long findMin(String id) {
		return categoryRepository.findMin(id);
	}
	
	public CategoryVo insert(CategoryVo categoryVo) {
		return categoryRepository.insert(categoryVo);
	}	
	
	public Long delete(Long no) {
		return categoryRepository.delete(no);
	}
}