/**
*	@author Jaewan Yun (Jay50@pitt.edu)
*	@version 1.0.0
*/

public class Neuron<T extends NeuronalData>
{
	private NeuronalData data;
	private double input;
	private double output;
	private double desiredOutcome;
	private double performancePartialDerivative;
	private double performancePrepartialDerivative;
	private boolean stateUnchanged = false;

	private Neuron(T data)
	{ this.data = data; }

	// Static factory
	public static Neuron getNeuron(double weight, double threshold, Node previousNode, Node nextNode)
	{ return new Neuron<NeuronalData>(new NeuronalData(weight, threshold, previousNode, nextNode)); }
	public static Neuron getNeuron_previous(double weight, double threshold, Node previousNode)
	{ return new Neuron<NeuronalData>(new NeuronalData(weight, threshold, previousNode, null)); }
	public static Neuron getNeuron_next(double weight, double threshold, Node nextNode)
	{ return new Neuron<NeuronalData>(new NeuronalData(weight, threshold, null, nextNode)); }
	public static Neuron getNeuron(double weight, double threshold)
	{ return new Neuron<NeuronalData>(new NeuronalData(weight, threshold, null, null)); }
	public static Neuron getNeuron(double weight, Node previousNode, Node nextNode)
	{ return new Neuron<NeuronalData>(new NeuronalData(weight, 0d, previousNode, nextNode)); }
	public static Neuron getNeuron_previous(double weight, Node previousNode)
	{ return new Neuron<NeuronalData>(new NeuronalData(weight, 0d, previousNode, null)); }
	public static Neuron getNeuron_next(double weight, Node nextNode)
	{ return new Neuron<NeuronalData>(new NeuronalData(weight, 0d, null, nextNode)); }
	public static Neuron getNeuron(double weight)
	{ return new Neuron<NeuronalData>(new NeuronalData(weight, 0d, null, null)); }

	// public double getPerformancePartialDerivative(double desiredOutcome)
	// {
	// 	if(!stateUnchanged)
	// 		throw new IllegalArgumentException();

	// 	return getPerformancePartialDerivative(input, desiredOutcome);
	// }
	// public double getPerformancePartialDerivative(double input, double desiredOutcome)
	// {
	// 	if(!stateUnchanged || this.input != input)
	// 		getOutput(input);

	// 	// performancePartialDerivative = getPerformancePartialDerivative()

	// 	return performancePartialDerivative;
	// }
	// private double getPerformancePrepartialDerivative(double input, double desiredOutcome)
	// {
	// 	if(!stateUnchanged || this.input != input || this.desiredOutcome != desiredOutcome)
	// 	{
	// 		getOutput(input);

	// 	}
	// 	else
	// 	{
	// 		return performancePrepartialDerivative;
	// 	}
	// }

	public double getOutput(double input)
	{
		return output = (1.0 / (1 + Math.exp(-1 * (input - data.getThreshold()))));
	}

	// Getters and Setters
	public double setWeight(double weight)
	{ return data.setWeight(weight); }
	public double setThreshold(double threshold)
	{ return data.setThreshold(threshold); }
	public Node setPreviousNode(Node previousNode)
	{ return data.setPreviousNode(previousNode); }
	public Node setNextNode(Node nextNode)
	{ return data.setNextNode(nextNode); }
	public double getWeight()
	{ return data.getWeight(); }
	public double getThreshold()
	{ return data.getThreshold(); }
	public Node getPreviousNode()
	{ return data.getPreviousNode(); }
	public Node getNextNode()
	{ return data.getNextNode(); }
}