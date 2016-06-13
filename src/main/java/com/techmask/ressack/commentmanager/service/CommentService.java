package com.techmask.ressack.commentmanager.service;

import java.util.List;
import java.util.Map;

import com.techmask.ressack.commentmanager.domain.Comment;


public interface CommentService {
	List<Comment> loadAllCommentByRefId(String refId);
	public Comment addComment(Map<String,Object> commentMap);
	public void deleteComment(Map<String,Object> commentMap);
}
