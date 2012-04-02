package com.basarc.opentangram.util;

import java.math.BigDecimal;

public class Utils {

	private static final int decimalRound = 6;

	private Utils() {
	}

	public static double cos(double theta) {
		double result = 0;
		double rad = Math.toRadians(theta);
		result = Math.cos(rad);
		return round(result);
	}

	public static double sin(double theta) {
		double result = 0;
		double rad = Math.toRadians(theta);
		result = Math.sin(rad);
		return round(result);
	}

	public static double sqrt(double value) {
		return round(Math.sqrt(value));
	}

	public static double round(double value) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(decimalRound, BigDecimal.ROUND_HALF_DOWN);
		return bd.doubleValue();
	}
}
