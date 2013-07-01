package com.netthreads.gwt.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.netthreads.gwt.client.common.TrafficData;
import com.netthreads.gwt.client.event.SelectItemEvent;
import com.netthreads.gwt.client.service.TrafficDataService;
import com.netthreads.gwt.client.view.MainView;

/**
 * The main view Presenter.
 * 
 */
public class MainViewPresenter implements Presenter, MainView.Presenter
{
	private TrafficDataService service;
	private final HandlerManager eventBus;
	private final MainView view;

	/**
	 * Construct presenter.
	 * 
	 * @param service
	 * @param eventBus
	 * @param view
	 */
	public MainViewPresenter(TrafficDataService service, HandlerManager eventBus, MainView view)
	{
		this.service = service;
		this.eventBus = eventBus;
		this.view = view;
		this.view.setPresenter(this);
	}

	/**
	 * On item clicked.
	 * 
	 */
	@Override
	public void onItemSelected(TrafficData item)
	{
		// Broadcast selection event.
		eventBus.fireEvent(new SelectItemEvent(item));
	}

	/**
	 * Assign view to container and update view data.
	 * 
	 */
	@Override
	public void go(HasWidgets container)
	{
		container.clear();
		container.add(view.asWidget());

		// Update view data.
		service.getTrafficData(view);
	}

}
