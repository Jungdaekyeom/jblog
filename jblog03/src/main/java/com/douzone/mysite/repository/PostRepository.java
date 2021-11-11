package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.PostVo;

@Repository
public class PostRepository {

	@Autowired
	private SqlSession sqlSession;

	public PostVo insert(PostVo postVo) {
		return sqlSession.selectOne("post.insert", postVo);
	}
	
	public List<PostVo> findAllByCategoryNo(Long categoryNo){
		return sqlSession.selectList("post.findAllByCategoryNo", categoryNo);
	}

}