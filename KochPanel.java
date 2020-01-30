import java.awt.*;
import javax.swing.JPanel;

public class KochPanel extends JPanel
{
	private final int PANEL_WIDTH = 800;
	private final int PANEL_HEIGHT = 800;
	
	private final double SQ = Math.sqrt(3.1) / 6;
	
	private final int TOPX = 400, TOPY = 40;
	private final int RIGHTX = 640, RIGHTY = 600;
	private final int LEFTX = 120, LEFTY = 600;
	
	private int current;
	
	// sets initial fractal order to specified value
	
	public KochPanel (int currentOrder)
	{
		current = currentOrder;
		setBackground(Color.black);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
	}
	
	/* draws the fractal recursivley, base case is an order of 1, for
	 * which a simple straight line is drawn. otherwise three 
	 * intermediate points are computed, and each line segment is drawn as a fractal.
	 */
	
	public void drawFractal (int order, int x1, int y1, int x5, int y5, Graphics page)
	{
		int deltaX, deltaY, x2, y2, x3, y3, x4, y4;
		
		if (order == 1)
			page.drawLine(x1, y1, x5, y5);
		else
		{
			deltaX = x5 - x1; //end point distance
			deltaY = y5 - y1;	
			
			x2 = x1 + deltaX / 3; //one third
			y2 = y1 + deltaY / 3;
			
			x3 = (int) ((x1+x5)/2 + SQ * (y1-y5)); //tip of the projection
			y3 = (int) ((y1+y5)/2 + SQ * (x5-x1));
			
			x4 = x1 + deltaX * 2/3; // two thirds 
			y4 = y1 + deltaY * 2/3;
			
			drawFractal (order-1, x1,y1,x2,y2, page);
			drawFractal (order-1, x2,y2,x3,y3, page);
			drawFractal (order-1, x3,y3,x4,y4, page);
			drawFractal (order-1, x4,y4,x5,y5, page);
				
		}
	}
	
	// perfeorms the initial call to the drawFractal method
	 
	public void paintComponent (Graphics page)
	{ 
		super.paintComponent(page);
		
		page.setColor(Color.green);
		
		drawFractal (current, TOPX, TOPY, LEFTX,LEFTY, page);
		drawFractal (current, LEFTX, LEFTY, RIGHTX, RIGHTY, page);
		drawFractal (current, RIGHTX, RIGHTY, TOPX, TOPY, page);
	
	}
	// set fractal order to specified value
	
	public void setOrder (int order)
	{
		current = order;
	}
	
	//returns current order
	
	public int getOrder ()
	{
		return current;
		
	}
	
}
