package evvie.kontroler;

import evvie.utils.AppTexts;
import evvie.utils.Logger;
import evvie.utils.PlanszaFactory;
import evvie.widok.Widok;
import evvie.widok.WZdarzenie;

/**
 * @author Jakub Such
 * 
 *         Wspólny interfejs zdarzeñ wykorzystanych w modelu widok.
 */

abstract class Akcja
{
	abstract void execute(Widok fasada, Logger log, AppTexts appTexts, PlanszaFactory planszaFactory, Kontroler kontroler, WZdarzenie zdarzenie);
}
