/**
 * 
 */
package evvie.exceptions;

/**
 * @author Jakub Such
 * 
 */
public class EvviePlanszaFactoryException extends Exception
{
	private static final long serialVersionUID = 1L;

	private final String message;

	public EvviePlanszaFactoryException(final String message)
	{
		super();
		this.message = message;
	}

	@Override
	public String getMessage()
	{
		return message;
	}
}
