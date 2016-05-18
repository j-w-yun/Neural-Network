public class DNNtester
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		Input i0 = new Input("i0", -1.0);
		Input i1 = new Input("i1", 0.0);
		Input i2 = new Input("i2", 0.0);

		Input[] i = new Input[3];
		i[0] = i0;
		i[1] = i1;
		i[2] = i2;

		Weight w1A = new Weight("w1A", 1.0);
		Weight w2A = new Weight("w2A", 1.0);
		Weight wA = new Weight("wA", 1.0);

		Weight[] w = new Weight[3];
		w[0] = w1A;
		w[1] = w2A;
		w[2] = wA;

		Neuron a_ = new Neuron("A", i, w);
		JayList<DifferentiableElement> a = new JayList<DifferentiableElement>();
		a.addLast(a_);
		PerformanceElement p = new PerformanceElement(a_, 0.0);

		Network n = new Network(p, a);

		double[][] data = {{0, 0, 0}, {1, 0, 0}, {0, 1, 0}, {1, 1, 1}};

		for(int j = 0; j < 10000; j++)
		{
			for(double[] datum : data)
			{
				for(int k = 0; k < n.inputs.length(); k++)
				{
					((Input) n.inputs.get(k)).setValue(datum[k]);
				}

				n.performanceNode.setDesired(datum[datum.length - 1]);

				n.clearCache();

				for(int k = 0; k < n.weights.length(); k++)
						// Object wei : n.weights.toArray())
				{
					((Weight) n.weights.get(k)).update();
					// ((Weight) wei).update();
				}

				JayList performances = new JayList();
				performances.addLast(n.performanceNode.output());

				n.clearCache();
			}
		}
	}
}