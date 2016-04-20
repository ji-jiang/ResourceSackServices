/**  
* @Copyright (C) 
* @Title: FeedBackService.java 
* @author WenKe  
* @Package com.techmask.ressack.feedback.service 
* @Description: TODO(用一句话描述该文件做什么)  
* @date 2016年4月17日 下午9:22:44 
* @History 历史  
* <author>     <time>           <version>         <desc>
* 修改人        时间               版本            描叙    
*/
package com.techmask.ressack.feedback.service;

import java.util.List;

import com.techmask.ressack.feedback.domain.FeedBack;

/** 
* @ClassName: FeedBackService 
* @Description(描叙): 
* @author Wenke 
* @date 2016年4月17日 下午9:22:44  
*/
public interface FeedBackService {
	
	
	public List<FeedBack> loadAllFeedBack();

}
