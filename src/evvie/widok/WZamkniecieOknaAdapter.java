package evvie.widok;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.BlockingQueue;

class WZamkniecieOknaAdapter extends WindowAdapter
{

	final BlockingQueue<WZdarzenie> kolejkaZdarzen;

	public WZamkniecieOknaAdapter(
			final BlockingQueue<WZdarzenie> kolejkaZdarzen)
	{
		super();
		this.kolejkaZdarzen = kolejkaZdarzen;
	}

	@Override
	public void windowClosing(final WindowEvent e)
	{
		try
		{
			kolejkaZdarzen.put(new WZdarzenieZamkniecieOkna());
		}
		catch (final InterruptedException e1)
		{
		}
	}

}
