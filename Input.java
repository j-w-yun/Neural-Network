public class Input extends ValuedElement implements DifferentiableElement
{
	private Double output;
	private boolean outputCached;
	private Double dOutdX;
	private boolean dOutdXCached;

	public Input(String name, Double value)
	{
		super(name, value);
	}

	public Double output()
	{
		if(outputCached)
			return output;



		outputCached = true;
		return output;
	}

	public Double dOutdX()
	{
		if(dOutdXCached)
			return dOutdX;



		dOutdXCached = true;
		return dOutdX;
	}

	public void clearCache()
	{
		output = 0.0;
		outputCached = false;
		dOutdX = 0.0;
		dOutdXCached = false;
	}
}