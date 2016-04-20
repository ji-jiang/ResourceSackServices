/**  
* @Copyright (C) 
* @Title: ParamVerifiesUntil.java 
* @author WenKe  
* @Package com.techmask.ressack.until 
* @Description: TODO(用一句话描述该文件做什么)  
* @date 2016年4月19日 下午8:28:53 
* @History 历史  
* <author>     <time>           <version>         <desc>
* 修改人        时间               版本            描叙    
*/
package com.techmask.ressack.core.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

/** 
* @ClassName: ParamVerifiesUntil 
* @Description(描叙): 参数验证的服务
* @author Wenke 
* @date 2016年4月19日 下午8:28:53  
*/
@Service
public class ParamVerifiesUntil {
	/**
	* @MethodName: isMobileNO 
	* @Description(描叙): 手机号码规格验证
	* 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
　　* 联通：130、131、132、152、155、156、185、186
　　* 电信：133、153、180、189、（1349卫通）
	* @author Wenke 
	* @param @param mobiles
	* @param @return 
	* @return boolean
	* @throws
	* @date 2015年3月12日 上午10:37:44
	 */
	public  boolean isMobileNO(String mobiles) {
		Matcher mm =null;
		try {
			Pattern p = Pattern
					.compile("^((13[0-9])|(14[0-9])|(17[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
			mm = p.matcher(mobiles);
		} catch (Exception e) {
			e.toString();
		}
		return mm.matches();
	}
	
	/**
	* @Description(描叙):验证日期格式 
	* @author Wenke
	* @date 2015年4月2日 上午9:58:08
	* @param time 日期2019-12-21
	* @return true false
	 */
	public  boolean isCheckTime(String time) {
		Matcher mm = null;
		try {
			Pattern p = Pattern
					.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
			mm = p.matcher(time);
		} catch (Exception e) {
			e.toString();
		}
		return mm.matches();
	}

	
	
	
	/**
	* @MethodName: isQQNo 
	* @Description(描叙): qq号码验证
	* @author Wenke 
	* @param @param number
	* @param @return 
	* @return boolean
	* @throws
	* @date 2015年3月18日 上午11:19:16
	 */
	public  boolean isQQNo(String number) {
		Matcher mm =null;
		try {
			Pattern p = Pattern
					.compile("^[1-9][0-9]{2,13}");
			mm = p.matcher(number);
		} catch (Exception e) {
			e.toString();
		}
		return mm.matches();
	}
	
	/**
	* @MethodName: isEmail 
	* @Description(描叙): 邮箱验证
	* @author Wenke 
	* @param @param email
	* @param @return 
	* @return boolean
	* @throws
	* @date 2015年4月3日 下午5:00:13
	 */
	public  boolean isEmail(String email) {
		
		if(email == null || "".equals(email) || "null".equals(email)){
			return false;
		}else{
			Matcher m = null;
			try {
				String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
				Pattern p = Pattern.compile(str);
				m = p.matcher(email);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return m.matches();
		}
		
	}
	
	

	
	
	/**
	* @Description(描叙):  判断字符串是否为数字
	* @author WenKe
	* @date 2015年5月27日 上午11:10:46
	* @param str
	* @return
	*/
	public  boolean isNum(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
	

}
