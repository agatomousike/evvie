/**
 * 
 */
package evvie.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jakub Such
 * 
 */
public class Logger
{
	public enum Level
	{
		DEBUG, ERROR, INFO
	}
	private final Date data;
	private final DateFormat dateFormat;

	/**
	 * 
	 */
	public Logger()
	{
		dateFormat = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
		data = new Date();

	}

	public void out(final Level level, final String message)
	{
		System.out.println(dateFormat.format(data) + "[" + level + "] "
				+ message);
	}

	public void outDebug(final String message)
	{
		System.out.println(dateFormat.format(data) + "[" + Level.DEBUG + "] "
				+ message);
	}
}
