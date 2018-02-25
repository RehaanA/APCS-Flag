import java.awt.*;
import javax.swing.JPanel;

public class FlagFrame extends JPanel {
	/**
	 * The following are properties used in this class.
	 */
	private Color flagBlue = new Color(0x041e42);
	private Color flagRed = new Color(0xbf0d3e);
	private Star[] stars = new Star[50]; 
	
	/**
	 * This is the class's constructor, where the size of the window is set.
	 * In addition, the repaint method is called, which invokes the paintComponent method. 
	 */
	public FlagFrame() {
		this.setSize(700, 385);
		this.repaint();
	}
	
	/**
	 *This is the paintComponent method, and it takes in an instance of Graphics.
	 *In this method, the paint method is called, and the instance of Graphics is passed in as an actual parameter.
	 */
	public void paintComponent(Graphics g) {
		this.paint(g);
	}
	
	/**
	 * In this method, all of the drawing occurs.
	 * While the logic itself occurs in separate methods, those methods are called in this paint function.
	 */
	public void paint(Graphics g) {
		//Width and Height values of the JFrame
		int width = this.getBounds().width;
		int height = this.getBounds().height;
		
		Polygon rect = new Polygon(new int[]{0, 0, (int)(1.9 * height), (int)(1.9 * height)}, new int[]{0, (int)(width / 1.9), (int)(width / 1.9), 0}, 4);
		int rectWidth = rect.getBounds().width;
		int rectHeight = rect.getBounds().height;
		
		//These are the various methods that are called to draw the objects on the screen.
		this.drawStripes(rectWidth, rectHeight, g);
		this.drawUnion(rectWidth, rectHeight, width, g);
		this.initializeStarArray(width, rectHeight);
		this.drawStars(rectHeight, width, g);
	}	
	
	/**
	 * This method is called in the paint method, and draws the stripes of the flag in alternating color
	 * @param rectWidth
	 * @param rectHeight
	 * @param g
	 */
	public void drawStripes(int rectWidth, int rectHeight, Graphics g) {
		//For loop that iterates 13 times, once for each stripe.
		//This also accounts for the fact that the stripes alternate in color.
		for (int i = 0; i <= 12; i++) {
			if (i % 2 == 0) {			
				g.setColor(this.flagRed);
				if (i == 12) {
					g.fillRect(0, (int)((rectHeight * (1.0/13)) * i), (int)(rectHeight * 1.9), (int)((int)((rectHeight * (1.0/13)))));
				} else {
					g.fillRect(0, (int)((rectHeight * (1.0/13)) * i), (int)(rectHeight * 1.9), (int)(rectWidth/1.9));
				}
			} else {
				g.setColor(Color.WHITE);
				g.fillRect(0, (int)((rectHeight * (1.0/13)) * i), (int)(rectHeight * 1.9), (int)(rectWidth / 1.9));
			}
		}
	}
	
	/**
	 * This method is called in the paint method, and draws the union on the flag.
	 * The union is the blue region on the flag that contains all 50 stars.
	 * @param rectWidth
	 * @param rectHeight
	 * @param width
	 * @param g
	 */
	public void drawUnion(int rectWidth, int rectHeight, int width, Graphics g) {
		//The blue rectangle that contains all of the stars
		//In the upper right corner
		g.setColor(this.flagBlue);
		g.fillRect(0, 0, (int)(0.4 * width), (int)(rectHeight * (7.0/13)));
	}
	
	/**
	 * This method initializes the star array that was declared at the top of this class.
	 * It inserts 50 new star objects, and the outer radius, x-offset, and y-offset, are passed into the constructors.
	 * @param width
	 * @param rectHeight
	 */
	public void initializeStarArray(int width, int rectHeight) {
		for (int i = 0; i < 50; i++) {
			Star star = new Star(0.01621052632 * width, 0.03315789474 * width - (0.01621052632 * width), 0.02842105263 * rectHeight + 29);
			this.stars[i] = star;
		}
	}
	
