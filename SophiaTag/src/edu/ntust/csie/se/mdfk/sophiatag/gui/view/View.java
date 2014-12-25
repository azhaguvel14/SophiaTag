/**
 * 
 */
package edu.ntust.csie.se.mdfk.sophiatag.gui.view;

import javax.swing.JFrame;

import edu.ntust.csie.se.mdfk.sophiatag.SophiaTagConfig;
import edu.ntust.csie.se.mdfk.sophiatag.gui.controller.glue.MVCGlue;

/**
 * @author maeglin89273
 *
 */
public abstract class View {
	
	protected JFrame frame;
	/**
	 * 
	 */
	public View(int x, int y, int width, int height) {
		this.initializeFrame(x, y, width, height);
		this.buildView(this.getFrame());
	}
	
	private void initializeFrame(int x, int y, int width, int height) {
		frame = new JFrame();
		frame.setTitle(SophiaTagConfig.SOFTWARE_TITLE);
		frame.setBounds(x, y, width, height);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	protected abstract void buildView(JFrame frame);
	public abstract void bindEvent(final MVCGlue glue);
	
	public JFrame getFrame() {
		return this.frame;
	}
}
