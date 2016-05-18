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

	private String myName;
	private T[] myInputs;
	private Weight[] myWeights;

	private HashMap myDescendantWeights;

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
			assert inputWeights[j] instanceof Weight;
		}

		this.myName = name;
		this.myInputs = inputs;
		this.myWeights = inputWeights;

		output = 0.0;
		outputCached = false;
		dOutdX = 0.0;
		dOutdXCached = false;

		myDescendantWeights = null;
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	@SuppressWarnings("unchecked") public HashMap getDescendantWeights()
	{
		if(myDescendantWeights == null)
		{
			myDescendantWeights = new HashMap();
			// T[] tempInputs = Arrays.copyOf(inputs, inputs.length);
			// Weight[] tempWeights = Arrays.copyOf(weights, weights.length);
			for(int j = 0; j < myWeights.length; j++)
			{
				Weight weight = myWeights[j];
				String weightName = myWeights[j].getName();
				myDescendantWeights.put(weightName, new HashSet());
				T input = myInputs[j];
				if(!(input instanceof Input))
				{
					HashMap descendants = ((Neuron) input).getDescendantWeights();
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
	public boolean isDecendantWeightOf(ValuedElement target, Weight weight)
	{
		HashMap weights = getDescendantWeights();
		if(weights.containsKey(weight.getName()))
			return weights.containsKey(target.getName());
		else
		{
			System.out.println("weight " + weight + " does not connect to node " + this);
			return false;
		}
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public boolean hasWeight(ValuedElement weight)
	{
		HashMap weights = getDescendantWeights();
		return weights.containsKey(weight.getName());
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public Weight[] getWeightNodes()
	{
		return myWeights;
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
			for(int j = 0; j < myInputs.length; j++)
			{
				// System.out.println(myInputs[j].output());
				out += myWeights[j].getValue() * myInputs[j].output();
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
			Double out = output();
			Double temp = out * (1 - out);

			if(hasWeight(element))
			{
				int index = 0;
				for(int j = 0; j < myWeights.length; j++)
				{
					if(myWeights[j].getName() == element.getName() &&
						myWeights[j].getValue() == element.getValue())
					{
						index = j;
						break;
					}
				}

				Double oa = myInputs[index].output();
				dOutdX = temp * oa;
			}
			else
			{
				dOutdX = 0.0;
				for(int j = 0; j < myWeights.length; j++)
				{
					Weight currentWeight = myWeights[j];
					if(isDecendantWeightOf(element, currentWeight))
					{
						Double inputDeriv = myInputs[j].dOutdX(element);
						dOutdX += currentWeight.getValue() * inputDeriv;
					}
				}
				dOutdX += temp;
			}
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

	public Weight[] getWeights()
	{
		return myWeights;
	}

	public T[] getInputs()
	{
		return myInputs;
	}

	public String getName()
	{
		return myName;
	}
}