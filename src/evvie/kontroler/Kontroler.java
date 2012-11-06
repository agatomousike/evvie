package evvie.kontroler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import evvie.exceptions.EvviePlanszaFactoryException;
import evvie.model.Plansza;
import evvie.model.PolePlanszyDlaWidoku;
import evvie.utils.AppTexts;
import evvie.utils.Logger;
import evvie.utils.Logger.Level;
import evvie.utils.PlanszaFactory;
import evvie.widok.WZdarzenieKlocekWRuchu;
import evvie.widok.WZdarzenieNastepnaPlansza;
import evvie.widok.WZdarzeniePoprzedniaPlansza;
import evvie.widok.WZdarzenieZamkniecieOkna;
import evvie.widok.Widok;
import evvie.widok.WZdarzenie;


/**
 * Kontroler gry. Zarz¹dza modelem planszy oraz koordynuje zdarzenia i
 * interakcje z u¿ytkownikiem wysy³ane przez widok.
 * 
 * @author Jakub Such
 * 
 */

public class Kontroler
{
	Widok fasada;;
	private final BlockingQueue<WZdarzenie> kolejkaZdarzen = new LinkedBlockingQueue<WZdarzenie>();

	private final AppTexts komunikaty;
	private final Logger log;
	private final PlanszaFactory planszaFactory;
	private Map<Integer, PolePlanszyDlaWidoku> planszaWidok;
	private boolean koniecGry;
	private Map<Class<? extends WZdarzenie>, Akcja> slownik;

	/**
	 * Konstruktor kontrolera - jest to g³ówny mechanizm gry. Tworz¹cy planszê i
	 * obs³uguj¹cy zdarzenia z widoku.
	 * 
	 * @param plansze
	 */
	public Kontroler(final Logger log, final AppTexts komunikaty)
	{
		this.log = log;
		this.komunikaty = komunikaty;
		this.planszaFactory = new PlanszaFactory();
		this.slownik =   new HashMap<Class<? extends WZdarzenie>, Akcja>();	
	}


	public void startGry()
	{
		initSlownik();
		if (initPierwszaPlansze())
		{
			WZdarzenie zdarzenie;
			while (true)
			{
				try
				{
					zdarzenie = kolejkaZdarzen.take();
					if (zdarzenie != null)
					{
						slownik.get(zdarzenie.getClass()).execute(fasada, log, komunikaty,
										planszaFactory, this, zdarzenie);
						if (koniecGry)
						{
							log.outDebug("Kontroler: koniec gry.");
							return;
						}	
					}
				}
				catch (final InterruptedException e)
				{
					log.out(Level.ERROR,"Kontroler: run: " + e.getMessage());
				}

			}
		}
		
	}


	private boolean initPierwszaPlansze()
	{
		try
		{
			final Plansza plansza = planszaFactory.getNextPlansza();
			plansza.setLog(log);
			plansza.setKomunikaty(komunikaty);
			planszaWidok = plansza.getPlanszaDlaWidoku();
			fasada = new Widok(plansza.getIdPlanszy(), planszaWidok,
					kolejkaZdarzen);
			fasada.showOkno();
			fasada.showKoumnikat(komunikaty.kontroler_startgry);
			return true;
		}
		catch (final EvviePlanszaFactoryException e)
		{
			log.out(Level.ERROR, e.getMessage());
			this.fasada = new Widok(0, null, null);
			fasada.showKoumnikat(komunikaty.kontroler_brakplanszy);
			fasada.koniec();
			return false;
		}

	}
	
	private void initSlownik()
	{
		slownik.put(WZdarzenieKlocekWRuchu.class, new AkcjaKlocekWRuchu());
		slownik.put(WZdarzenieNastepnaPlansza.class, new AkcjaNastPlansza());
		slownik.put(WZdarzeniePoprzedniaPlansza.class, new AkcjaPoprzedniaPlansza());
		slownik.put(WZdarzenieZamkniecieOkna.class, new AkcjaKoniecGry());
	}
	
	public void tellKoniecGry(){
		this.koniecGry = true;
	}
}
