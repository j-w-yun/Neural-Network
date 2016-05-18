class Input extends DifferentiableElement
{
	private String name;
	private double value;

	Input(String name, double value)
	{
		this.name = name;
		this.value = value;
	}

	double output()
	{
		return value;
	}

	double dOutdX(Weight element)
	{
		return 0;
	}

	void setValue(double value)
	{
		this.value = value;
	}

	double getValue()
	{
		return value;
	}

	String getName()
	{
		return name;
	}
}