package evvie.widok;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.BlockingQueue;

class WNastepnaPlanszaListener implements MouseListener
{

	final BlockingQueue<WZdarzenie> kolejkaZdarzen;

	public WNastepnaPlanszaListener(
			final BlockingQueue<WZdarzenie> kolejkaZdarzen)
	{
		this.kolejkaZdarzen = kolejkaZdarzen;
	}

	@Override
	public void mouseClicked(final MouseEvent e)
	{
		try
		{
			kolejkaZdarzen.put(new WZdarzenieNastepnaPlansza());
		}
		catch (final InterruptedException e1)
		{
		}
	}

	@Override
	public void mouseEntered(final MouseEvent e)
	{
	}

	@Override
	public void mouseExited(final MouseEvent e)
	{
	}

	@Override
	public void mousePressed(final MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(final MouseEvent e)
	{
	}

}
