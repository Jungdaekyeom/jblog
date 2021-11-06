package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BasicVo;

@Repository
public class BasicRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public BasicVo find(String id) {
		return sqlSession.selectOne("basic.find", id);
	}	
	
	public BasicVo update(BasicVo basicVo) {
		return sqlSession.selectOne("basic.update", basicVo);
	}


}