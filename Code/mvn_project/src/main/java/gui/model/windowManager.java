package gui.model;

import bll.logic.Authentication;
import gui.controller.IWindow;

/**
 * @version 1.0
 * @authors Bryan Curchod
 */
public class windowManager {
	
	private IWindow connectionFrame = null;
	private IWindow mainFrame = null;
	
	private windowManager() {}
	
	private static class Instance {
		
		static final windowManager instance = new windowManager();
	}
	
	public static windowManager getInstance() {
		
		return Instance.instance;
	}
	
	public void setConnectionFrame(IWindow connectionFrame) {
		
		this.connectionFrame = connectionFrame;
	}
	
	public void setMainFrame(IWindow mainFrame) {
		
		this.mainFrame = mainFrame;
	}
	
	public boolean hasMainframe() {
		
		return mainFrame != null;
	}
	
	public void displayMainFrame() {
		
		if (mainFrame != null && connectionFrame != null) {
			connectionFrame.hide();
			mainFrame.show();
		}
	}
	
	public void displayConnectionFrame() {
		
		if (mainFrame != null && connectionFrame != null) {
			connectionFrame.show();
			mainFrame.hide();
			Authentication.disconnect();
		}
	}
}