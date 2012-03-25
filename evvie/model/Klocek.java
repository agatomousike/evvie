package evvie.model;

public class Klocek {
	final LiczbaSegementow liczbaSegmentow;
	final OrientacjaKlocka orientacja;
	PozycjaNaPlanszy pozycjaNaPlanszy;
	
	public Klocek(LiczbaSegementow liczbaSegmentow,
			OrientacjaKlocka orientacja, PozycjaNaPlanszy pozycjaNaPlanszy) {
		super();
		this.liczbaSegmentow = liczbaSegmentow;
		this.orientacja = orientacja;
		this.pozycjaNaPlanszy = pozycjaNaPlanszy;
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
