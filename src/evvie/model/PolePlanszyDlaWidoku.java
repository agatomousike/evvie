package evvie.model;

/**
 * @author Jakub Such
 * 
 *         Model pola planszy który zostanie wykorzystany w widoku.
 */
public class PolePlanszyDlaWidoku
{
	private final int idKlocka;
	private final int nrSegementu;
	private final OrientacjaKlockaEnum orientacjaKlocka;

	public PolePlanszyDlaWidoku(final int idKlocka, final int nrSegementu,
			final OrientacjaKlockaEnum orientacjaKlocka)
	{
		super();
		this.idKlocka = idKlocka;
		this.nrSegementu = nrSegementu;
		this.orientacjaKlocka = orientacjaKlocka;
	}

	public int getIdKlocka()
	{
		return idKlocka;
	}

	public int getNrSegementu()
	{
		return nrSegementu;
	}

	public OrientacjaKlockaEnum getOrientacjaKlocka()
	{
		return orientacjaKlocka;
	}
}
