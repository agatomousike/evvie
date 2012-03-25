package evvie.model;

public class Klocek {
	private LiczbaSegementow liczbaSegmentow;
	private OrientacjaKlocka orientacja;
	private PozycjaNaPlanszy pozycjaNaPlanszy;
	
	public Klocek() {
		this.liczbaSegmentow = LiczbaSegementow.DWA; /*domyœlnie*/
		this.orientacja = OrientacjaKlocka.POZIOM; /*domyœlnie*/
		this.pozycjaNaPlanszy = PozycjaNaPlanszy.P0; /*domyœlnie - poza plansza */
	}
	
	public Klocek(LiczbaSegementow liczbaSegmentow,
			OrientacjaKlocka orientacja, PozycjaNaPlanszy pozycjaNaPlanszy) {
		super();
		this.liczbaSegmentow = liczbaSegmentow;
		this.orientacja = orientacja;
		this.pozycjaNaPlanszy = pozycjaNaPlanszy;
	}

	public void setLiczbaSegmentow(LiczbaSegementow liczbaSegmentow) {
		this.liczbaSegmentow = liczbaSegmentow;
	}

	public void setOrientacja(OrientacjaKlocka orientacja) {
		this.orientacja = orientacja;
	}

	public Klocek(LiczbaSegementow liczbaSegmentow, OrientacjaKlocka orientacja) {
		super();
		this.liczbaSegmentow = liczbaSegmentow;
		this.orientacja = orientacja;
		this.pozycjaNaPlanszy = PozycjaNaPlanszy.P0;
	}

	public PozycjaNaPlanszy getPozycjaNaPlanszy() {
		return pozycjaNaPlanszy;
	}

	public void setPozycjaNaPlanszy(PozycjaNaPlanszy pozycjaNaPlanszy) {
		this.pozycjaNaPlanszy = pozycjaNaPlanszy;
	}

	public LiczbaSegementow getLiczbaSegmentow() {
		return liczbaSegmentow;
	}

	public OrientacjaKlocka getOrientacja() {
		return orientacja;
	}
}
