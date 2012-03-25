package evvie.model;

public class Plansza {
	private final String idPlanszy;
	private final String opisPlanszy;
	private PolePlanszy [] plansza; /*czy pole jest zaj�te czy wolne i jaki klocek si� tam znajduje*/
	private final Klocek[] klocki; /*zbi�r klock�w na planszy*/
	private final int liczbaKlockow;
	
	public Plansza(String idPlanszy, String opisPlanszy, int liczbaKlockow) {
		super();
		this.idPlanszy = idPlanszy;
		this.opisPlanszy = opisPlanszy;
		this.liczbaKlockow = liczbaKlockow;
		this.klocki = new Klocek[liczbaKlockow];
		this.plansza = new PolePlanszy[PozycjaNaPlanszy.getLICZBA_POL_PLANSZY()];
	}
	
	public String getIdPlanszy() {
		return idPlanszy;
	}

	public String getOpisPlanszy() {
		return opisPlanszy;
	}

	public boolean setKlocek(int nrKlocka, LiczbaSegementow liczbaSegementow, OrientacjaKlocka orientacja, PozycjaNaPlanszy pozycja){
		if(nrKlocka < liczbaKlockow){
				klocki[nrKlocka].setLiczbaSegmentow(liczbaSegementow);
				klocki[nrKlocka].setOrientacja(orientacja);
				klocki[nrKlocka].setPozycjaNaPlanszy(pozycja);
				return true;
		}
		return false;
	}
	
	private boolean isWolneMiejsceNaKlocek(int nrKlocka, PozycjaNaPlanszy pozycja){
		if(nrKlocka<liczbaKlockow)
		{
			LiczbaSegementow segment = klocki[nrKlocka].getLiczbaSegmentow();
			OrientacjaKlocka orientacja = klocki[nrKlocka].getOrientacja();
			PozycjaNaPlanszy staraPozycja= klocki[nrKlocka].getPozycjaNaPlanszy();
			
			if(staraPozycja==pozycja) return true;
			/*1 jest wolne miejsce*/
			if(orientacja==OrientacjaKlocka.PION){
				if(segment==LiczbaSegementow.DWA){
					//sprawdzi� czy pole nie jest zakazane tz czy ty� klocka nie wychodzi poza plansze
					if(plansza[pozycja.getValue()].isWolnePole() && 
							plansza[pozycja.getValue()+1].isWolnePole()) return true;
					return false;
				}else{/*trzy*/
					if(plansza[pozycja.getValue()].isWolnePole() && 
							plansza[pozycja.getValue()+1].isWolnePole()&&
								plansza[pozycja.getValue()+2].isWolnePole()) return true;
					return false;
				}
			}else{/*poziom*/
				
			}
		}
		return false; /*nie ma takiego klocka*/
	}
}
