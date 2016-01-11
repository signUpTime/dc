package com.qq.common.tag;
import java.text.ParseException;import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.util.StringUtils;

public class WriteFormatDateTag extends TagSupport {

	private static final long serialVersionUID = 1053385290997524299L;
	private static final String DATE_FORMATTER_STR = "EEE MMM dd HH:mm:ss Z yyyy";
	private static final String TIMESTAMP_FORMATTER_STR = "yyyy-MM-dd hh:mm:ss";
	private static final String[] FORMATTER_STRS = {DATE_FORMATTER_STR,TIMESTAMP_FORMATTER_STR};
	
	private String value;
	private String format;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		if (StringUtils.isEmpty(value) || StringUtils.isEmpty(format)) {
			return SKIP_BODY;
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			out.print(dateFormat.format(getFormatDateObj(value)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
	private static Date getFormatDateObj(String value) {
		SimpleDateFormat format = null;
		Date d = null;
		int count = -1;
		for (String fStr : FORMATTER_STRS) {
			if(DATE_FORMATTER_STR.equals(fStr)){
				format = new SimpleDateFormat(fStr,Locale.ENGLISH);
			} else {
				format = new SimpleDateFormat(fStr);
			}
			try {
				d = format.parse(value);
				if(d != null) {
					return d;
				}
			} catch (ParseException e) {
				count++;
				if(count == FORMATTER_STRS.length-1) {
					throw new RuntimeException(e.getMessage());
				} else {
					continue;
				}
			}
		}
		return d;
	}
}
