package evvie.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import evvie.exceptions.EvviePlanszaException;
import evvie.utils.AppTexts;
import evvie.utils.Logger;

public class Plansza implements Serializable
{
	transient Logger log;
	transient AppTexts komunikaty;
	transient public static final int LICZBA_POL_PLANSZY = 36;
	
	private static final long serialVersionUID = 1L;
	private int idPlanszy;
	private int liczbaKlockow = 0;
	private Klocek[] plansza;

	public Plansza(final int idPlanszy, final Logger loger,
			final AppTexts komunikaty)
	{
		this.idPlanszy = idPlanszy;
		this.komunikaty = komunikaty;
		this.log = loger;
		this.plansza = new Klocek[LICZBA_POL_PLANSZY];
	}

	public boolean addKlocek(final int nrPierwszegoPola, final Klocek newKlocek)
			throws EvviePlanszaException
	{
		if (!(isVaildPole(nrPierwszegoPola) && newKlocek != null && isSpaceForKlocek(
				nrPierwszegoPola, newKlocek)))
		{
			return false;
		}

		isPoprawnyKlocekGracza(newKlocek);

		switch (newKlocek.getOrientacja())
		{
			case PION :
				putKlocekPion(nrPierwszegoPola, newKlocek);
				break;

			case POZIOM :
				putKlocekPoziom(nrPierwszegoPola, newKlocek);
				break;

			default :
				throw new EvviePlanszaException(komunikaty.plansza_enumerr);
		}
		liczbaKlockow++;
		return true;
	}

	private int findPierwszePoleKlocka(final Klocek klocek)
	{
		int nrPolaKlocka = -1;
		for (int i = 0; i < LICZBA_POL_PLANSZY; i++)
		{
			if (plansza[i] == klocek)
			{
				nrPolaKlocka = i; // pozycja klocka zaczyna sie od tego pola
				break;
			}
		}
		return nrPolaKlocka;
	}



	public int getIdPlanszy()
	{
		return idPlanszy;
	}

	public Klocek getKlockaFromPole(final int nrPola)
	{
		if (isVaildPole(nrPola))
		{
			return plansza[nrPola];
		}
		return null;
	}

	/**
	 * Generuje makietê planszy.
	 */
	public Map<Integer, PolePlanszyDlaWidoku> getPlanszaDlaWidoku()
	{
		final Map<Integer, PolePlanszyDlaWidoku> wynik = new HashMap<Integer, PolePlanszyDlaWidoku>();
		final int[] klocek = new int[liczbaKlockow];
		for (int nrPola = 0; nrPola < 36; nrPola++)
		{
			if (plansza[nrPola] == null)
			{
				wynik.put(nrPola, null); // PolePlanszy domyœlnie jest puste
				continue;
			}
			else
			{
				final int idKlocka = plansza[nrPola].getIdKlocka();
				wynik.put(nrPola, new PolePlanszyDlaWidoku(idKlocka,
						klocek[idKlocka], plansza[nrPola].getOrientacja()));
				klocek[idKlocka]++;
			}
		}
		return wynik;
	}

	private boolean isPoprawnyKlocekGracza(final Klocek newKlocek)
			throws EvviePlanszaException
	{
		if (newKlocek.getIdKlocka() == 0
				&& (newKlocek.getOrientacja() == OrientacjaKlockaEnum.PION || newKlocek
						.getLiczbaSegmentow() == 3))
		{
			throw new EvviePlanszaException(komunikaty.plansza_graczklocekerr);
		}
		return true;
	}

