package com.qq.common.tag;

import com.qq.common.constants.BookStatusEnum;

public class WriteBookStatusTag extends AbstractWriteSelectTagSupport{
	/**  */
	private static final long serialVersionUID = -2244293275532177208L;

	@Override
	public String setDisplayLabel() {
		return BookStatusEnum.getNameByValue(value);
	}

}
