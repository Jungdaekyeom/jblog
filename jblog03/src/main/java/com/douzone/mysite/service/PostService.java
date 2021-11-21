package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.PostRepository;
import com.douzone.mysite.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	public PostVo insert(PostVo postVo) {
		return postRepository.insert(postVo);
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