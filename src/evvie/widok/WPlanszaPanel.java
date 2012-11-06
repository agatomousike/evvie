package evvie.widok;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import evvie.widok.WZdarzenie;
import evvie.model.OrientacjaCzesciKlockaEnum;
import evvie.model.PolePlanszyDlaWidoku;

/**
 * @author Jakub Such
 * 
 *         Panel przedstawiaj¹cy g³ówna logikê gry. Wyœwietla klocki oraz
 *         rejestruje interakcje z graczem. Wyœwietla makietê planszy gry
 *         stworzona z pól PolePlanszyDlaWidoku z pakietu model.
 */
class WPlanszaPanel extends JPanel
		implements
			MouseMotionListener,
			MouseListener
{
	private static final long serialVersionUID = 1L;
	Image imgKoniecGry;
	Image imgTloPanelu;
	BlockingQueue<WZdarzenie> kolejkaZdarzen;
	int myszkaKlikniecieDoPola = 0;

	int myszkaKlikniecieZPola = 0;
	Boolean myszkaPrzyciskNacisniety = false;
	int myszNadPolem;

	Map<Integer, PolePlanszyDlaWidoku> plansza;
	Map<OrientacjaCzesciKlockaEnum, ImageIcon> rysunkiKlocka;

	public WPlanszaPanel(final Map<Integer, PolePlanszyDlaWidoku> plansza,
			final BlockingQueue<WZdarzenie> kolejkaZdarzen)
	{
		this.plansza = plansza;
		this.kolejkaZdarzen = kolejkaZdarzen;
		myszNadPolem = 0;
		setBackground(new Color(106, 72, 32)); // ciemny braz
		setDoubleBuffered(true);
		addMouseMotionListener(this);
		addMouseListener(this);

		loadImages();
	}

	private ImageIcon getKlocekPartImg(ImageIcon img,
			final PolePlanszyDlaWidoku pole)
	{
		switch (pole.getOrientacjaKlocka())
		{
			case POZIOM :
				switch (pole.getNrSegementu())
				{
					case 0 :
						if (pole.getIdKlocka() == 0)
						{
							img = rysunkiKlocka
									.get(OrientacjaCzesciKlockaEnum.GraczPoczatek);
						}
						else
						{
							img = rysunkiKlocka
									.get(OrientacjaCzesciKlockaEnum.PoziomoPoczatek);
						}
						break;
					case 1 :
						if (pole.getIdKlocka() == 0)
						{
							img = rysunkiKlocka
									.get(OrientacjaCzesciKlockaEnum.GraczKoniec);
						}
						else
						{
							img = rysunkiKlocka
									.get(OrientacjaCzesciKlockaEnum.PoziomoSrodek);
						}
						break;
					case 2 :
						img = rysunkiKlocka
								.get(OrientacjaCzesciKlockaEnum.PoziomoKoniec);
						break;
				}
				break;
			case PION :
				switch (pole.getNrSegementu())
				{
					case 0 :
						img = rysunkiKlocka
								.get(OrientacjaCzesciKlockaEnum.PionowoPoczatek);
						break;
					case 1 :
						img = rysunkiKlocka
								.get(OrientacjaCzesciKlockaEnum.PionowoSrodek);
						break;
					case 2 :
						img = rysunkiKlocka
								.get(OrientacjaCzesciKlockaEnum.PionowoKoniec);
						break;
				}
				break;
		}
		return img;
	}

	private int getKtorePolePodMyszka(final MouseEvent m)
	{
		final int jednostkax = this.getWidth() / 6;
		final int jednostkay = this.getHeight() / 6;
		final int polex = m.getX() / jednostkax;
		final int poley = m.getY() / jednostkay;
		return polex + 6 * poley;
	}

	private void loadImages()
	{
		rysunkiKlocka = new HashMap<OrientacjaCzesciKlockaEnum, ImageIcon>();
		rysunkiKlocka.put(
				OrientacjaCzesciKlockaEnum.PoziomoPoczatek,
				new ImageIcon(this.getClass().getResource(
						"/zasoby/poziom_p.png")));
		rysunkiKlocka.put(
				OrientacjaCzesciKlockaEnum.PoziomoKoniec,
				new ImageIcon(this.getClass().getResource(
						"/zasoby/poziom_k.png")));
		rysunkiKlocka.put(
				OrientacjaCzesciKlockaEnum.PoziomoSrodek,
				new ImageIcon(this.getClass().getResource(
						"/zasoby/poziom_s.png")));
		rysunkiKlocka
				.put(OrientacjaCzesciKlockaEnum.PionowoPoczatek, new ImageIcon(
						this.getClass().getResource("/zasoby/pion_p.png")));
		rysunkiKlocka
				.put(OrientacjaCzesciKlockaEnum.PionowoSrodek, new ImageIcon(
						this.getClass().getResource("/zasoby/pion_s.png")));
		rysunkiKlocka
				.put(OrientacjaCzesciKlockaEnum.PionowoKoniec, new ImageIcon(
						this.getClass().getResource("/zasoby/pion_k.png")));
		rysunkiKlocka.put(
				OrientacjaCzesciKlockaEnum.GraczPoczatek,
				new ImageIcon(this.getClass().getResource(
						"/zasoby/poziom0a.png")));
		rysunkiKlocka.put(
				OrientacjaCzesciKlockaEnum.GraczKoniec,
				new ImageIcon(this.getClass().getResource(
						"/zasoby/poziom0b.png")));
		ImageIcon ii = new ImageIcon(this.getClass().getResource(
				"/zasoby/tlo01.png"));
		imgTloPanelu = ii.getImage();
		ii = new ImageIcon(this.getClass().getResource("/zasoby/koniecgry.png"));
		imgKoniecGry = ii.getImage();
	}

	@Override
	public void mouseClicked(final MouseEvent m)
	{
	}

	@Override
	public void mouseDragged(final MouseEvent m)
	{
		myszNadPolem = getKtorePolePodMyszka(m);
		if (myszkaKlikniecieZPola != myszNadPolem)
		{
			kolejkaZdarzen.add(new WZdarzenieKlocekWRuchu(myszkaKlikniecieZPola,
					myszNadPolem));
			myszkaKlikniecieZPola = myszNadPolem;
		}
		repaint();
	}

	@Override
	public void mouseEntered(final MouseEvent arg0)
	{
	}

	@Override
	public void mouseExited(final MouseEvent arg0)
	{
		myszNadPolem = -1;
	}

	@Override
	public void mouseMoved(final MouseEvent m)
	{
		if (getKtorePolePodMyszka(m) != myszNadPolem)
		{
			myszNadPolem = getKtorePolePodMyszka(m);
		}
		repaint();
	}

	@Override
	public void mousePressed(final MouseEvent m)
	{
		myszkaKlikniecieZPola = getKtorePolePodMyszka(m);
	}

	@Override
	public void mouseReleased(final MouseEvent m)
	{
	}

	@Override
	public void paint(final Graphics g)
	{
		super.paint(g);
		Toolkit.getDefaultToolkit().sync();

		final Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(new Color(150, 150, 150));

		final RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

		final int jednostkax = this.getWidth() / 6;
		final int jednostkay = this.getHeight() / 6;

		// rysuj tlo
		g2d.drawImage(imgTloPanelu, 0, 0, this.getWidth(), this.getHeight(),
				this);

		// element konica gry - 3 strza³ki
		g2d.drawImage(imgKoniecGry, 6 * jednostkax - 20, 2 * jednostkay, 20,
				jednostkay, this);

		// rysujemy planszei
		int rx = 0;
		int ry = 0;
		g2d.setColor(new Color(150));
		ImageIcon img = null;
		for (int i = 0; i < 36; i++)
		{
			final PolePlanszyDlaWidoku pole = plansza.get(i);
			if (pole != null)
			{
				img = getKlocekPartImg(img, pole);
				if (img != null)
				{
					g2d.drawImage(img.getImage(), rx, ry, jednostkax,
							jednostkay, this);
				}
			}

			rx = rx + jednostkax;
			if (rx >= (6 * jednostkax))
			{
				rx = 0;
			}
			if (i == 5 || i == 11 || i == 17 || i == 23 || i == 29)
			{
				ry = ry + jednostkay;
			}
		}

		// linia gorna i dolna
		g2d.setColor(new Color(0, 0, 0));
		g2d.drawLine(0, 0, this.getWidth(), 0);
		g2d.drawLine(0, this.getHeight() - 1, this.getWidth(),
				this.getHeight() - 1);

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void setPlansza(final Map<Integer, PolePlanszyDlaWidoku> plansza)
	{
		this.plansza = plansza;
		repaint();
	}

}