package com.douzone.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.CategoryRepository;
import com.douzone.mysite.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryVo insert(CategoryVo categoryVo) {
		
		return categoryRepository.insert(categoryVo);
	}
}