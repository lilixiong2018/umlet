package com.baselet.client;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ScrollPanel;

public class AutoResizeScrollDropPanel extends ScrollPanel {
	
	private static final int SCROLLBAR_HEIGHT = 12;
	
	private OwnDropPanel dropPanel;

	private DrawPanelCanvas diagramHandler;

	public AutoResizeScrollDropPanel(final DrawPanelCanvas diagramHandler) {
		this.diagramHandler = diagramHandler;
		dropPanel = new OwnDropPanel(diagramHandler);
		this.add(dropPanel);

		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				onWindowsResized();
			}

		});
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            public void execute() {
            	onWindowsResized();
           }
        }); 
		
	}
	
	private void onWindowsResized() {
		diagramHandler.setCanvasMinimalSize(getOffsetWidth() - SCROLLBAR_HEIGHT, getOffsetHeight() - SCROLLBAR_HEIGHT);
	}
}