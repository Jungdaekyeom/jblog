package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public List<CategoryVo> findAll(String id) {
		return sqlSession.selectList("category.findAll", id);
	}
	
	public Long findMin(String id) {
		return sqlSession.selectOne("category.findMin", id);
	}
	
	public CategoryVo insert(CategoryVo categoryVo) {
		return sqlSession.selectOne("category.insert", categoryVo);
	}	
	
	public int delete(Long no) {
		return sqlSession.delete("category.delete", no);
	}
}