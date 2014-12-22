package edu.ntust.csie.se.mdfk.sophiatag.gui.custom.tagbutton;

import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public abstract class TagButtonUIUtility {
	static final double RADIUS = 7;
	static final float STROKE_WIDTH = 1f;
	static final float FOCUSED_STROKE_WIDTH = 1.5f;
	
	static final RenderingHints HINTS = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	static final Color DARK_BLUE = new Color(0x0e3d85);
	static Shape getTagPartShape(boolean isLeftPart, double x, double y, double width, double height) {
		return getTagPartShape(isLeftPart, x, y, width, height, STROKE_WIDTH);
	}
	
	static Shape getTagPartShape(boolean isLeftPart, double x, double y, double width, double height, float strokeWidth) {
		double strokePadding = strokeWidth / 2;
		double drawPadding = strokePadding + RADIUS;
		y += strokePadding;
		height -= strokeWidth;
		
		RoundRectangle2D.Double roundRect = new RoundRectangle2D.Double(x + strokePadding, y,
				width - 2 * strokePadding, height, RADIUS, RADIUS);
		
		Area borderShape = new Area(roundRect);
		
		width -= drawPadding;
		
		Rectangle2D.Double rect;
		if (isLeftPart) {
			 rect = new Rectangle2D.Double(x + drawPadding, y, width, height);
		} else {
			rect = new Rectangle2D.Double(x + strokePadding, y, width, height);
		}
		
		borderShape.add(new Area(rect));
		
		return borderShape;
	}
	
	static Shape getRoundedRectangle(double x, double y, double width, double height) {
		return getRoundedRectangle(x, y, width, height, STROKE_WIDTH);
	}
	
	static Shape getRoundedRectangle(double x, double y, double width, double height, float strokeWidth) {
		strokeWidth /= 2;
		RoundRectangle2D.Double roundRect = new RoundRectangle2D.Double(x + strokeWidth, y + strokeWidth,
				width - 2 * strokeWidth, height - 2 * strokeWidth, RADIUS, RADIUS);
		
		return roundRect;
	}
}