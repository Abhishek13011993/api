package com.ninza.hrm.api.genericutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		Random rand=new Random();
		int randnum=rand.nextInt(5000);
		return randnum;
	}
	public String getSystemDateYYYYMMDD() {
		Date da=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(da);
		return date;
	}
	public String getRequiredDateYYYTMMDD(int days) {
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqdate=sim.format(cal.getTime());
		return reqdate;
}
}