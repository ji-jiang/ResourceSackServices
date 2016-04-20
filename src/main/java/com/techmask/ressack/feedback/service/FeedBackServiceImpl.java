/**  
* @Copyright (C) 
* @Title: FeedBackServiceImpl.java 
* @author WenKe  
* @Package com.techmask.ressack.feedback.service 
* @Description: TODO(用一句话描述该文件做什么)  
* @date 2016年4月17日 下午9:23:58 
* @History 历史  
* <author>     <time>           <version>         <desc>
* 修改人        时间               版本            描叙    
*/
package com.techmask.ressack.feedback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmask.ressack.feedback.domain.FeedBack;
import com.techmask.ressack.feedback.repository.FeedBackRepository;

/** 
* @ClassName: FeedBackServiceImpl 
* @Description(描叙): 
* @author Wenke 
* @date 2016年4月17日 下午9:23:58  
*/
@Service
public class FeedBackServiceImpl implements FeedBackService {
	
	@Autowired
	private FeedBackRepository  feedBackRepository;

	/** 
	* @Title: loadAllFeedBack 
	* @anthor Wenke
	* @Description(描述这个方法) : TODO 
	* @return  
	* @throws 
	*/
	@Override
	public List<FeedBack> loadAllFeedBack() {
		
		return feedBackRepository.loadAllFeedBack();
	}

}
