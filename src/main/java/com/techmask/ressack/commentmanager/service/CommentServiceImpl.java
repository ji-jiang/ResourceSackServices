package com.techmask.ressack.commentmanager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmask.ressack.commentmanager.domain.Comment;
import com.techmask.ressack.commentmanager.repository.CommentRepository;
import com.techmask.ressack.core.error.AppException;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.core.utils.ValidateUtils;
import com.techmask.ressack.statisticsmanager.service.StatisticsService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private StatisticsService statisticsService;
	
	@Override
	public List<Comment> loadAllCommentByRefId(String refId) {
		List<Comment> comments = commentRepository.loadAllCommentByRefId(refId);
		List<Comment> resultComments = postProcessComments(comments);
		return resultComments;
	}

	@Override
	public void addComment(Map<String, Object> commentMap) {
		vlaidateAddComment(commentMap);
		commentRepository.addComment(commentMap);
		
		commentMap.put("changedCount", 1);
		commentMap.put("statisticsType", "C");
		statisticsService.updateStatistics(commentMap);
	}

	@Override
	public void deleteComment(Map<String, Object> commentMap) {
		commentRepository.deleteComment(commentMap);
		
		commentMap.put("changedCount", -1);
		commentMap.put("statisticsType", "C");
		statisticsService.updateStatistics(commentMap);
	}
	
	
	protected List<Comment> postProcessComments(List<Comment> comments){
		Map<String,Comment> commentMap = new HashMap<String,Comment>();
		List<Comment> resultComments = new ArrayList<Comment>();
		for(int i=0;i<comments.size();i++){
			Comment comment = comments.get(i);
			if(StringUtils.isBlank(comment.getParentId())){
				commentMap.put(comment.getId(), comment);
				resultComments.add(comment);
			}else{
				String parentId = comment.getParentId();
				if(commentMap.containsKey(parentId)){
					Comment parentComment = commentMap.get(parentId);
					if(parentComment.getChildComments() == null){
						parentComment.setChildComments(new ArrayList<Comment>());
					}
					List<Comment> childComments  = parentComment.getChildComments();
					childComments.add(comment);
				}
			}
			
		}
		return resultComments;
	}
	
	protected void vlaidateAddComment(Map<String, Object> commentMap){
		String userId = (String) commentMap.get("userId");
		if (StringUtils.isBlank(userId)) {
			throw new AppException(AppException.PERMISSION_DENIED_ERROR);
		}
		
		StringBuffer errorMsg = new StringBuffer();

		String comment = (String) commentMap.get("comment");
		ValidateUtils.validateField(errorMsg, "comment", comment, true, 200);
		
		if (errorMsg.length() > 0) {
			throw new ValidationException(errorMsg.toString());
		}
	}

}
