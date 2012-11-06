/**
 * 
 */
package evvie.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import evvie.exceptions.EvviePlanszaFactoryException;

/**
 * @author Jakub Such
 * 
 */
public class ObjectIO
{

	public static Object loadObject(final String fileName)
			throws EvviePlanszaFactoryException
	{
		FileInputStream fis;
		ObjectInputStream ois = null;
		Object returnObject = null;
		try
		{
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);
			returnObject = ois.readObject();
			ois.close();
			return returnObject;
		}
		catch (final Exception e)
		{
			throw new EvviePlanszaFactoryException("PlanszaFactory: loadObject: Filename:" + fileName
							+ " Message:" + e.getMessage());
		}
		finally
		{
			if (ois != null)
			{
				try
				{
					ois.close();
				}
				catch (final IOException e)
				{
				}
			}
		}
	}

	public static Object loadObjectFromResources(final String fileName)
			throws EvviePlanszaFactoryException
	{
		InputStream is;
		ObjectInputStream ois = null;
		Object returnObject = null;
		try
		{
			is = PlanszaFactory.class.getResourceAsStream(fileName);
			ois = new ObjectInputStream(is);
			returnObject = ois.readObject();
			ois.close();
			return returnObject;
		}
		catch (final Exception e)
		{
			throw new EvviePlanszaFactoryException("PlanszaFactory: loadObject: Filename:" + fileName
							+ "Typ: " + e.toString() + " Message:"
							+ e.getMessage());
		}
		finally
		{
			if (ois != null)
			{
				try
				{
					ois.close();
				}
				catch (final IOException e)
				{
				}
			}
		}

	}

	public static void saveObject(final String fileName, final Object obiect)
			throws EvviePlanszaFactoryException
	{
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try
		{
			fos = new FileOutputStream(fileName);
			out = new ObjectOutputStream(fos);
			out.writeObject(obiect);
			out.close();
		}
		catch (final IOException ex)
		{
			throw new EvviePlanszaFactoryException("PlanszaFactory: saveObject: Filename:" + fileName
							+ "Object: " + Object.class + " Message:"
							+ ex.getMessage());
		}
	}

}
