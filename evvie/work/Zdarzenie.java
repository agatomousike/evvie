package evvie.work;

import java.util.EventObject;


public class Zdarzenie {
	 private ZdarzenieRodzaj rodzaj;
	 private EventObject zdarzenie;
	 private Object data;
	 
	 public Zdarzenie(ZdarzenieRodzaj rodzaj, EventObject zdarzenie, Object data) {
			super();
			this.rodzaj = rodzaj;
			this.zdarzenie = zdarzenie;
			this.data = data;
		}
	 
	 public Object getData() {
		return data;
	}
	
	public Zdarzenie(ZdarzenieRodzaj rodzaj, EventObject zdarzenie) {
		super();
		this.rodzaj = rodzaj;
		this.zdarzenie = zdarzenie;
	}

	public Zdarzenie(ZdarzenieRodzaj rodzaj) {
		this.rodzaj = rodzaj;
	}

	public ZdarzenieRodzaj getRodzaj() {
		return rodzaj;
	}
	
	public  EventObject getZdarzenie() {
		return zdarzenie;
	}
}
