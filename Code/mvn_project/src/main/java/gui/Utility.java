package gui;

import javafx.scene.paint.Color;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class implementing useful static method
 * @author Bryan Curchod
 * @version 1.0
 */
public abstract class Utility {
	
	/**
	 * convert a Color object into a HEX string
	 * @param color Color that we want the HEX code
	 * @return HEX code (web format)
	 */
	static public String toRGBCode( Color color )
	{
		return String.format( "#%02X%02X%02X",
				(int)( color.getRed() * 255 ),
				(int)( color.getGreen() * 255 ),
				(int)( color.getBlue() * 255 ) );
	}
	
	/**
	 * check if a string can be parsed to a double
	 * @param str string to check
	 * @return true if the string can be parsed
	 */
	static public boolean isDouble(String str) {
		
		try {
			Double.valueOf(str);
			return true;
		}catch (NumberFormatException e){
			return false;
		}
	}
	
	/**
	 * Adjust the precision of a double value to
	 * a certain decimal number
	 * @param value double value to adjust
	 * @param nbDecimal number of decimal
	 * @return rounded
	 *
	 */
	static public double truncateDouble(double value, int nbDecimal){
		
		Double truncatedDouble = BigDecimal.valueOf(value)
				.setScale(nbDecimal, RoundingMode.DOWN)
				.doubleValue();
		
		return truncatedDouble;
	}
	
	/**
	 * define the text color based on the background color property
	 * @param color background color
	 * @return white or black, depending on the brightness of the color, in HEX web format
	 */
	public static String textColorBasedOnGB(Color color) {
		if (color.getBrightness() < 0.5){
			return toRGBCode(Color.WHITE);
		} else {
			return toRGBCode(Color.BLACK);
		}
	}
	
}
