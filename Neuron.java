import java.util.*;

/**
*	@author Jaewan Yun (Jay50@pitt.edu)
*	@version 1.0.0
*/

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

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
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

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
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
						// TODO : test this
						HashSet hs = (HashSet) myDescendantWeights.get(weightName);
						hs.addAll((HashSet) descendants.get((String) key));
						hs.add((String) key);
						myDescendantWeights.put(weightName, hs);
					}
				}
			}
		}

		return myDescendantWeights;
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public boolean isDecendantWeightOf(ValuedElement target, Weight weight) throws Exception
	{
		Hashtable weights_ = getDescendantWeights();
		if(weights_.contains(weight.getName()))
			return weights_.contains(target.getName());
		else
			throw new Exception("weight " + weight + " does not connect to node " + this);
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public boolean hasWeight(ValuedElement weight)
	{
		Hashtable weights_ = getDescendantWeights();
		return weights_.contains(weight.getName());
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public Weight[] getWeightNodes()
	{
		return weights;
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public Double output()
	{
		if(outputCached)
			return output;
		else
		{
			Double out = 0.0;
			for(int j = 0; j < inputs.length; j++)
			{
				out += weights[j].getValue() * inputs[j].output();
			}

			output = (1.0 / (1.0 + Math.exp(-out)));
		}

		outputCached = true;
		return output;
	}

	/**
	*	Backpropagation (gradient ascent - note that performance calculation is factored negative)
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public Double dOutdX(ValuedElement element)
	{
		if(dOutdXCached)
			return dOutdX;
		else
		{
			// Double out = output();
			// Double temp = out * (1 - out);

			// if(hasWeight(element))
			// {
			// 	int index = weights;
			// }
		}

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