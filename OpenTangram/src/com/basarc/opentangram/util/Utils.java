package com.basarc.opentangram.util;

import java.math.BigDecimal;

public class Utils {

	private Utils() {
	}

	public static float cos(float theta) {
		float result = 0;
		float rad = (float) Math.toRadians(theta);
		result = (float) Math.cos(rad);
		return round(result);
	}

	public static float sin(float theta) {
		float result = 0;
		float rad = (float) Math.toRadians(theta);
		result = (float) Math.sin(rad);
		return round(result);
	}

	public static float sqrt(float value) {
		return (float) Math.sqrt(value);
	}

	public static float round(float value) {

		float roundedValue = Math.round(value);
		float fark = (float) Math.abs(value - roundedValue);

		BigDecimal bd = new BigDecimal(Float.toString(fark));
		bd = bd.setScale(5, BigDecimal.ROUND_HALF_EVEN);

		if (bd.floatValue() == 0)
			return roundedValue;
		else
			return value;
	}
}
