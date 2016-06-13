package com.techmask.ressack.commentmanager.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.techmask.ressack.commentmanager.domain.Comment;

@Mapper
public interface CommentRepository {
	List<Comment> loadAllCommentByRefId(String refId);
	public int addComment(Map<String,Object> commentMap);
	public int deleteComment(Map<String,Object> commentMap);
	public Comment loadLatestAddedComment(Map<String,Object> commentMap);
}
