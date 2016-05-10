public class Network
{
	JayList inputs;
	JayList weights;
	PerformanceElement performanceNode;
	Double output;
	JayList neurons;

	@SuppressWarnings("unchecked") public Network(PerformanceElement performanceNode, JayList neurons)
	{
		inputs = new JayList();
		weights = new JayList();
		this.performanceNode = performanceNode;
		// output = performanceNode.getInput();
		this.neurons = neurons;
		this.neurons.sort();
		for(int j = 0; j < neurons.length(); j++)
		{
			Weight[] someWeights = ((Neuron) neurons.get(j)).getWeights();
			for(int k = 0; k < someWeights.length; k++)
			{
				weights.addLast(someWeights[k]);
			}

			// OMG java
			for(DifferentiableElement i : ((DifferentiableElement[]) ((Neuron) neurons.get(j)).getInputs()))
			{
				if(i instanceof Input && i.getName() != "i0" && !inputs.contains(i))
				{
					inputs.addLast(i);
					// Phew...
				}
			}
		}
		// weights.reverse();
		weights.clear();
		for(DifferentiableElement i : ((DifferentiableElement[]) (neurons.toArray())))
		{
			// TODO test this
			weights.addLast(((Neuron) i).getWeightNodes());
		}
	}

	public void clearCache()
	{
		for(DifferentiableElement i : ((DifferentiableElement[]) (neurons.toArray())))
		{
			i.clearCache();
		}
	}
}