/**
 *
 */
package fr.eni.javaee.encheres.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author amorf
 *
 */
public class OutilDate {

	private static final SimpleDateFormat LE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	public static String getStringFormatDate(Date uneDate) {

		SimpleDateFormat leFormat = LE_FORMAT;
		return leFormat.format(uneDate);
	}

	public static Date getDateFormatDate(String dateStr) {

		SimpleDateFormat formatter = LE_FORMAT;

		Date date = null;
		try {
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;
	}
}
