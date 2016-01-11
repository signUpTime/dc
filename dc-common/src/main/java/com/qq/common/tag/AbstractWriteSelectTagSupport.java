package com.qq.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.util.StringUtils;

/**
 * 自定义select标签的显示
 * 
 * @author danyulong
 * @time 2015年11月18日 下午3:19:28
 */
public abstract class AbstractWriteSelectTagSupport extends TagSupport{
	/**  */
	private static final long serialVersionUID = 5605348105147416524L;
	/** 充值状态 */
	protected Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		if (StringUtils.isEmpty(value)) {
			return SKIP_BODY;
		}
		try {
			out.print(setDisplayLabel());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	/**
	 * 设置自定义select的显示字符串
	 * @return
	 */
	public abstract String setDisplayLabel();
		
}
