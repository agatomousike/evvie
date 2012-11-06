package evvie.model;

import java.io.Serializable;

import evvie.exceptions.EvvieKlocekException;
import evvie.utils.AppTexts;

public class Klocek implements Serializable
{
	private static final long serialVersionUID = 1L;
	private final int idKlocka;
	private int liczbaSegmentow;
	private final OrientacjaKlockaEnum orientacja;

	public Klocek(final int idKlocka,
			final OrientacjaKlockaEnum orientacjaKlockaEnum,
			final int liczbaSegementow, final AppTexts komunikaty)
			throws EvvieKlocekException
	{
		this.idKlocka = idKlocka;
		this.orientacja = orientacjaKlockaEnum;
		if (liczbaSegementow == 2 || liczbaSegementow == 3)
		{
			this.liczbaSegmentow = liczbaSegementow;
		}
		else
		{
			throw new EvvieKlocekException(komunikaty.klocek_consterr);
		}
	}

	public int getIdKlocka()
	{
		return idKlocka;
	}

	public int getLiczbaSegmentow()
	{
		return liczbaSegmentow;
	}

	public OrientacjaKlockaEnum getOrientacja()
	{
		return orientacja;
	}

}
