/**
 * 
 */
package evvie.utils;

import evvie.exceptions.EvviePlanszaFactoryException;
import evvie.model.Plansza;

/**
 * @author Jakub Such
 * 
 */
public class PlanszaFactory
{
	private Plansza plansza = null;
	private final String planszaNamePost = ".pl";
	private final String planszaNamePref = "/plansze/plansza";
	private int planszaNumber = -1; // aktualnie wczytana plansza

	private final int planszeNumberTotal = 4;

	public PlanszaFactory()
	{
		planszaNumber = -1;
	}

	public Plansza getCurrentPlansza()
	{
		return plansza;
	}

	public Plansza getNextPlansza() throws EvviePlanszaFactoryException
	{
		return getPlanszaFromFile(planszaNumber + 1);
	}

	public Plansza getPlanszaFromFile(final int nrPlanszy)
			throws EvviePlanszaFactoryException
	{
		if (nrPlanszy >= 0 && nrPlanszy < planszeNumberTotal)
		{
			plansza = (Plansza) ObjectIO
					.loadObjectFromResources(planszaNamePref + nrPlanszy
							+ planszaNamePost);
			planszaNumber = nrPlanszy;
			return plansza;
		}
		return null;
	}

	public Plansza getPrevPlansza() throws EvviePlanszaFactoryException
	{
		return getPlanszaFromFile(planszaNumber - 1);
	}

}
