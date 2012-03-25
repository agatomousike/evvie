package evvie.kontroler;
import evvie.model.*;

public class Kontroler {

	public static void main(String[] args) {
		Gracz gracz = new Gracz("kuba");
		gracz.setCzasGry(50);
		gracz.setPunktyGracza(10000);
		System.out.println("przezwisko " + gracz.getPrzezwisko());
		System.out.println("czas " + gracz.getCzasGry());
		System.out.println("punkty " + gracz.getPunktyGracza());
		
		PozycjaNaPlanszy poz = PozycjaNaPlanszy.P10;
		System.out.println("enumeracja pozycja: " +poz.getValue());
		
	}

}
