package evvie.widok;

public class WZdarzenieKlocekWRuchu extends WZdarzenie
{
	private final int doPole;
	private final int zPole;

	public WZdarzenieKlocekWRuchu(final int ruchZ, final int ruchDo)
	{
		this.zPole = ruchZ;
		this.doPole = ruchDo;
	}

	public WZdarzenieKlocekWRuchu()
	{ 
		doPole = 0;
		zPole = 0;
		
	}

	public int getDoPole()
	{
		return doPole;
	}

	public int getzPole()
	{
		return zPole;
	}	
}
