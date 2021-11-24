package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	public PostVo insert(PostVo postVo) {
		return postRepository.insert(postVo);
	}
	
	public int delete(Long no) {
		return postRepository.delete(no);
	}
	
	public List<PostVo> findAllByCategoryNo(Long categoryNo) {
		return postRepository.findAllByCategoryNo(categoryNo);
	}

	public PostVo findMainContents(PostVo postVo) {
		return postRepository.findMainContents(postVo);
	}

	public PostVo findNewMainContents(Long categoryNo) {
		return postRepository.findNewMainContents(categoryNo);
	}
}