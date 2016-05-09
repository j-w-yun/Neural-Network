/**
*	@author Jaewan Yun (Jay50@pitt.edu)
*	@version 1.0.0
*/

public interface DifferentiableElement
{
	public abstract Double output();

	public abstract Double dOutdX();

	public void clearCache();
}