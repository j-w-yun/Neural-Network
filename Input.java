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
		if(outputCached)
			return output;

		// TODO

		outputCached = true;
		return output;
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public Double dOutdX()
	{
		if(dOutdXCached)
			return dOutdX;

		// TODO

		dOutdXCached = true;
		return dOutdX;
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