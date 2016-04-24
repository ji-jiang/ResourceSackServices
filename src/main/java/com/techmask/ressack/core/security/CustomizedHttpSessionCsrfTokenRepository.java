package com.techmask.ressack.core.security;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.util.Assert;

public class CustomizedHttpSessionCsrfTokenRepository implements CsrfTokenRepository {
	private static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";

	private static final String DEFAULT_CSRF_HEADER_NAME = "X-CSRF-TOKEN";

	private static final String DEFAULT_CSRF_TOKEN_ATTR_NAME = HttpSessionCsrfTokenRepository.class
			.getName().concat(".CSRF_TOKEN");

	private String parameterName = DEFAULT_CSRF_PARAMETER_NAME;

	private String headerName = DEFAULT_CSRF_HEADER_NAME;

	private String sessionAttributeName = DEFAULT_CSRF_TOKEN_ATTR_NAME;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.csrf.CsrfTokenRepository#saveToken(org.springframework
	 * .security.web.csrf.CsrfToken, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public void saveToken(CsrfToken token, HttpServletRequest request,
			HttpServletResponse response) {
		
		if (token == null) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.removeAttribute(sessionAttributeName);
			}
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute(sessionAttributeName, token);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.csrf.CsrfTokenRepository#loadToken(javax.servlet
	 * .http.HttpServletRequest)
	 */
	public CsrfToken loadToken(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		//TODO check to logics
		if (session == null ) {
			if(request.getHeader(headerName)!=null){
				return new DefaultCsrfToken(headerName, parameterName, request.getHeader(headerName));
			}else{
				return null;
			}
			
		}
		return (CsrfToken) session.getAttribute(sessionAttributeName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.csrf.CsrfTokenRepository#generateToken(javax.servlet
	 * .http.HttpServletRequest)
	 */
	public CsrfToken generateToken(HttpServletRequest request) {
		return new DefaultCsrfToken(headerName, parameterName, createNewToken());
	}

	/**
	 * Sets the {@link HttpServletRequest} parameter name that the {@link CsrfToken} is
	 * expected to appear on
	 * @param parameterName the new parameter name to use
	 */
	public void setParameterName(String parameterName) {
		Assert.hasLength(parameterName, "parameterName cannot be null or empty");
		this.parameterName = parameterName;
	}

	/**
	 * Sets the header name that the {@link CsrfToken} is expected to appear on and the
	 * header that the response will contain the {@link CsrfToken}.
	 *
	 * @param headerName the new header name to use
	 */
	public void setHeaderName(String headerName) {
		Assert.hasLength(headerName, "headerName cannot be null or empty");
		this.headerName = headerName;
	}

	/**
	 * Sets the {@link HttpSession} attribute name that the {@link CsrfToken} is stored in
	 * @param sessionAttributeName the new attribute name to use
	 */
	public void setSessionAttributeName(String sessionAttributeName) {
		Assert.hasLength(sessionAttributeName,
				"sessionAttributename cannot be null or empty");
		this.sessionAttributeName = sessionAttributeName;
	}

	private String createNewToken() {
		return UUID.randomUUID().toString();
	}
}

