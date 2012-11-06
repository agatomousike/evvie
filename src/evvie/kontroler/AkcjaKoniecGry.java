package evvie.kontroler;

import evvie.utils.AppTexts;
import evvie.utils.Logger;
import evvie.utils.PlanszaFactory;
import evvie.widok.Widok;
import evvie.widok.WZdarzenie;

class AkcjaKoniecGry extends Akcja
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see evvie.widok.Zdarzenie#execute(evvie.model.Plansza,
	 * evvie.widok.OknaFasada, evvie.utils.Logger)
	 */
	@Override
	void execute(final Widok fasada, final Logger log,
			AppTexts appTexts, PlanszaFactory planszaFactory, Kontroler kontroler, WZdarzenie zdarzenie)
	{
		fasada.koniec();
		log.outDebug("AkcjaKoniecGry: ");
		kontroler.tellKoniecGry();
	}

}
