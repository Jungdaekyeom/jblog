package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public List<CategoryVo> findAll() {
		return sqlSession.selectList("category.findAll");
	}
	
	public CategoryVo insert(CategoryVo categoryVo) {
		return sqlSession.selectOne("category.insert", categoryVo);
	}	
	
	public Long delete(Long no) {
		return sqlSession.selectOne("category.delete", no);
	}
}