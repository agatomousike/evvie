package evvie.model;

public class Gracz {
	private final String przezwisko;
	private int punktyGracza;
	private int czasGry;
	
	public Gracz(String przezwisko, int punktyGracza, int czasGry) {
		super();
		this.przezwisko = przezwisko;
		this.punktyGracza = punktyGracza;
		this.czasGry = czasGry;
	}

	public Gracz(String przezwisko) {
		super();
		this.przezwisko = przezwisko;
		this.punktyGracza = 0;
		this.czasGry = 0;
	}
	
	public int getCzasGry() {
		return czasGry;
	}
	public String getPrzezwisko() {
		return przezwisko;
	}
	public int getPunktyGracza() {
		return punktyGracza;
	}
	public void setCzasGry(int czasGry) {
		this.czasGry = czasGry;
	}
	public void setPunktyGracza(int punktyGracza) {
		this.punktyGracza = punktyGracza;
	}
	public void addPunktyGracza(int punkty){
		this.punktyGracza = this.punktyGracza + punkty; 
	}
	public void addCzasGry(int czas){
		this.czasGry = this.czasGry +czasGry;
	}
	
}
