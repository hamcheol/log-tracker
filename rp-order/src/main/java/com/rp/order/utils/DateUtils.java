package com.rp.order.utils;

import java.text.Format;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.FastDateFormat;

public class DateUtils {

	public static String date(String pattern) {
        Date today = new Date();
        Format fdf = FastDateFormat.getInstance(pattern, Locale.getDefault());
        return fdf.format(today);
    }
}
