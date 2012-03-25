package evvie.work;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventObject;

import javax.swing.*;

	public class OknoTestoweZdarzenia extends JFrame implements Runnable {
		protected JTextArea textArea;
		private final KolejkaZdarzen kolejka;
		
		public OknoTestoweZdarzenia(KolejkaZdarzen setKolejka){
			super("OknoTestoweZdarzenia");
			kolejka = setKolejka;
			new Thread(this, "Consumer").start();
		}
		
		private void onOtworz(ActionEvent arg0) {
			kolejka.put(new Zdarzenie(ZdarzenieRodzaj.OPEN, arg0));
		}
		

		private void onZapiszAction( ActionEvent arg0) {
			kolejka.put(new Zdarzenie(ZdarzenieRodzaj.SAVE, arg0));
		}

		public void run() {
			this.addMouseMotionListener(new MouseMotionListener() {
				public void mouseMoved(MouseEvent arg0) {
					kolejka.put(new Zdarzenie(ZdarzenieRodzaj.MOUSEMOTION,(EventObject) arg0));
				}
				public void mouseDragged(MouseEvent arg0) {
					kolejka.put(new Zdarzenie(ZdarzenieRodzaj.MOUSEMOTION,(EventObject) arg0));
				}
			});
			
			setLayout(new FlowLayout());
			textArea = new JTextArea(5, 20);
			textArea.setEditable(true);
			textArea.addFocusListener(new FocusListener() {
				public void focusLost(FocusEvent arg0) {
					kolejka.put(new Zdarzenie(ZdarzenieRodzaj.TEXTFOCUS,(EventObject) arg0, textArea.getText()));
					
				}
				
				@Override
				public void focusGained(FocusEvent arg0) {
					kolejka.put(new Zdarzenie(ZdarzenieRodzaj.TEXTFOCUS,(EventObject) arg0, textArea.getText()));
				}
			});
			add(textArea);
			JButton zapiszButton = new JButton("Zapisz");
			add(zapiszButton);
			zapiszButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					onZapiszAction(arg0);
					
				}
			});
		    zapiszButton.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent arg0) {
					kolejka.put(new Zdarzenie(ZdarzenieRodzaj.MOUSERELEASED,(EventObject) arg0));	
				}
				public void mousePressed(MouseEvent arg0) {
					kolejka.put(new Zdarzenie(ZdarzenieRodzaj.MOUSEPRESSED,(EventObject) arg0));
				}
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			JButton otworzButton = new JButton("Otwórz");
			add(otworzButton);
			otworzButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					onOtworz(arg0);		  
				}
				});

			setSize(300,300);
			pack();
			setVisible(true);
			
		}

	}
