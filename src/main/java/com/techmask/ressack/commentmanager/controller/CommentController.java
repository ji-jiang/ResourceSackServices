package com.techmask.ressack.commentmanager.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmask.ressack.commentmanager.domain.Comment;
import com.techmask.ressack.commentmanager.service.CommentService;
import com.techmask.ressack.core.controller.BaseController;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.core.session.UserSession;
import com.techmask.ressack.core.session.UserSessionManager;

@RestController
@Configuration
@RequestMapping("/comment")
public class CommentController extends BaseController {
	@Autowired
	private CommentService commentService;

	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> addComment(HttpServletRequest request, @RequestBody Map<String, Object> commentMap) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {

			UserSession userSession = UserSessionManager.getInstance().getUserSession(request);
			commentMap.put("userId", userSession.getUserId());

			commentService.addComment(commentMap);

			resultMap.put(RESULT_CODE, "SUCCESS");

		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{commentId}")
	public Map<String, Object> deleteComment(HttpServletRequest request, @PathVariable("commentId") String commentId) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Map<String, Object> requestMap = new LinkedHashMap<String, Object>();
		try {
			requestMap.put("commentId", commentId);

			UserSession userSession = UserSessionManager.getInstance().getUserSession(request);
			requestMap.put("userId", userSession.getUserId());

			commentService.deleteComment(requestMap);

			resultMap.put(RESULT_CODE, "SUCCESS");

		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{refId}")
	public Map<String, Object> loadAllCommentByUser(HttpServletRequest request, @PathVariable("refId") String refId) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Map<String, Object> requestMap = new LinkedHashMap<String, Object>();


		try {

			List<Comment> comments = commentService.loadAllCommentByRefId(refId);
			resultMap.put("comments", comments);

		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;

	}
}
