package com.common.util.date;

import java.util.Date;

public interface DateReset {

	/** {@link DateUtil#resetTimeStamp(Date)} */
	Date resetTimeStamp(Date date);
	
	/** {@link DateUtil#lastestTimeStamp(Date)} */
	Date lastestTimeStamp(Date date);
}
