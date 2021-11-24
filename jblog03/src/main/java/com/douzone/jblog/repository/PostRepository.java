package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {

	@Autowired
	private SqlSession sqlSession;

	public PostVo insert(PostVo postVo) {
		return sqlSession.selectOne("post.insert", postVo);
	}

	public int delete(Long no) {
		return sqlSession.delete("post.delete", no);
	}

	public int deleteAllCategoryNo(Long no) {
		return sqlSession.delete("post.deleteAllCategoryNo", no);
	}
	
	public List<PostVo> findAllByCategoryNo(Long categoryNo){
		return sqlSession.selectList("post.findAllByCategoryNo", categoryNo);
	}
	
	public PostVo findMainContents(PostVo postVo) {
		return sqlSession.selectOne("post.findMainContents", postVo);
	}
	
	public PostVo findNewMainContents(Long categoryNo) {
		return sqlSession.selectOne("post.findNewMainContents", categoryNo);
	}
	

}