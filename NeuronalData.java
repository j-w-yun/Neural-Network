/**
*	@author Jaewan Yun (Jay50@pitt.edu)
*	@version 1.0.0
*/

class NeuronalData
{
	private double weight;
	private double threshold;
	private Node nextNode;
	private Node previousNode;

	NeuronalData(double weight, double threshold, Node previousNode, Node nextNode)
	{
		this.weight = weight;
		this.threshold = threshold;
		this.previousNode = previousNode;
		this.nextNode = nextNode;
	}

	double setWeight(double weight)
	{ return this.weight = weight; }
	double setThreshold(double threshold)
	{ return this.threshold = threshold; }
	Node setNextNode(Node nextNode)
	{ return this.nextNode = nextNode; }
	Node setPreviousNode(Node previousNode)
	{ return this.previousNode = previousNode; }
	double getWeight()
	{ return weight; }
	double getThreshold()
	{ return threshold; }
	Node getNextNode()
	{ return nextNode; }
	Node getPreviousNode()
	{ return previousNode; }
}