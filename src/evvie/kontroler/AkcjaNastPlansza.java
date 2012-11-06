package evvie.kontroler;

import evvie.exceptions.EvviePlanszaFactoryException;
import evvie.model.Plansza;
import evvie.utils.AppTexts;
import evvie.utils.Logger;
import evvie.utils.Logger.Level;
import evvie.utils.PlanszaFactory;
import evvie.widok.Widok;
import evvie.widok.WZdarzenie;

class AkcjaNastPlansza extends Akcja
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see evvie.zdarzenia.Zdarzenie#execute(evvie.model.Plansza,
	 * evvie.widok.WidokGry, evvie.utils.Logger)
	 */
	@Override
	void execute(final Widok fasada, final Logger log,
			final AppTexts appTexts, final PlanszaFactory planszaFactory, Kontroler kontroler, WZdarzenie zdarzenie)
	{
		try
		{
			final Plansza nowaPlansza = planszaFactory.getNextPlansza();
			if (nowaPlansza != null)
			{
				nowaPlansza.setKomunikaty(appTexts);
				nowaPlansza.setLog(log);
				fasada.setPlanszaDlaWidoku(nowaPlansza.getPlanszaDlaWidoku());
				fasada.setNrPlanszy(nowaPlansza.getIdPlanszy());
				log.outDebug("AkcjaNastPlansza: wczytano plansze id:"
						+ nowaPlansza.getIdPlanszy());
				return;
			}
			fasada.showKoumnikat(appTexts.zdarzenie_brakplanszy);
			log.outDebug("AkcjaNastPlansza: nie uda³o sie wczytaæ nastêpnej planszy.");
		}
		catch (final EvviePlanszaFactoryException e)
		{
			log.out(Level.ERROR, e.getMessage());
		}

	}

}
