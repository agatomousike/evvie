package evvie.kontroler;

import evvie.exceptions.EvviePlanszaException;
import evvie.model.Plansza;
import evvie.utils.AppTexts;
import evvie.utils.Logger;
import evvie.utils.PlanszaFactory;
import evvie.widok.Widok;
import evvie.widok.WZdarzenie;
import evvie.widok.WZdarzenieKlocekWRuchu;

class AkcjaKlocekWRuchu extends Akcja
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see evvie.widok.Zdarzenie#execute(evvie.model.Plansza,
	 * evvie.widok.OknaFasada, evvie.utils.Logger)
	 */
	@Override
	void execute(final Widok fasada, final Logger log,
			final AppTexts appTexts, final PlanszaFactory planszaFactory, Kontroler kontroler, WZdarzenie zdarzenie)
	{
		try
		{
			final Plansza plansza = planszaFactory.getCurrentPlansza();
			if(zdarzenie instanceof WZdarzenieKlocekWRuchu ){
				final WZdarzenieKlocekWRuchu zdarzenieKlocekWRuchu = (WZdarzenieKlocekWRuchu) zdarzenie;
				log.outDebug("AkcjaKlocekwRuchu:");
				plansza.moveKlocek(zdarzenieKlocekWRuchu.getzPole(),zdarzenieKlocekWRuchu.getDoPole());
				fasada.setPlanszaDlaWidoku(plansza.getPlanszaDlaWidoku());
				if (plansza.isWygranaKoniecPlanszy())
				{
					fasada.showKoumnikat(appTexts.zdarzenie_wygrales);
				}
			}
		}
		catch (final EvviePlanszaException e)
		{
			log.out(Logger.Level.ERROR,
					"AkcjaKlocekwRuchu: " + e.getMessage());
		}
	}


}
