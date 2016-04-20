/**  
* @Copyright (C) 
* @Title: ResultEntity.java 
* @author WenKe  
* @Package com.techmask.ressack.until 
* @Description: TODO(用一句话描述该文件做什么)  
* @date 2016年4月19日 下午8:53:59 
* @History 历史  
* <author>     <time>           <version>         <desc>
* 修改人        时间               版本            描叙    
*/
package com.techmask.ressack.core.util;

/** 
* @ClassName: ResultEntity 
* @Description(描叙):   用来返回信息给客户端的实体
* @author Wenke 
* @date 2016年4月19日 下午8:53:59  
*/
public class ResultEntity {
	
	
	private String code; // 状态码    0： 成功；  100 开始  客户端错误  ;200开始  服务端错误 
	private String message; // 信息
	private String apiAuthor; // 接口的作者
	private Object data;//  需要返回的数据
	
	
	public ResultEntity(String apiAuthor){
		this.apiAuthor  = apiAuthor;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getApiAuthor() {
		return apiAuthor;
	}
	public void setApiAuthor(String apiAuthor) {
		this.apiAuthor = apiAuthor;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	

}
