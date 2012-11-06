package evvie.model;

public enum KierunekEnum
{
	DOL(6), GORA(-6), LEWO(-1), PRAWO(1);

	int kierunek;

	private KierunekEnum(final int kierunek)
	{
		this.kierunek = kierunek;
	}

	public int getInt()
	{
		return kierunek;
	}
}
