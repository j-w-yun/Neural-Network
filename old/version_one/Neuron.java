public class Neuron
{
	private static final double step = 0.5;

	private JayList<Double> weight;
	private JayList<Neuron> parent;
	private JayList<Neuron> child;

	private double threshold = 0.0;	// For now

	private double input;
	private double product;
	private double output;
	private double sigma;
	private double rate;

	// For output neurons
	private double desired;

	private boolean validState;
	private boolean uptodateIOput;
	private boolean uptodateSigma;

	public Neuron()
	{
		weight = new JayList<Double>();
		parent = new JayList<Neuron>();
		child = new JayList<Neuron>();

		validState = false;
		uptodateIOput = false;
		uptodateSigma = false;
	}

	public void addParent(Neuron parent, double thisWeight)
	{
		this.parent.addLast(parent);
		this.weight.addLast(thisWeight);
		parent.child.addLast(this);

		validState = true;
		parent.validState = true;
		uptodateIOput = false;
		uptodateSigma = false;
	}

	public void addChild(Neuron child, double childWeight)
	{
		child.parent.addLast(this);
		child.weight.addLast(childWeight);
		this.child.addLast(child);

		validState = true;
		child.validState = true;
		uptodateIOput = false;
		uptodateSigma = false;
	}

	public double calculateOutput()
	{
		if(!validState)
			throw new IllegalStateException();

		output = 0.0;
		input = 0.0;
		product = 0.0;

		if(isHead())
		{
			// Sigmoid function
			product = input * weight.getFirst();
			output = 1.0 / (1 + Math.exp(-product + threshold));
		}
		else
		{
			for(int j = 0; j < parent.length(); j++)
			{
				// Go through the deque
				Double currentWeight = weight.addLast(weight.removeFirst());
				// Sigmoid function
				product += parent.get(j).calculateOutput() * currentWeight;
				output += 1.0 / (1 + Math.exp(-product + threshold));
			}
		}

		uptodateIOput = true;
		uptodateSigma = false;
		return output;
	}

	public void calculateWeight()
	{
		if(isHead())
		{
			weight.removeFirst();
			Double newWeight = rate * input * getPDsigma();
			weight.addLast(newWeight);
		}
		else
		{
			for(int j = 0; j < parent.length(); j++)
			{
				Double currentWeight = weight.removeFirst();
				Double newWeight = currentWeight + (rate * input * getPDsigma());
				weight.addLast(newWeight);
			}
		}

		uptodateIOput = false;
		uptodateSigma = false;
	}

	private double getPDsigma()
	{
		// if(uptodateSigma)
			// return sigma;

		if(!uptodateIOput)
			calculateOutput();

		if(isLeaf())
		{
			sigma = (output * (1 - output) * (desired - output));
		}
		else
		{
			sigma = output * (1 - output) * getTotal();
		}

		uptodateSigma = true;
		return sigma;
	}

	private double getTotal()
	{
		double total = 0.0;

		if(isLeaf())
			return weight.getFirst();

		for(int j = 0; j < parent.length(); j++)
		{
			for(int k = 0; k < parent.get(j).weight.length(); k++)
			{
				// System.out.println(parent.length() + " " + parent.get(j).weight.length());
				total += (parent.get(j).weight.get(k) * parent.get(j).getPDsigma()) + parent.get(j).getTotal();
			}
		}

		return total;
	}

	public void setDesired(double desired)
	{
		this.desired = desired;
	}

	public void setInput(double input, double weight)
	{
		this.input = input;
		this.weight.addLast(weight);
		uptodateIOput = false;
		uptodateSigma = false;
	}

	public int getNumChild()
	{
		if(!validState)
			throw new IllegalStateException();

		if(isLeaf())
			return 0;

		return child.length();
	}

	public boolean isHead()
	{
		if(!validState)
			throw new IllegalStateException();

		return parent.length() == 0;
	}

	public boolean isLeaf()
	{
		if(!validState)
			throw new IllegalStateException();

		return child.length() == 0;
	}
}