import java.util.*;

public class ANN
{
	public static void main(String[] args)
	{
		int layer = 5;
		int width = 10;

		// Making a 5 x 5 neural net
		Neuron[][] ann = new Neuron[layer][width];

		for(int j = 0; j < layer; j++)
		{
			for(int k = 0; k < width; k++)
			{
				ann[j][k] = new Neuron();
			}
		}

		// For each column other than the last
		for(int j = 0; j < layer - 1; j++)
		{
			// And for each neurons in those columns
			for(int k = 0; k < width; k++)
			{
				// And for each neurons in the next column for each of those neurons
				for(int l = 0; l < width; l++)
				{
					// Add child and weight of the output given to it by this
					ann[j][k].addChild(ann[j + 1][l], new Random().nextDouble());
				}
			}
		}

		for(int j = 0; j < width; j++)
		{
			ann[0][j].setInput(10.0, 1.0);
		}

		for(int j = 0; j < width; j++)
		{
			System.out.println("OUTPUT " + j + " : " + ann[4][j].calculateOutput());
		}
		System.out.println();

		for(int j = 0; j < width; j++)
		{
			ann[4][j].setDesired(10.0);
		}

		for(int l = 0; l < 10; l++)
		{
			// For each column other than the last
			for(int j = 0; j < layer; j++)
			{
				// And for each neurons in those columns
				for(int k = 0; k < width; k++)
				{
					ann[j][k].calculateWeight();
				}
			}

			for(int j = 0; j < width; j++)
			{
				System.out.println("OUTPUT " + j + " : " + ann[4][j].calculateOutput());
			}
			System.out.println();
		}
	}
}