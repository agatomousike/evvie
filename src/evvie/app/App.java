/**
 * 
 */
package evvie.app;

import evvie.kontroler.Kontroler;
import evvie.utils.AppTexts;
import evvie.utils.Logger;

/**
 * @author Jakub Such
 * 
 */
public class App
{

	/**
	 * @param args
	 */
	public static void main(final String[] args)
	{
		final Logger log = new Logger();
		final AppTexts appTexts = new AppTexts();
		final Kontroler kontroler = new Kontroler(log, appTexts);

		log.out(Logger.Level.INFO, appTexts.app_start);
		kontroler.startGry();
	}

}
