/**
*	@author Jaewan Yun (Jay50@pitt.edu)
*	@version 1.0.0
*/

public class ValuedElement
{
	String name;
	Double value;

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public ValuedElement(String name, Double value)
	{
		// this.name = name;
		// this.value = value;
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public void setValue(Double value)
	{
		this.value = value;
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public Double getValue()
	{
		return value;
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public String getName()
	{
		return name;
	}

	/**
	*	@since 1.0
	*	@author Jaewan Yun (Jay50@pitt.edu)
	*/
	public String toString()
	{
		return ("(" + name + ")" + value.toString());
	}

	// // Tester
	// public static void main(String[] args)
	// {
	// 	ValuedElement ve = new ValuedElement("W1", -1.3);
	// 	System.out.println(ve.getName());
	// 	System.out.println(ve.getValue());
	// 	ve.setValue(-2.5);
	// 	System.out.println(ve.getValue());
	// 	System.out.println(ve);
	// }
}