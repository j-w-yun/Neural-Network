public class Network
{
	JayList inputs;
	JayList weights;
	PerformanceElement performanceNode;
	Double output;
	JayList neurons;

	public Network(PerformanceElement performanceNode, JayList neurons)
	{
		inputs = new JayList();
		weights = new JayList();
		this.performanceNode = performanceNode;
		// output = performanceNode.getInput();
		this.neurons = neurons;
		this.neurons.sort();
	}
}