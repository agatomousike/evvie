package evvie.work;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.*;
class Consumer implements Runnable {
	   boolean mousePresed;
		static int idC;
	   private final KolejkaZdarzen queue;
	   Consumer(KolejkaZdarzen q) {
		   mousePresed = false;
		   ++idC;
		   queue = q; }
	   public void run() {
	       while(true) {
	    	   consume(queue.take());
	       }
	   }

	void consume(Zdarzenie x) { 
		   if(x!=null&& (x.getZdarzenie() instanceof ActionEvent) )
		   System.out.println("Konsumuje " + idC +":" + x.getRodzaj() + " " + ((ActionEvent)x.getZdarzenie()).getActionCommand());
		   
		   switch(x.getRodzaj()){
		   	case OPEN:
			   System.out.println("otwieranie pliku..");
			   break;
		   	case SAVE:
		   		   System.out.println("zapisywanie pliku..");
				   break;
		   	case TEXTFOCUS:
		   		   System.out.println(x.getRodzaj());
				   break;
		   	case MOUSEMOTION:
				   System.out.println("mousemotion " + ((MouseEvent)x.getZdarzenie()).getX() + "x" + ((MouseEvent)x.getZdarzenie()).getX() );
				   break;
		   	case MOUSEPRESSED:
		   		   mousePresed =true;
				   System.out.println("mousepressed " + ((MouseEvent)x.getZdarzenie()).getX() + "x" + ((MouseEvent)x.getZdarzenie()).getX() );
				   break;
		   	case MOUSERELEASED:
		   		   mousePresed = false;
				   System.out.println("mouserelased " + ((MouseEvent)x.getZdarzenie()).getX() + "x" + ((MouseEvent)x.getZdarzenie()).getX() );
				   break;
		   }
		   
	
	   }
}