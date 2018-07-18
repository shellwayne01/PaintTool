package mylovelypaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI_Frame extends JFrame{

	//private DrawingPanel drawingPanel1;//, drawingPanel2;
	private DrawingAndTypingPanel drawingAndTypingPanel1;
	
	public GUI_Frame(){
		setSize(800,725);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		JPanel mainJP = new JPanel();
		mainJP.setLayout(new BorderLayout());
//		mainJP.setLayout(new GridLayout(1,2));
//		drawingPanel1 = new DrawingPanel();
//		drawingPanel1.setBackground(Color.YELLOW);
//		drawingPanel2 = new DrawingPanel();
		drawingAndTypingPanel1 = new DrawingAndTypingPanel();
		
		ColorChooserPanel colorChooserJP = new ColorChooserPanel();
//		JPanel eastJP = new JPanel(); 
		JPanel westJP = new JPanel();
		JPanel southJP = new JPanel();
		
//		eastJP.setLayout(new GridLayout(3,1));
		westJP.setLayout(new GridLayout(3,1));
		southJP.setLayout(new GridLayout(2,1));
		
		ShapeSelectionPanel shapeSelectionPanel = new ShapeSelectionPanel();
		SizeSelectionPanel sizeSelectionPanel = new SizeSelectionPanel();
		BrushSelectionPanel brushSelectionPanel = new BrushSelectionPanel();
		
		westJP.add(shapeSelectionPanel);
		westJP.add(sizeSelectionPanel);
		westJP.add(brushSelectionPanel);
		
//		JLabel displayCoords = drawingAndTypingPanel1.displayCoords;
//		southJP.add(displayCoords);
		ExtraPanel extraJP = new ExtraPanel();
		southJP.add(extraJP);

		
		mainJP.add(colorChooserJP, BorderLayout.NORTH);	
		mainJP.add(westJP, BorderLayout.WEST);
		mainJP.add(southJP, BorderLayout.SOUTH);
//		mainJP.add(eastJP, BorderLayout.EAST);
//		mainJP.add(drawingPanel1, BorderLayout.CENTER);
//		mainJP.add(drawingPanel2);
		mainJP.add(drawingAndTypingPanel1, BorderLayout.CENTER);
		add(mainJP); 
	}
	
	private class ColorChooserPanel extends JPanel implements ActionListener{
		private ImageIcon iconImg; //for icon
		private JLabel icon;
		private JButton jb;
		private JColorChooser colChooser;
		private JButton clrjb;
		
		public ColorChooserPanel(){
			Color customColor = Color.decode("#DBDBDB");
			setBackground(customColor);
			colChooser = new JColorChooser();
			iconImg = new ImageIcon(getClass().getResource("color-palette_20.png")); //what's the easier way?
			icon = new JLabel(iconImg);
			int w = iconImg.getIconWidth();
	        int h = iconImg.getIconHeight();
	        System.out.println("Width= "+w+"\nHeight= "+h);
			//Image tempImage = jb.getImage(); 
			
			jb = new JButton("Color Picker");
			clrjb = drawingAndTypingPanel1.clearBtn;
			//
			jb.addActionListener(this);
			clrjb.addActionListener(this);
			
			add(icon);
			add(jb);
			add(clrjb);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton chosenbtn = (JButton) e.getSource();
			
//			Color chosenColor = 
//					colChooser.showDialog(null, 
//							"Choose Color", 
//							drawingPanel1.getColor());
//			drawingPanel1.setColor(chosenColor);
			if(chosenbtn == jb) {
			Color chosenColor = 
					colChooser.showDialog(null, 
							"Choose Color", 
							drawingAndTypingPanel1.getColor());
			drawingAndTypingPanel1.setColor(chosenColor);
			}
			else if(chosenbtn == clrjb) {
				drawingAndTypingPanel1.clear();
			}
			
//			Component[] comps = this.getParent().getComponents();
//			for(int i=0; i<comps.length; i++){
//				if(!comps[i].equals(drawingPanel1)){
//					comps[i].setBackground(chosenColor);
//				}
//			}
		}
	}

	
	private class ShapeSelectionPanel extends JPanel implements ActionListener{
		private JRadioButton [] radBtnArr;
		private ButtonGroup radBtnGroup;
		private final int NUM_SHAPES = DrawingPanel.shapeNames.length;
		
		
		public ShapeSelectionPanel(){
//			Color customColor1 = new Color(234, 182, 255)
//			Color customColor1 = Color.decode("#DEC8FA");
			Color customColor2 = Color.decode("#E0E0E0");
			setBackground(customColor2);
			radBtnGroup = new ButtonGroup();
			radBtnArr = new JRadioButton[NUM_SHAPES];
			setLayout(new GridLayout(NUM_SHAPES,1));
			for(int i=0; i<radBtnArr.length; i++){
				radBtnArr[i] = new JRadioButton(DrawingPanel.shapeNames[i]);//initialized and put a String of text
				radBtnArr[i].setActionCommand(DrawingPanel.shapeNames[i]);//set the ActionCommand
				radBtnArr[i].addActionListener(this);//make it listen for events to trigger the actionPerformed
				radBtnGroup.add(radBtnArr[i]);//add to the group so only 1 is selected at a time
				add(radBtnArr[i]);//add the radio button to the ShapeSelection JPanel
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String actCmd = e.getActionCommand();
//			drawingPanel1.setShape(actCmd);
			drawingAndTypingPanel1.setShape(actCmd);
		}
	}
	
	private class SizeSelectionPanel extends JPanel implements ActionListener{
		private JRadioButton [] radBtnArr;
		private ButtonGroup radBtnGroup;
		private final int NUM_SIZES = DrawingPanel.sizeOptions.length;
		
		public SizeSelectionPanel(){
			Color customColor = Color.decode("#6A73FA");
//			setBackground(Color.GREEN);
			setBackground(customColor);
			radBtnGroup = new ButtonGroup();
			radBtnArr = new JRadioButton[NUM_SIZES];
			setLayout(new GridLayout(NUM_SIZES,1));
			for(int i=0; i<radBtnArr.length; i++){
				radBtnArr[i] = new JRadioButton(Integer.toString(DrawingPanel.sizeOptions[i]));//initialized and put a String of text
				//radBtnArr[i].setActionCommand(Integer.toString(DrawingPanel.sizeOptions[i]));//set the ActionCommand
				radBtnArr[i].addActionListener(this);//make it listen for events to trigger the actionPerformed
				radBtnGroup.add(radBtnArr[i]);//add to the group so only 1 is selected at a time
				add(radBtnArr[i]);//add the radio button to the SizeSelection JPanel
		}
	}

		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton btn = (JRadioButton)e.getSource();
			int size = Integer.parseInt((btn.getText()));
//			drawingPanel1.setSize(actCmd);
			drawingAndTypingPanel1.setDrawingSize(size);
		}
	}
	
	private class BrushSelectionPanel extends JPanel implements ChangeListener{
		private final int THIN = 1;
		private final int MEDIUM = 5;
		private final int THICK = 10;
		private JSlider brushSlider;
		
		public BrushSelectionPanel() {
			brushSlider = new JSlider(JSlider.VERTICAL, THIN, THICK, MEDIUM); //min,max,default values
			brushSlider.setMajorTickSpacing(1);
			brushSlider.setPaintTicks(true);
			brushSlider.addChangeListener(this);
			
			Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
			labelTable.put( new Integer( THIN ), new JLabel("Thin") );
//			labelTable.put( new Integer( MEDIUM ), new JLabel("Medium") );
			labelTable.put( new Integer( THICK), new JLabel("Thick") );
			brushSlider.setLabelTable( labelTable );
			brushSlider.setPaintLabels(true);

			add(brushSlider);	
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			JSlider brushSlider = (JSlider)e.getSource();
		    if (!brushSlider.getValueIsAdjusting()) { //state changes when slider isn't being adjusted
		        int brushStroke = (int)brushSlider.getValue();
		        drawingAndTypingPanel1.setBrushStroke(brushStroke);
		    }
		}
	}
	
	//Come back to this later
	private class ExtraPanel extends JPanel{	
		public JLabel displayCoords = new JLabel("Coordinates");
		
		public ExtraPanel(){
			Color customColor = Color.decode("#DBDBDB");
			//setBackground(customColor);
			displayCoords = drawingAndTypingPanel1.displayCoords; 
			add(displayCoords);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
