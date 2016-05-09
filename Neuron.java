import java.util.*;

public class Neuron<T extends DifferentiableElement> implements DifferentiableElement
{
	private Double output;
	private boolean outputCached;
	private Double dOutdX;
	private boolean dOutdXCached;

	private String name;
	private T[] inputs;
	private Weight[] weights;

	private Hashtable myDescendantWeights;

	public Neuron(String name, T[] inputs, Weight[] inputWeights)
	{
		assert inputs.length == inputWeights.length;
		for(int j = 0; j < inputs.length; j++)
		{
			assert (inputs[j] instanceof Input || inputs[j] instanceof Neuron);
			assert weights[j] instanceof Weight;
		}

		output = 0.0;
		outputCached = false;
		dOutdX = 0.0;
		dOutdXCached = false;

		this.name = name;
		this.inputs = inputs;
		this.weights = inputWeights;
		myDescendantWeights = null;
	}

	@SuppressWarnings("unchecked") public Hashtable getDescendantWeights()
	{
		if(myDescendantWeights == null)
		{
			myDescendantWeights = new Hashtable();
			// T[] tempInputs = Arrays.copyOf(inputs, inputs.length);
			// Weight[] tempWeights = Arrays.copyOf(weights, weights.length);
			for(int j = 0; j < weights.length; j++)
			{
				Weight weight = weights[j];
				String weightName = weight.getName();
				T input = inputs[j];
				myDescendantWeights.put(weightName, new HashSet());
				if(!(input instanceof Input))
				{
					Hashtable descendants = ((Neuron) input).getDescendantWeights();
					for(Object key : descendants.keySet())
					{
						HashSet hs = (HashSet) myDescendantWeights.get(weightName);
						hs.addAll((HashSet) descendants.get((String) key));
					}
				}
			}
		}

		return myDescendantWeights;
	}

	public Double output()
	{
		if(outputCached)
			return output;

		// TODO

		outputCached = true;
		return output;
	}

	public Double dOutdX()
	{
		if(dOutdXCached)
			return dOutdX;

		// TODO

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