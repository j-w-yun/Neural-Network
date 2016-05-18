public class PerformanceElement<T extends DifferentiableElement> implements DifferentiableElement
{
	private T myInput;
	private Double myDesiredValue;

	public PerformanceElement(T input, Double desiredValue)
	{
		assert input instanceof Input || input instanceof Neuron;
		myInput = input;
		myDesiredValue = desiredValue;
	}

	public Double output()
	{
		return -0.5 * (myDesiredValue - myInput.output()) * (myDesiredValue - myInput.output());
	}

	public Double dOutdX(ValuedElement element)
	{
		return (myDesiredValue - myInput.output() * myInput.dOutdX(element));
	}

	public void clearCache() {}

	public void setDesired(Double newDesired)
	{
		myDesiredValue = newDesired;
	}

	public T getInput()
	{
		return myInput;
	}
}