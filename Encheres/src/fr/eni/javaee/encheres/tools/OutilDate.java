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
 * classe utilitaire pour les dates
 *
 */
public class OutilDate {

	private static final SimpleDateFormat LE_FORMAT_DATE = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat LE_FORMAT_DATE_HEURE = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	private static final SimpleDateFormat LE_FORMAT_DATE_HTML = new SimpleDateFormat("yyyy-MM-dd");


	public static String getStringFormatDate(Date uneDate) {

		SimpleDateFormat leFormat = LE_FORMAT_DATE;
		return leFormat.format(uneDate);
	}

	public static Date getDateFormatDate(String dateStr) {

		SimpleDateFormat formatter = LE_FORMAT_DATE;

		Date date = null;
		try {
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;
	}

	public static String getStringFormatDateHeure(Date uneDate) {

		SimpleDateFormat leFormat = LE_FORMAT_DATE_HEURE;
		return leFormat.format(uneDate);
	}

	public static Date getDateFormatDateHeure(String dateStr) {

		SimpleDateFormat formatter = LE_FORMAT_DATE_HEURE;

		Date date = null;
		try {
			date = formatter.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;
	}
	
	public static Date getDateFromHtml(String dateStr) {

		SimpleDateFormat formatter = LE_FORMAT_DATE_HTML;

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