	/**
	 * Sprawdza czy jest wolne miejsce dla danego danego klocka.
	 * 
	 * @param klocek
	 * @return
	 */
	private boolean isSpaceForKlocek(final int nrPola, final Klocek klocek)
			throws EvviePlanszaException
	{
		if (!isVaildPole(nrPola))
		{
			return false;
		}
		if (!(plansza[nrPola] == null || plansza[nrPola] == klocek))
		{
			log.outDebug("Plansza: isSpaceForKlocek false " + nrPola + " idK "
					+ klocek.getIdKlocka());
			return false;
		}
		switch (klocek.getOrientacja())
		{
			case PION :
				return isSpaceForKlocekPion(nrPola, klocek);

			case POZIOM :
				return isSpaceForKlocekPoziom(nrPola, klocek);

			default :
				throw new EvviePlanszaException(komunikaty.plansza_enumerr);
		}
	}

	/**
	 * Sprawdza czy jest wolne miejsce dla pionowego klocka.
	 * 
	 * @param nrPola
	 * @param klocek
	 * @return
	 * @throws EvviePlanszaException
	 */
	private boolean isSpaceForKlocekPion(final int nrPola, final Klocek klocek)
	{
		int pole = nrPola;
		for (int i = 0; i < klocek.getLiczbaSegmentow(); i++)
		{
			if (!(isVaildPole(pole) && (plansza[pole] == null || plansza[pole] == klocek)))
			{
				log.outDebug("Plansza: isSpaceForKlocek flase pion " + nrPola
						+ " idK " + klocek.getIdKlocka());
				return false;
			}
			pole += 6;
		}
		log.outDebug("Plansza: isSpaceForKlocek true pion " + nrPola + " idK "
				+ klocek.getIdKlocka());
		return true;
	}

	/**
	 * Sprawdza czy jest wolne miejsce dla pionowego klocka.
	 * 
	 * @param nrPola
	 * @param klocek
	 * @return
	 * @throws EvviePlanszaException
	 */
	private boolean isSpaceForKlocekPoziom(final int nrPola, final Klocek klocek)
	{
		int pole = nrPola;
		final int liczbaSegmentow = klocek.getLiczbaSegmentow();
		for (int i = 0; i < liczbaSegmentow; i++)
		{
			if (!isVaildPole(pole)
					|| !(plansza[pole] == null || plansza[pole] == klocek))
			{
				log.outDebug("Plansza: isSpaceForKlocek false poziom" + nrPola
						+ " idK " + klocek.getIdKlocka());
				return false;
			}
			if ((pole + 1) % 6 == 0 && i + 1 != liczbaSegmentow)
			{
				log.outDebug("Plansza: isSpaceForKlocek false poziom" + nrPola
						+ " idK " + klocek.getIdKlocka());
				return false;
			}
			pole = pole + 1;
		}
		log.outDebug("Plansza: isSpaceForKlocek true poziom" + nrPola + " idK "
				+ klocek.getIdKlocka());
		return true;
	}

	/**
	 * Funkcja sprawdza czy pole nale¿y do planszy.
	 * 
	 * @param nrPola
	 * @return boolean
	 */
	private boolean isVaildPole(final int nrPola)
	{
		if (nrPola < 0 || nrPola >= LICZBA_POL_PLANSZY)
		{
			return false;
		}
		return true;
	}

