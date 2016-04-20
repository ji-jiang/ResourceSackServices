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

import com.techmask.ressack.core.util.ResultEntity;

/** 
* @ClassName: FeedBackService 
* @Description(描叙): 
* @author Wenke 
* @date 2016年4月17日 下午9:22:44  
*/
public interface FeedBackService {
	
	
	/** 
	* @MethodName: loadAllFeedBack 
	* @Description(描叙):    获取意见反馈
	* @author Wenke 
	* @param @return 
	* @return List<FeedBack>
	* @throws
	* @date 2016年4月19日 下午8:38:53  
	*/
	public void loadAllFeedBack(ResultEntity resultEntity );

	
	/** 
	* @MethodName: addFeedBack 
	* @Description(描叙):   添加 意见反馈
	* @author Wenke 
	* @param @param name
	* @param @param email
	* @param @param content
	* @param @param createBy
	* @param @param updateBy
	* @param @return 
	* @return FeedBack
	* @throws
	* @date 2016年4月19日 下午8:38:23  
	*/
	public void addFeedBack(String name,String email,String content,String createBy,String updateBy,ResultEntity resultEntity);
	
	
	
	/** 
	* @MethodName: updateFeedBack 
	* @Description(描叙):  根据id 修改意见反馈内容
	* @author Wenke 
	* @param @param feedBack
	* @param @return 
	* @return int
	* @throws
	* @date 2016年4月19日 下午8:39:20  
	*/
	public void updateFeedBack(String feedBackId ,String content,ResultEntity resultEntity);
	
	
	/** 
	* @MethodName: deleteFeedBack 
	* @Description(描叙):   删除 意见反馈
	* @author Wenke 
	* @param @param feedBackId
	* @param @return 
	* @return int
	* @throws
	* @date 2016年4月19日 下午8:47:53  
	*/
	public void deleteFeedBack(String feedBackId,ResultEntity resultEntity);

}
