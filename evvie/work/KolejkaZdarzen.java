package evvie.work;

import java.util.LinkedList;

public class KolejkaZdarzen{
	/* pojemnoœæ kolejki */
	final int pojemnosc;
	LinkedList<Zdarzenie> kolejka;
	public KolejkaZdarzen() {
		kolejka = new LinkedList<Zdarzenie>();
		pojemnosc = 1000;
	}
	
	public KolejkaZdarzen(int setPojemnosc) {
		kolejka = new LinkedList<Zdarzenie>();
		pojemnosc = setPojemnosc;
	}
	
	synchronized Zdarzenie take() {
		if (kolejka.isEmpty())
		try {
		wait();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught wait take" + e.getMessage());
		}
		notify();
		return kolejka.removeFirst();
	}
	
	synchronized void put(Zdarzenie produce) {
		if (kolejka.size()>pojemnosc)
		try {
		wait();
		} catch (InterruptedException e) {
			System.out.println("InterruptedException caught wait put");
		}
		kolejka.addLast(produce);
		notify();
	}

	
	public boolean isEmpty() {
		return kolejka.isEmpty();
	}
	
	public int kolejkaSize(){
		return kolejka.size();
	}

}
