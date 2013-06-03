package jire.logging;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UniversalLogger extends AbstractLogger {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"MM-dd-yyyy");
	private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat(
			"MM-dd-yyyy HH:mm:ss aa");// "MM-dd-yyyy HH:mm:ss aa"
	private static final DateFormat TIME_FORMAT = new SimpleDateFormat(
			"HH:mm:ss aa");

	@Override
	public void showLog(final String content, final Type type,
			final Format format) {
		String dateContent = "[";
		Date date = new Date();
		switch (format) {
		case DATE:
			dateContent += DATE_FORMAT.format(date);
			break;
		case DATE_TIME:
			dateContent += DATE_TIME_FORMAT.format(date);
			break;
		case TIME:
			dateContent += TIME_FORMAT.format(date);
			break;
		default:
			break;
		}

		String output = format.equals(Format.RAW) ? content : dateContent
				+ "] [" + type + "]: " + content;
		switch (type) {
		case ERROR:
			System.err.println(output);
			break;
		default:
			System.out.println(output);
			break;
		}
	}

}