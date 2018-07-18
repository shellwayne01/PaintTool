package mylovelypaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel implements MouseListener,MouseMotionListener{

	//Would be easier to have a scale or to get user input(int) for size
	public static final int EXTRA_SMALL = 10;
	public static final int SMALL = 25;
	public static final int MEDIUM = 50;
	public static final int LARGE = 75;
	public static final int EXTRA_LARGE = 100;
	public static final int [] sizeOptions = {EXTRA_SMALL, SMALL, MEDIUM, LARGE, EXTRA_LARGE};
	//public static String [] sizeOptionsStrings = new String [sizeOptions.length];
	
	public static final String NO_SHAPE = "none";
	public static final String CIRCLE = "circle";
	public static final String SQUARE = "square";
	public static final String RECTANGLE = "rectangle";
	public static final String TRIANGLE = "triangle";
	public static final String LINE = "line";
	public static final String [] shapeNames = {NO_SHAPE, CIRCLE, SQUARE, RECTANGLE, TRIANGLE, LINE};
	
	private int xStart=0, yStart=0;
	private int[] xcoords, ycoords; //for custom polygon
	private int size;
	private Color color;
	private String shape;
	private int brushThickness;
	
	public String coords; 
	public JLabel displayCoords = new JLabel("Coordinates: ");
	
	public JButton clearBtn = new JButton("Clear");
	
	public DrawingPanel(){
		setBackground(Color.WHITE);
		setSize(100,100);
		color = Color.RED;//default
		size = MEDIUM;
		shape = NO_SHAPE;
		brushThickness = 1;
		//sizeOptionsStrings = this.getsizeOptionsStrings();
		addMouseListener(this);//make the JPanel listen for mouse events aka clicks
		addMouseMotionListener(this);//make the JPanel listen for MORE mouse events
	}
	private void drawShape(int x, int y){
		//Graphics g = getGraphics();
		Graphics2D g = (Graphics2D)getGraphics();
		g.setColor(color);
		g.setStroke(new BasicStroke(brushThickness));
		switch(shape){
		case CIRCLE:
			g.drawOval(x - size/2, y - size/2, size, size);
			//g.drawOval(x, y, size, size);//incorrect drawing
			break;
		case SQUARE:
			g.drawRect(x - size/2, y - size/2, size, size); // or fillRect 
			break;
		case RECTANGLE: 
			g.drawRect(x, y, size+30, size); //generic rectangle
			break;
		case TRIANGLE: 
			int[] xcoords = {x, x-size, x+size};
			int[] ycoords = {y, y+size, y+size};
			g.drawPolygon(xcoords, ycoords, 3);
			break;
		case LINE: 
			g.drawLine(x, y, x+size, y); ;
			break;
		default:
			shape = NO_SHAPE;
			g.drawLine(x,y,x,y);//hahahaha just a dot
		}
		g.dispose();
	}
	
	protected void record(int x, int y){
		xStart = x;
		yStart = y;
		//System.out.println("Just recorded x="+x+" y= "+y);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		drawShape(x, y);
		System.out.println("mouse clicked"); 
	}
	@Override
	public void mousePressed(MouseEvent e) {
		record(e.getX(),e.getY()); //store the x,y
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
//		int xEnd = e.getX();
//		int yEnd = e.getY();
//		Graphics g = getGraphics();
//		g.setColor(color);
//		g.drawLine(xStart, yStart, xEnd, yEnd);
//		g.dispose();	
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		requestFocus(); 
		record(e.getX(), e.getY());	
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		int xEnd = e.getX();
		int yEnd = e.getY();
		
		Graphics2D g = (Graphics2D)getGraphics();
		g.setStroke(new BasicStroke(brushThickness));
		g.setColor(color);
		g.drawLine(xStart, yStart, xEnd, yEnd);
		g.dispose();
		record(xEnd,yEnd); //store the x,y
		updateCoords();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		record(e.getX(), e.getY());
		updateCoords();
		
	}
	public Color getColor(){
		return color;
	}
	protected void setColor(Color c){
		color = c;
	}
	protected void setShape(String theShape){
		if(theShape.equalsIgnoreCase(CIRCLE)){
			shape = CIRCLE;
		}
		else if(theShape.equalsIgnoreCase(SQUARE)){
			shape = SQUARE;
		}
		else if(theShape.equalsIgnoreCase(RECTANGLE)){
			shape = RECTANGLE;
		}
		else if(theShape.equalsIgnoreCase(TRIANGLE)){
			shape = TRIANGLE;
		}
		else if(theShape.equalsIgnoreCase(LINE)){
			shape = LINE;
		}
		else{
			shape = NO_SHAPE;
			System.out.println("invalid shape was entered "+ theShape);
		}
	}
	
	
	public int getBrushStroke(){
		return brushThickness;
	}
	
	public void setBrushStroke(int x){
		brushThickness = x;
		System.out.println("Brush thickness value changed to "+ brushThickness);
	}
	
	public int getDrawingSize(){
		return this.size;
	}

	protected void setDrawingSize(int s){
		size = s;
	}
	
	public int getLatestX(){
		return this.xStart;
	}
	
	public int getLatestY() {
		return this.yStart;
	}
	
	public void updateCoords(){
		coords = "Coordinates: "+ getLatestX()+ ", "+ getLatestY();
		displayCoords.setText(coords); 
		System.out.println(coords);
	}
	
	public void clear(){
		removeAll() ;
		repaint();
	}
	
//	public String[] getsizeOptionsStrings(){
//		for(int i=0; i<sizeOptions.length; i++) {
//		sizeOptionsStrings[i] = Integer.toString(sizeOptions[i]); //doesnt work like that. Doesnt pick up on variable names
//		}
//		return sizeOptionsStrings;
//	}
//	
	
	
	
	
	
	
}
