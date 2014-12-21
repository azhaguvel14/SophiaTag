package edu.ntust.csie.se.mdfk.sophiatag.gui.tagbutton;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

public class TagButtonUI extends BasicButtonUI {
		
		private static final int NORMAL = 0, HOVER = 1, PRESSED = 2, DISABLED = 3;
		
		private static final Color[] COLORS = {new Color(0x196eef), new Color(0x2f7cf0), new Color(0x155ECC), new Color(0xFCFCFC)};
		
		private final boolean leftPart;
		private final Color[] buttonColors;
		
		public TagButtonUI(boolean leftPart) {
			this.leftPart = leftPart;
			this.buttonColors = COLORS;
//			this.buttonColors = this.leftPart ? LEFT_PART_COLORS : RIGHT_PART_COLORS;
			
		}
		
		@Override
		public void paint(Graphics g, JComponent c) {
			AbstractButton button = (AbstractButton)c;
			ButtonModel model = button.getModel();
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHints(TagButtonUIUtility.HINTS);
			
			if (model.isEnabled()) {
				
				if (model.isArmed() && model.isPressed()) {
		            g2.setColor(this.buttonColors[PRESSED]);
		        } else if (model.isRollover()) {
		        	g2.setColor(this.buttonColors[HOVER]);
		     
		        } else {
		        	g2.setColor(this.buttonColors[NORMAL]);
		        }
				
				g2.fill(TagButtonUIUtility.getTagPartShape(this.leftPart, 0, 0, button.getWidth(), button.getHeight()));
			} else {
				g2.setColor(this.buttonColors[DISABLED]);
				g2.fill(TagButtonUIUtility.getRoundedRectangle(0, 0, button.getWidth(), button.getHeight()));
			}
			
			this.paintText(g2, button, 0, 0, button.getWidth(), button.getHeight());
		}	
		
		private Point2D.Double getTextPlacement(AbstractButton b, Graphics2D g2, String progressString, double x, double y, double width, double height) {
	    	
	    	g2.setFont(b.getFont());
	    	FontMetrics fm=g2.getFontMetrics();
	    
	    	int ascent=fm.getAscent();
	    	int descent=fm.getDescent();
	    	
	    	int stringWidth = fm.stringWidth(progressString);
	    	
	    	double stringX = x + (width-stringWidth) / 2;
	    	double stringY = y + (height - (ascent + descent)) / 2 + ascent;
	    	return new Point2D.Double(stringX,stringY);
		}
		
		private void paintText(Graphics2D g2, AbstractButton b, double x, double y, double width, double height){
			Point2D.Double stringLocation = getTextPlacement(b, g2, b.getText(),x, y, width, height);
	    	g2.setColor(b.getForeground());
	        g2.drawString(b.getText(), (float)(stringLocation.getX()), (float)(stringLocation.getY()));
	        
	    }
	}