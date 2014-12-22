package edu.ntust.csie.se.mdfk.sophiatag.gui.custom.tagbutton;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Stroke;
import java.beans.PropertyChangeEvent;

import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

public class TagButtonBorder extends AbstractBorder {
		
		public enum Mode {
			BUTTON, TEXTBOX
		}
	
		private static final int NORMAL = 0;
		private static final int DISABLED = 1;
//		
		private static final Color[] BUTTON_COLORS = {TagButtonUIUtility.DARK_BLUE, new Color(0x939393)};
		private static final Color[] TEXTBOX_COLORS = {new Color(0x13a2b3), TagButtonUIUtility.DARK_BLUE};
		private final Insets insets;
		
		private final boolean leftPart;
		private final int insetsPaddingX;
		private final int insetsPaddingY;
		
		private final Stroke stroke;
		private final float strokeWidth;
		private final Color[] borderColors;
		
		public TagButtonBorder(float strokeWidth, Color[] borderColors, int paddingX, int paddingY, boolean left) {
			this.borderColors = borderColors;
			double strokePadding = strokeWidth / 2.0;
			this.insetsPaddingX = (int) (strokePadding + paddingX);
			this.insetsPaddingY = (int) (strokePadding + paddingY);
			this.insets = new Insets(insetsPaddingY, insetsPaddingX, insetsPaddingY, insetsPaddingX);
			this.leftPart = left;
			this.stroke = new BasicStroke(strokeWidth);
			this.strokeWidth = strokeWidth;
		}
		
		@Override 
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHints(TagButtonUIUtility.HINTS);
			
			g2.setStroke(this.stroke);
			if (c.isEnabled()) {
				g2.setColor(this.borderColors[NORMAL]);
				g2.draw(TagButtonUIUtility.getTagPartShape(this.leftPart, x, y, width, height, this.strokeWidth));
			} else {
				g2.setColor(this.borderColors[DISABLED]);
				g2.draw(TagButtonUIUtility.getRoundedRectangle(x, y, width, height, this.strokeWidth));
			}
			
		}
		
		public Insets getBorderInsets(Component c) {
			return this.insets;
		}
		
		@Override
	    public Insets getBorderInsets(Component c, Insets insets) {
	        insets.set(insetsPaddingY, insetsPaddingX, insetsPaddingY, insetsPaddingX);
	        return insets;
	    }
		
		public static Border makeBorder(Mode mode, boolean isLeft) {
			switch (mode) {
			case BUTTON:
				return new TagButtonBorder(TagButtonUIUtility.STROKE_WIDTH, BUTTON_COLORS, (isLeft? 7: 5), 4, isLeft);
				
			case TEXTBOX:
				return new TagButtonBorder(TagButtonUIUtility.FOCUSED_STROKE_WIDTH, TEXTBOX_COLORS, (isLeft? 7: 5), 4, isLeft);
			}
			
			return null;
		}
		
		

	}