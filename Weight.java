public class Weight extends ValuedElement
{
	private Double nextValue;

	public Weight(String name, Double value)
	{
		super(name, value);
		nextValue = null;
	}

	public void setNextValue(Double nextValue)
	{
		this.nextValue = nextValue;
	}

	public void update()
	{
		value = nextValue;
	}
}