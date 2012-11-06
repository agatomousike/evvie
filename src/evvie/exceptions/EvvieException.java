/**
 * 
 */
package evvie.exceptions;

/**
 * @author Jakub Such
 * 
 */
public class EvvieException extends Exception
{
	private static final long serialVersionUID = 1L;

	private final String message;

	public EvvieException(final String message)
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