	/**
	 * Odpowiada czy plansza jest w stanie gdy u¿ytkownik wygra³.
	 * 
	 */
	public Boolean isWygranaKoniecPlanszy()
	{
		if (plansza[17] != null && plansza[17].getIdKlocka() == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean isZabronionyRuchKlocka(final Klocek klocek,
			final KierunekEnum kierunek)
	{
		switch (klocek.getOrientacja())
		{
			case PION :
				if (kierunek == KierunekEnum.PRAWO
						|| kierunek == KierunekEnum.LEWO)
				{
					return true;
				}
				break;
			case POZIOM :
				if (kierunek == KierunekEnum.DOL
						|| kierunek == KierunekEnum.GORA)
				{
					return true;

				}
				break;
		}
		return false;
	}

	/**
	 * Przemieszcza klocek do nowego miejsca.
	 * 
	 * @param nrPola
	 *            - nowe pierwsze pole klocka
	 * @param klocek
	 * @return
	 * @throws EvviePlanszaException
	 */
	private boolean moveKlocek(final int nrPola, final Klocek klocek)
			throws EvviePlanszaException
	{
		if (!isVaildPole(nrPola))
		{
			return false;
		}
		zdejmijKlocekZPlanszy(klocek);

		switch (klocek.getOrientacja())
		{
			case PION :
				putKlocekPion(nrPola, klocek);
				break;
			case POZIOM :
				putKlocekPoziom(nrPola, klocek);
				break;
			default :
				throw new EvviePlanszaException(komunikaty.plansza_enumerr);
		}
		return true;
	}
	
	public boolean moveKlocek(final int zPole, final int doPole)
	throws EvviePlanszaException
	{	
		final KierunekEnum kierunek = findKierunek(zPole,doPole);
		final Klocek klocek = getKlockaFromPole(zPole);
		
		if (klocek ==null || kierunek == null)
		{
			log.outDebug("ZdarzenieKlocekwRuchu: nie ma takiego ruchu");
			return false; // niedozwolony ruch
		}
		return moveKlocek(klocek, kierunek);
	}
	
	public boolean moveKlocek(final Klocek klocek, final KierunekEnum kierunek)
			throws EvviePlanszaException
	{
		if (klocek == null)
		{
			return false;
		}
		final int nrPolaKlocka = findPierwszePoleKlocka(klocek);
		if (nrPolaKlocka == -1)
		{
			log.outDebug("Plansza: moveKlocek false klocka nie ma na planszy idK "
					+ klocek.getIdKlocka());
			return false; // klocka nie ma na planszy
		}

		if (isZabronionyRuchKlocka(klocek, kierunek))
		{
			log.outDebug("Plansza: moveKlocek zabroniony");
			return false;
		}

		if (isSpaceForKlocek(nrPolaKlocka + kierunek.getInt(), klocek))
		{
			log.outDebug("Plansza: moveKlocek true idK " + klocek.getIdKlocka());
			moveKlocek(nrPolaKlocka + kierunek.getInt(), klocek);
			return true;
		}
		else
		{
			log.outDebug("Plansza: moveKlocek false nie ma mijesca idK "
					+ klocek.getIdKlocka());
			return false;
		}
	}

	private void putKlocekPion(final int nrPierwszegoPola,
			final Klocek newKlocek)
	{
		plansza[nrPierwszegoPola] = newKlocek;
		plansza[nrPierwszegoPola + 6] = newKlocek;
		if (newKlocek.getLiczbaSegmentow() == 3)
		{
			plansza[nrPierwszegoPola + 12] = newKlocek;
		}
	}

	private void putKlocekPoziom(final int nrPierwszegoPola,
			final Klocek newKlocek)
	{
		plansza[nrPierwszegoPola] = newKlocek;
		plansza[nrPierwszegoPola + 1] = newKlocek;
		if (newKlocek.getLiczbaSegmentow() == 3)
		{
			plansza[nrPierwszegoPola + 2] = newKlocek;
		}
	}

	

	public void setKomunikaty(final AppTexts komunikaty)
	{
		this.komunikaty = komunikaty;
	}

	public void setLog(final Logger log)
	{
		this.log = log;
	}

	private void zdejmijKlocekZPlanszy(final Klocek klocek)
	{
		for (int i = 0; i < LICZBA_POL_PLANSZY; i++)
		{
			if (plansza[i] == klocek)
			{
				plansza[i] = null;
			}
		}
	}
	
	private KierunekEnum findKierunek(final int zPole, final int doPole)
	{
		final int intKierunek = Math.abs(doPole - zPole);
		switch (intKierunek)
		{
			case 1 : // poziom
				if (doPole > zPole)
				{
					return KierunekEnum.PRAWO;
				}
				else
				{
					return KierunekEnum.LEWO;
				}
			case 6 : // pion
				if (doPole > zPole)
				{
					return KierunekEnum.DOL;
				}
				else
				{
					return KierunekEnum.GORA;
				}
			default :
				return null;// niedozwolony ruch

		}
	}

}
