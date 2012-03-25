package evvie.model;

class PolePlanszy {
	private boolean wolnePole;
	private int idKlocka;
	
	public PolePlanszy() {
		wolnePole = true;
		idKlocka=-1;//poza plansza
	}

	public PolePlanszy(boolean wolnePole, int idKlocka) {
		super();
		this.wolnePole = wolnePole;
		this.idKlocka = idKlocka;
	}

	public boolean isWolnePole() {
		return wolnePole;
	}

	public void setWolnePole(boolean wolnePole) {
		this.wolnePole = wolnePole;
	}

	public int getIdKlocka() {
		return idKlocka;
	}

	public void setIdKlocka(int idKlocka) {
		this.idKlocka = idKlocka;
	}

}
