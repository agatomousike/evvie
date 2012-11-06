package evvie.widok;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import evvie.widok.WZdarzenie;
import evvie.model.PolePlanszyDlaWidoku;

public class Widok
{
	private final JPanel gorneMenu;
	private final JLabel lblNrPlanszy;
	private final JFrame okno;
	private final WPlanszaPanel planszaGry;

	public Widok(final int nrPlanszy,
			final Map<Integer, PolePlanszyDlaWidoku> planszaDlaWidoku,
			final BlockingQueue<WZdarzenie> kolejkaZdarzen)
	{

		planszaGry = new WPlanszaPanel(planszaDlaWidoku, kolejkaZdarzen);

		gorneMenu = new JPanel();
		gorneMenu.setBackground(new Color(106, 72, 32));
		lblNrPlanszy = new JLabel("" + nrPlanszy);
		final JLabel lblNastPlansza = new JLabel(">");
		final JLabel lblPoprzedniaPlansza = new JLabel("<");
		lblNastPlansza.addMouseListener(new WNastepnaPlanszaListener(
				kolejkaZdarzen));
		lblPoprzedniaPlansza.addMouseListener(new WPoprzedniaPlanszaListener(
				kolejkaZdarzen));
		final Font font = new Font("Arial", 0, 42);
		lblNrPlanszy.setFont(font);
		lblPoprzedniaPlansza.setFont(font);
		lblNastPlansza.setFont(font);
		gorneMenu.setMinimumSize(new Dimension(600, 200));
		gorneMenu.add(lblPoprzedniaPlansza);
		gorneMenu.add(lblNrPlanszy);
		gorneMenu.add(lblNastPlansza);

		lblPoprzedniaPlansza.setForeground(Color.white);
		lblNrPlanszy.setForeground(Color.white);
		lblNastPlansza.setForeground(Color.white);

		okno = new JFrame("Evvie");
		okno.addWindowListener(new WZamkniecieOknaAdapter(kolejkaZdarzen));
		okno.pack();
		okno.setSize(600, 700);
		okno.setLocationRelativeTo(null);
		okno.setTitle("Evvie");
		okno.setResizable(false);
		okno.setLayout(new BorderLayout());
		okno.add(gorneMenu, BorderLayout.NORTH);
		okno.add(planszaGry, BorderLayout.CENTER);
	}

	public void koniec()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				if (okno != null)
				{
					okno.setVisible(false);
					okno.dispose();
				}
			}
		});

	}

	public void setNrPlanszy(final int nrPlanszy)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				lblNrPlanszy.setText("" + nrPlanszy);
			}
		});
	}

	public void setPlanszaDlaWidoku(
			final Map<Integer, PolePlanszyDlaWidoku> planszaDlaWidoku)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				planszaGry.setPlansza(planszaDlaWidoku);
			}
		});
	}

	public void showKoumnikat(final String message)
	{
		try
		{
			SwingUtilities.invokeAndWait(new Runnable()
			{
				@Override
				public void run()
				{
					JOptionPane.showMessageDialog(null, message);
				}
			});
		}
		catch (final InterruptedException e)
		{
		}
		catch (final InvocationTargetException e)
		{
		}
	}

	public void showOkno()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				if (okno != null)
				{
					okno.setVisible(true);
				}
			}
		});

	}

}
