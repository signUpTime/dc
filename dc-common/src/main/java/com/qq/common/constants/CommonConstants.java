package com.qq.common.constants;

public class CommonConstants {
	public static final String DATE_FORMART_CH="yyyy年MM月dd日  HH:mm";
	public static final String DATE_FORMART_SPACES="yyyy-MM-dd HH:mm";
	public static final String DATE_FORMART_HOUR="HH:mm";
	public static final String DATE_FORMART_DATE_CH="yyyy年MM月dd日";
	public static final String DATE_FORMART_DATE_SPACES="yyyy-MM-dd";
	public static final String DATE_FORMART_MONTH_SPACES="yyyy-MM";
	public static final String DATE_FORMART_MONTH_CH="yyyy年MM月";
	public static final String DATE_FORMART_DATE_SPRIT="yyyy/MM/dd";
	public static final String DATE_FORMART_DAY="MM月dd日";
	public static final String DATE_FORMART_DAY_HOUR="MM月dd日 HH:mm";
	public static final String DATE_FORMART_DAY_ONLY="dd日";
	public static final String DATE_FORMART_TOTAL="yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMART_DATE_NUM="yyyyMMdd";
	/** yyyyMMddHHmmss */ 
	public static final String DATE_FORMAT_NUMBER="yyyyMMddHHmmss";
//	public static final int PICTURE_TYPE_LEFT_PHOTE=0;
//	public static final int PICTURE_TYPE_SOUND=1;
//	public static final int PICTURE_TYPE_ICON=2;
//	public static final int PICTURE_TYPE_VIDEO=3;
//	
//	public static final String PICTURE_SIZE_SMALL="s";
//	public static final String PICTURE_SIZE_MIDDLE="m";
//	public static final String PICTURE_SIZE_BIG="b";
	/** 00:00:00 */
	public static final String START_DAY_TIME = " 00:00:00";

	/** 23:59:59 */
	public static final String END_DAY_TIME = " 23:59:59";
	public static final String END_ORDER_FOOD_TIME = "END_ORDER_FOOD_TIME";
	
	public static final int PIC_SIZE_SAMLLIMAGE = 0;
	public static final int PIC_SIZE_BIGIMAGE = 1;

	public static final int PIC_TYPE_TEACHERIMAGE = 0;
	public static final int PIC_TYPE_THIRDIMAGE = 1;
	public static final int PIC_TYPE_HEADIMAGE = 2;
	
	public static final int PAGING_SIZE = 20;
	
	
	//设置分层比例
	public static final String ASSISTANT_OPERATION_TYPE_SETRATE="SETRATE";
	
	//order_course_operation_log
	public static final int ORDER_COURSE_LOG_TYPE_TEACHER_LATE=2;//老师迟到
	public static final int ORDER_COURSE_LOG_TYPE_BAD_APPRAISE=3;//差评课程
	public static final int ORDER_COURSE_LOG_TYPE_DELETE_COURSE=4;//后台删除课程
	public static final int ORDER_COURSE_LOG_TYPE_DELETE_COURSE_TA=7;//TA端同意删除课程
	public static final int ORDER_COURSE_LOG_TYPE_FREEZE_COURSE=5;//课程冻结
	public static final int ORDER_COURSE_LOG_TYPE_GOOD_APPRAISE =6;//好评
	public static final String UTF8 = "UTF-8";
	public static final int connectionTimeout = 5000;
	
	public static final int EXPORT_MAX_LENGTH = 5000;
	
	public static final String USER_NAME = "Name";
	public static final String USER_ACCOUNT_NAME = "sAMAccountName";
	public static final String USER_MAIL = "Mail";
	public static final String USER_DEPARTMENT = "Department";
	public static final String LOGINED_USER = "loginUser";
}
