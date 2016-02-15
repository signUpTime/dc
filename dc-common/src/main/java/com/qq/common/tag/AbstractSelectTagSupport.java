package com.qq.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.util.StringUtils;

/**
 * 自定义select标签抽象类，提供select标签的公共属性，并提供一个抽象方法，供</br>
 * 继承它的类实现，为select标签添加自定义的option子节点
 * 
 * @author danyulong
 * @time 2015年11月18日 下午2:32:00
 */
public abstract class AbstractSelectTagSupport extends TagSupport{
	/**  */
	private static final long serialVersionUID = -2920053780144094442L;
	
	protected String firstLabelValue = null; // 第一行数据
	protected String firstLabelDisplay = null; // 第一行显示
	protected String styleClass = null; // 样式
	protected String selectedValue = null; // 默认选中值
	protected String name = null; // name
	protected String id = null; // id
	protected String onchangeFunc = null; // onchange事件
	
	public String getFirstLabelValue() {
		return firstLabelValue;
	}

	public void setFirstLabelValue(String firstLabelValue) {
		this.firstLabelValue = firstLabelValue;
	}

	public String getFirstLabelDisplay() {
		return firstLabelDisplay;
	}

	public void setFirstLabelDisplay(String firstLabelDisplay) {
		this.firstLabelDisplay = firstLabelDisplay;
	}

	public String getOnchangeFunc() {
		return onchangeFunc;
	}

	public void setOnchangeFunc(String onchangeFunc) {
		this.onchangeFunc = onchangeFunc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	
	
	@Override
	public int doStartTag() throws JspException {
		if(StringUtils.isEmpty(styleClass)){
			styleClass="select";
		}
		JspWriter out = pageContext.getOut();
		StringBuffer sb = new StringBuffer("<select id=\"" + id + "\" name=\"" + name + "\" class=\""
				+ styleClass + "\" onchange=\"" + onchangeFunc + "\">");
		if (!StringUtils.isEmpty(firstLabelValue) && !StringUtils.isEmpty(firstLabelDisplay)) {
			if (!StringUtils.isEmpty(selectedValue) && selectedValue.equals(firstLabelValue)) {
				sb.append("<option value=\"" + firstLabelValue + "\" selected=\"selected\">").append(firstLabelDisplay)
						.append("</option>");
			} else {
				sb.append("<option value=\"" + firstLabelValue + "\">").append(firstLabelDisplay).append("</option>");
			}
		}
		
		sb.append(setOptions());
		sb.append("</select>");
		try {
			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return SKIP_BODY;
	}
	
	/**
	 * 为自定义select标签设置option子节点
	 * 
	 * @return 返回"&lt;option&gt;&lt;/option&gt;&lt;option&gt;&lt;/option&gt;"形式的字符串
	 */
	public abstract String setOptions();
}
