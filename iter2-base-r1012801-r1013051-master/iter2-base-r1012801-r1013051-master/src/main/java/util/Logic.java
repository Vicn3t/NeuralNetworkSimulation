package util;

/**
 * LEGIT
 */
public class Logic
{
	/* Private prevents objects from being created */
	private Logic() { }
	
	/**
	 * @post | result == (!p || q)
	 */
	public static boolean implies(boolean p, boolean q)
	{
		return !p || q;
	}
	
	/**
	 * @post | result == (p == q)
	 */
	public static boolean iff(boolean p, boolean q)
	{
		return p == q;
	}
}
