public interface DifferentiableElement
{
	public abstract Double output();

	public abstract Double dOutdX();

	public void clearCache();
}