/**
 * 
 */
package evvie.app;

import evvie.exceptions.EvvieKlocekException;
import evvie.exceptions.EvviePlanszaException;
import evvie.exceptions.EvviePlanszaFactoryException;
import evvie.model.Klocek;
import evvie.model.OrientacjaKlockaEnum;
import evvie.model.Plansza;
import evvie.utils.AppTexts;
import evvie.utils.Logger;
import evvie.utils.ObjectIO;

/**
 * @author Jakub Such
 * 
 */
public class AppGenPlansze
{
	public static void main(final String[] args)
	{
		generePlansza();

	}

	private static void generePlansza()
	{
		final Logger log = new Logger();
		final AppTexts appTexts = new AppTexts();

		final Plansza plansza = new Plansza(3, log, appTexts);
		try
		{
			final Klocek k0 = new Klocek(0, OrientacjaKlockaEnum.POZIOM, 2,
					appTexts);
			System.out.println(plansza.addKlocek(12, k0));

			final Klocek k1 = new Klocek(1, OrientacjaKlockaEnum.PION, 2,
					appTexts);
			System.out.println(plansza.addKlocek(0, k1));

			final Klocek k2 = new Klocek(2, OrientacjaKlockaEnum.POZIOM, 2,
					appTexts);
			System.out.println(plansza.addKlocek(1, k2));

			final Klocek k3 = new Klocek(3, OrientacjaKlockaEnum.POZIOM, 2,
					appTexts);
			System.out.println(plansza.addKlocek(3, k3));

			final Klocek k4 = new Klocek(4, OrientacjaKlockaEnum.PION, 2,
					appTexts);
			System.out.println(plansza.addKlocek(8, k4));

			final Klocek k5 = new Klocek(5, OrientacjaKlockaEnum.PION, 3,
					appTexts);
			System.out.println(plansza.addKlocek(15, k5));

			final Klocek k6 = new Klocek(6, OrientacjaKlockaEnum.PION, 3,
					appTexts);
			System.out.println(plansza.addKlocek(16, k6));

			final Klocek k7 = new Klocek(7, OrientacjaKlockaEnum.POZIOM, 3,
					appTexts);
			System.out.println(plansza.addKlocek(24, k7));

			ObjectIO.saveObject(
					"E:\\repo\\evvie\\evvie\\plansze\\plansza3.pl", plansza);

		}
		catch (final EvvieKlocekException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (final EvviePlanszaException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (final EvviePlanszaFactoryException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}