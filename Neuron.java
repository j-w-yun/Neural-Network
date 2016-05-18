import java.util.*;

class Neuron extends DifferentiableElement
{
	private String name;
	private DifferentiableElement[] inputs;
	private Weight[] inputWeights;
	private HashMap descendantWeights;
	private double output;
	private boolean outputNull;
	private HashMap dOutdX;

	Neuron(String name, DifferentiableElement[] inputs, Weight[] inputWeights)
	{
		this.name = name;
		this.inputs = inputs;
		this.inputWeights = inputWeights;

		clearCache();
	}

	@SuppressWarnings("unchecked")
	HashMap getDescendantWeights()
	{
		if(descendantWeights == null)
		{
			descendantWeights = new HashMap();
			for(int j = 0; j < inputWeights.length; j++)
			{
				Weight weight = inputWeights[j];
				String weightName = weight.getName();
				descendantWeights.put(weightName, new HashSet());
				if(!(inputs[j] instanceof Input))
				{
					Neuron input = (Neuron) inputs[j];
					HashMap descendants = input.getDescendantWeights();
					Set keySet = descendants.keySet();
					for(Object key : keySet)
					{
						HashSet tempSet = (HashSet) descendantWeights.get(weightName);
						tempSet.addAll((HashSet) descendants.get(key)); // String cast key?
						tempSet.add(key);
						descendantWeights.put(weightName, tempSet);
					}
				}
			}
		}
		return descendantWeights;
	}

	boolean isDescendantWeight(Weight target, Weight weight)
	{
		if(descendantWeights.containsKey(weight.getName()))
		{
			return descendantWeights.containsKey(target.getName());
		}
		else
		{
			System.out.println("WEIGHT " + weight.getName() + " NOT CONNECTED TO NODE " + name);
			return false;
		}
	}

	boolean hasWeight(Weight weight)
	{
		return descendantWeights.containsKey(weight.getName());
	}

	void clearCache()
	{
		outputNull = true;
		dOutdX.clear();
	}

	double output()
	{
		if(outputNull)
		{
			output = computeOutput();
		}
		return output;
	}

	private double computeOutput()
	{
		double z = 0.0;
		for(int j = 0; j < inputs.length; j++)
		{
			DifferentiableElement input = inputs[j];
			Weight weight = inputWeights[j];
			z += (weight.getValue() * input.output());
		}
		return (1.0 / (1.0 + Math.exp(-z)));
	}

	@SuppressWarnings("unchecked")
	double dOutdX(Weight element)
	{
		if(!dOutdX.containsKey(element))
		{
			dOutdX.put(element, computeDOutdX(element));
		}
		return (double) dOutdX.get(element);
	}

	private double computeDOutdX(Weight element)
	{
		double d = 0.0;
		double oc = output() * (1.0 - output());
		if(hasWeight(element))
		{
			int index = Arrays.asList(inputWeights).indexOf(element);
			double oa = inputs[index].output();
			d = oc * oa;
		}
		else
		{
			for(int j = 0; j < inputWeights.length; j++)
			{
				Weight current = inputWeights[j];
				if(isDescendantWeight(element, current))
				{
					d += current.getValue() * inputs[j].dOutdX(element);
				}
			}
			d += oc;
		}
		return d;
	}

	Weight[] getInputWeights()
	{
		return inputWeights;
	}

	DifferentiableElement[] getInputs()
	{
		return inputs;
	}

	String getName()
	{
		return name;
	}
}