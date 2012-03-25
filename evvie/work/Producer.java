package evvie.work;

class Producer implements Runnable {
	   private final KolejkaZdarzen queue;
	   Producer(KolejkaZdarzen q) { 
		   queue = q;
		   idProdukt = 0;
	   }
	   
	   public void run() {

	       while(true){
	    	   queue.put(produce());  
	       }
	   }
	   
	   Integer idProdukt;
	   Zdarzenie produce() {
		   System.out.println("Produkuje: " + ++idProdukt);
		   return new Zdarzenie(ZdarzenieRodzaj.TEST);
	   }
}