/**
*	@author Jaewan Yun (Jay50@pitt.edu)
*	@version 1.0.0
*/

public class Weight extends ValuedElement
{
	private Double nextValue;

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public Weight(String name, Double value)
	{
		super(name, value);
		nextValue = null;
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public void setNextValue(Double nextValue)
	{
		this.nextValue = nextValue;
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public void update()
	{
		value = nextValue;
	}
}