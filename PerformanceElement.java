class PerformanceElement extends DifferentiableElement
{
	private DifferentiableElement input;
	private double desiredValue;

	PerformanceElement(DifferentiableElement input, double desiredValue)
	{
		this.input = input;
		this.desiredValue = desiredValue;
	}

	double output()
	{
		return -0.5 * (desiredValue - input.output()) * (desiredValue - input.output());
	}

	double dOutdX(Weight element)
	{
		return (desiredValue - input.output()) * input.dOutdX(element);
	}

	void setDesired(double desiredValue)
	{
		this.desiredValue = desiredValue;
	}

	DifferentiableElement getInput()
	{
		return input;
	}

	String getName()
	{
		return null;
	}
}