package evvie.kontroler;

import evvie.exceptions.EvviePlanszaFactoryException;
import evvie.model.Plansza;
import evvie.utils.AppTexts;
import evvie.utils.Logger;
import evvie.utils.Logger.Level;
import evvie.utils.PlanszaFactory;
import evvie.widok.Widok;
import evvie.widok.WZdarzenie;

class AkcjaPoprzedniaPlansza extends Akcja
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see evvie.widok.Zdarzenie#getObject()
	 */
	@Override
	void execute(final Widok fasada, final Logger log,
			final AppTexts appTexts, final PlanszaFactory planszaFactory, Kontroler kontroler, WZdarzenie zdarzenie)
	{
		try
		{
			final Plansza nowaPlansza = planszaFactory.getPrevPlansza();
			if (nowaPlansza != null)
			{
				nowaPlansza.setKomunikaty(appTexts);
				nowaPlansza.setLog(log);
				fasada.setPlanszaDlaWidoku(nowaPlansza.getPlanszaDlaWidoku());
				fasada.setNrPlanszy(nowaPlansza.getIdPlanszy());
				log.outDebug("AkcjaPoprzedniaPlansza: wczytano plansze id:"
						+ nowaPlansza.getIdPlanszy());
				return;
			}
			fasada.showKoumnikat(appTexts.zdarzenie_brakplanszy);
			log.outDebug("AkcjaPrevPlansza: nie uda³o sie wczytaæ poprzedniej planszy.");
		}
		catch (final EvviePlanszaFactoryException e)
		{
			log.out(Level.ERROR, e.getMessage());
		}

	}

}
