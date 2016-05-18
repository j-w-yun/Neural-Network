/**
*	@author Jaewan Yun (Jay50@pitt.edu)
*	@version 1.0.0
*/

public interface DifferentiableElement
{
	public abstract Double output();

	public abstract Double dOutdX(ValuedElement element);

	public void clearCache();

	public String getName();
}