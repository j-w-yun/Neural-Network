/**
*	@author Jaewan Yun (Jay50@pitt.edu)
*	@version 1.0.0
*/

public class Input extends ValuedElement implements DifferentiableElement
{
	private Double output;
	private boolean outputCached;
	private Double dOutdX;
	private boolean dOutdXCached;

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public Input(String name, Double value)
	{
		super(name, value);

		output = 0.0;
		outputCached = false;
		dOutdX = 0.0;
		dOutdXCached = false;
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public Double output()
	{
		return this.value;
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public Double dOutdX(ValuedElement element)
	{
		return 0.0;
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public void clearCache()
	{
		output = 0.0;
		outputCached = false;
		dOutdX = 0.0;
		dOutdXCached = false;
	}
}