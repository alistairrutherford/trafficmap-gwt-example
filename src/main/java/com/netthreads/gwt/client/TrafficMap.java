package com.netthreads.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;
import com.netthreads.gwt.client.service.TrafficDataService;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class TrafficMap implements EntryPoint
{
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
		TrafficDataService service = GWT.create(TrafficDataService.class);
		HandlerManager eventBus = new HandlerManager(null);
		AppController appViewer = new AppController(service, eventBus);

		appViewer.go(RootPanel.get());
	}

}
