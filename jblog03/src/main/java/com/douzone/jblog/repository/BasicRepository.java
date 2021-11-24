package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BasicVo;

@Repository
public class BasicRepository {

	@Autowired
	private SqlSession sqlSession;

	public boolean insert(BasicVo basicVo) {
		int count = sqlSession.insert("basic.insert", basicVo);
		return count == 1;
	}	
	
	public BasicVo find(String id) {
		return sqlSession.selectOne("basic.find", id);
	}	
	
	public BasicVo update(BasicVo basicVo) {
		return sqlSession.selectOne("basic.update", basicVo);
	}


}