	/**
	 * This method is called in the paint method, and draws the stars themselves. It utilizes for loops to generalize every row.
	 * In terms of improvement, it would be great if these 9 for loops could be generalized even further, to utilize good programming practice.
	 * @param rectHeight
	 * @param width
	 * @param g
	 */
	public void drawStars(int rectHeight, int width, Graphics g) {
		//1st Row
		for (int i = 0; i < 6; i++) {
			if (i != 0) {
				this.stars[i].xOffset = this.stars[i-1].xOffset + (0.03315789474 * width) * 2;
				this.stars[i].calculateXOffset();
				this.stars[i].yOffset = this.stars[i-1].yOffset;
				this.stars[i].calculateYOffset();
				Star star = this.stars[i];
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			} else {
				Star star = stars[i];
				star.calculateXOffset();
				star.yOffset = /*0.02842105263 * height - 30;*/ 19.32/rectHeight - 19.32;
				star.calculateYOffset();
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			}
		}
		//2nd Row
		for (int i = 6; i < 11; i++) {
			if (i != 6) {
				this.stars[i].xOffset = this.stars[i-1].xOffset + (0.03315789474 * width) * 2;
				this.stars[i].calculateXOffset();
				this.stars[i].yOffset = this.stars[i-1].yOffset;				
				this.stars[i].calculateYOffset();
				Star star = this.stars[i];
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			} else {
				Star star = stars[i];
				star.xOffset += 0.03315789474 * width; 
				star.calculateXOffset();
				star.yOffset += 0.02842105263 * rectHeight - 50;
				star.calculateYOffset();
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			}
		}
		
		//3rd Row
		for (int i = 11; i < 17; i++) {
			if (i != 11) {
				this.stars[i].xOffset = this.stars[i-1].xOffset + (0.03315789474 * width) * 2;
				this.stars[i].calculateXOffset();
				this.stars[i].yOffset = this.stars[i-1].yOffset;
				this.stars[i].calculateYOffset();
				Star star = this.stars[i];
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			} else {
				Star star = stars[i];
				star.calculateXOffset();
				star.yOffset += (this.stars[i-1].yOffset) + (0.02842105263 * rectHeight - 30);
				star.calculateYOffset();
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			}
		}
		//4th Row
		for (int i = 17; i < 22; i++) {
			if (i != 17) {
				this.stars[i].xOffset = this.stars[i-1].xOffset + (0.03315789474 * width) * 2;
				this.stars[i].calculateXOffset();
				this.stars[i].yOffset = this.stars[i-1].yOffset;
				this.stars[i].calculateYOffset();
				Star star = this.stars[i];
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			} else {
				Star star = stars[i];
				star.xOffset += 0.03315789474 * width; 
				star.calculateXOffset();
				star.yOffset += (this.stars[i-1].yOffset) + (0.02842105263 * rectHeight - 30);
				star.calculateYOffset();
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			}
		}
		//5th Row
		for (int i = 22; i < 28; i++) {
			if (i != 22) {
				this.stars[i].xOffset = this.stars[i-1].xOffset + (0.03315789474 * width) * 2;
				this.stars[i].calculateXOffset();
				this.stars[i].yOffset = this.stars[i-1].yOffset;
				this.stars[i].calculateYOffset();
				Star star = this.stars[i];
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			} else {
				Star star = stars[i];
				star.calculateXOffset();
				star.yOffset += (this.stars[i-1].yOffset) + (0.02842105263 * rectHeight - 30);
				star.calculateYOffset();
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			}
		}
		//6th Row
		for (int i = 28; i < 33; i++) {
			if (i != 28) {
				this.stars[i].xOffset = this.stars[i-1].xOffset + (0.03315789474 * width) * 2;
				this.stars[i].calculateXOffset();
				this.stars[i].yOffset = this.stars[i-1].yOffset;
				this.stars[i].calculateYOffset();
				Star star = this.stars[i];
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			} else {
				Star star = stars[i];
				star.xOffset += 0.03315789474 * width; 
				star.calculateXOffset();
				star.yOffset += (this.stars[i-1].yOffset) + (0.02842105263 * rectHeight - 30);
				star.calculateYOffset();
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			}
		}
		//7th Row
		for (int i = 33; i < 39; i++) {
			if (i != 33) {
				this.stars[i].xOffset = this.stars[i-1].xOffset + (0.03315789474 * width) * 2;
				this.stars[i].calculateXOffset();
				this.stars[i].yOffset = this.stars[i-1].yOffset;
				this.stars[i].calculateYOffset();
				Star star = this.stars[i];
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			} else {
				Star star = stars[i];
				star.calculateXOffset();
				star.yOffset += (this.stars[i-1].yOffset) + (0.02842105263 * rectHeight - 30);
				star.calculateYOffset();
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			}
		}
		//8th Row
		for (int i = 39; i < 44; i++) {
			if (i != 39) {
				this.stars[i].xOffset = this.stars[i-1].xOffset + (0.03315789474 * width) * 2;
				this.stars[i].calculateXOffset();
				this.stars[i].yOffset = this.stars[i-1].yOffset;
				this.stars[i].calculateYOffset();
				Star star = this.stars[i];
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			} else {
				Star star = stars[i];
				star.xOffset += 0.03315789474 * width; 
				star.calculateXOffset();
				star.yOffset += (this.stars[i-1].yOffset) + (0.02842105263 * rectHeight - 30);
				star.calculateYOffset();
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			}
		}
		//9th Row
		for (int i = 44; i < 50; i++) {
			if (i != 44) {
				this.stars[i].xOffset = this.stars[i-1].xOffset + (0.03315789474 * width) * 2;
				this.stars[i].calculateXOffset();
				this.stars[i].yOffset = this.stars[i-1].yOffset;
				this.stars[i].calculateYOffset();
				Star star = this.stars[i];
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			} else {
				Star star = stars[i];
				star.calculateXOffset();
				star.yOffset += (this.stars[i-1].yOffset) + (0.02842105263 * rectHeight - 30);
				star.calculateYOffset();
				Polygon starPolygon = new Polygon(star.getXCoords(), star.getYCoords(), 10);
				g.setColor(Color.WHITE);
				g.fillPolygon(starPolygon);
			}
		}
	}
}