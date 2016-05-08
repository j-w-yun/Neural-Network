public class ValuedElement
{
	String name;
	Double value;

	public ValuedElement(String name, Double value)
	{
		this.name = name;
		this.value = value;
	}

	public void setValue(Double value)
	{
		this.value = value;
	}

	public Double getValue()
	{
		return value;
	}

	public String getName()
	{
		return name;
	}

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