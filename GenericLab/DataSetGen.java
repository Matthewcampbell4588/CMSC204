
public class DataSetGen<T> {
	private double sum;
	private Measurable maximum;
	private int count;

	/**
	      Constructs an empty data set.
	 */
	public DataSetGen()
	{
		sum = 0;
		count = 0;
		maximum = null;
	}

	/**
	      Adds a data value to the data set.
	      @param x a data value
	 */
	public void add(Measurable x)
	{
		sum = sum + x.getMeasure();
		if (count == 0 || maximum.getMeasure() < x.getMeasure())
			maximum = x;
		count++;
	}

	/**
	      Gets the average of the added data.
	      @return the average or 0 if no data has been added
	 */
	public double getAverage()
	{
		double p=0;

		if (count == 0) 
			return 0;
		else {
		p= (int)((sum / count)*1000);
		p=p/1000;
			return p ;
					
		}
	}

	/**
	      Gets the largest of the added data.
	      @return the maximum or 0 if no data has been added
	 */
	public T getMaximum()
	{
		return (T) maximum;
	}
}
