package mylovelypaint;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class DrawingAndTypingPanel extends DrawingPanel implements KeyListener{
	private Font font; 
	private FontMetrics fm; 
	private int fontSize;
	private int latestSW = 0;
	public static final String FONT_NAME = "Serif";
//	public static final int FONT_SIZE = 0;
	public static final int FONT_STYLE = Font.PLAIN ;
//	public static final int FONT_SIZE = 5;
//	public static final int FONT_STYLE = 1 ;
	
	public DrawingAndTypingPanel() {
		super();
		fontSize = DrawingPanel.MEDIUM;
		font = new Font(FONT_NAME, FONT_STYLE, fontSize);
		fm = getFontMetrics(font);
		addKeyListener(this); //listen for keystrokes
	}
		
	@Override
	public void keyTyped(KeyEvent e){
		String s = String.valueOf(e.getKeyChar());
		System.out.println("Key "+ s + " has been pressed" );
		Graphics2D g = (Graphics2D)getGraphics();
		font = new Font(FONT_NAME, FONT_STYLE, this.getDrawingSize());
		fm = getFontMetrics(font);
		g.setFont(font);
		g.setColor(getColor());
		g.drawString(s, this.getLatestX()+ this.getLatestSW(), this.getLatestY());
		record(this.getLatestX() + this.getLatestSW(), getLatestY());
		setLatestSW(fm.stringWidth(s));
	}
		

	public int getLatestSW(){ //latest string width
		return latestSW;
	}
	
	public void setLatestSW(int x){ //latest string width
		latestSW = x;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
			
	}
		
	@Override
	public void keyReleased(KeyEvent e) {
		
	}	
	
}
