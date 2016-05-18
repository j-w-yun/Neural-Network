abstract class DifferentiableElement
{
	abstract double output();
	abstract double dOutdX(Weight element);
	abstract String getName();
}