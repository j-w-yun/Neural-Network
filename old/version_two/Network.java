public class Network
{
	JayList inputs;
	JayList<Weight> weights;
	PerformanceElement performanceNode;
	Double output;
	JayList<DifferentiableElement> neurons;

	@SuppressWarnings("unchecked") public Network(PerformanceElement performanceNode, JayList neurons)
	{
		inputs = new JayList();
		weights = new JayList<Weight>();
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
		for(Object i : neurons.toArray())
		{
			// TODO test this
			weights.setArray(((Neuron) i).getWeightNodes());
		}
	}

	public void clearCache()
	{
		for(int j = 0; j < neurons.length(); j++)
		{
			neurons.get(j).clearCache();
		}
		// for(Object i : neurons.toArray())
		// {
		// 	// i.clearCache();
		// }
	}
}