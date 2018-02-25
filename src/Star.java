public class Star {
	//Properties that are utilized
	private int[] xc = new int[10];
	private int[] yc = new int[10];
	private double outerRadius;
	private double innerRadius;
	public double xOffset;
	public double yOffset;
	
	/**
	 * This is the class's constructor, and it calculates the star's x-offset, y-offset, points, and radii.
	 * @param outerRadius
	 * @param xOffset
	 * @param yOffset
	 */
	public Star(double outerRadius, double xOffset, double yOffset) {
		this.outerRadius = outerRadius;
		this.calculatePoints();
		this.yOffset = yOffset;
		this.xOffset = xOffset;
		this.calculateXOffset();
		this.calculateYOffset();
	}
	
	/**
	 * This method is called in the constructor, and calculates the points, using the Math package.
	 */
	public void calculatePoints() {
		this.innerRadius = this.outerRadius * 0.382;
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				//Outer Radius
				this.xc[i] = (int)(this.outerRadius * Math.cos((i/2.0) * ((2 * Math.PI) / 5) + Math.toRadians(-18)));
				this.yc[i] = (int)(this.outerRadius * Math.sin((i/2.0) * ((2 * Math.PI) / 5) + Math.toRadians(-18))); 
			} else {
				//Inner Radius
				this.xc[i] = (int)(this.innerRadius * Math.cos((i/2.0) * ((2 * Math.PI) / 5) + Math.toRadians(-18)));
				this.yc[i] = (int)(this.innerRadius * Math.sin((i/2.0) * ((2 * Math.PI) / 5) + Math.toRadians(-18)));
			}
		}
	}
	
	/**
	 * This method loops through the xc array, and adds an x-offset.
	 */
	public void calculateXOffset() {
		for (int i = 0; i < 10; i++) {
			this.xc[i] += this.xOffset;
		}
	}
	
	/**
	 * This method loops through the yc array, and adds a y-offset.
	 */
	public void calculateYOffset() {
		for (int i = 0; i < 10; i++) {
			this.yc[i] += this.yOffset;
		}
	}
	
	/**
	 * This method returns the x-coordinate array
	 * @return
	 */
	public int[] getXCoords() {
		return this.xc;
	}
	
	/**
	 * This method returns the y-coordinate array
	 * @return
	 */
	public int[] getYCoords() {
		return this.yc;
	}
}