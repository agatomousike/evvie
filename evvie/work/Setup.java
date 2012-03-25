package evvie.work;

class Setup {
	public static void main(String args[])  {
	     KolejkaZdarzen q = new KolejkaZdarzen();
//	     Producer p = new Producer(q);
	     Consumer c1 = new Consumer(q);
//	     Consumer c2 = new Consumer(q);
//	     new Thread(p).start();
	     new Thread(c1).start();
//	     new Thread(c2).start();
	     
		System.out.println("OknoTestoweZdarzenia starting..");
		new OknoTestoweZdarzenia(q);
	   
	}
	 }