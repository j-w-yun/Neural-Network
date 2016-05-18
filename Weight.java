class Weight
{
	private String name;
	private double value;
	private double nextValue;

	Weight(String name, double value)
	{
		this.name = name;
		this.value = value;
	}

	void setNextValue(double nextValue)
	{
		this.nextValue = nextValue;
	}

	void update()
	{
		value = nextValue;
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