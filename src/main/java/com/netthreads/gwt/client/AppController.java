package com.netthreads.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasWidgets;
import com.netthreads.gwt.client.common.TrafficData;
import com.netthreads.gwt.client.event.SelectItemEvent;
import com.netthreads.gwt.client.event.SelectItemEventHandler;
import com.netthreads.gwt.client.event.SelectMainViewEvent;
import com.netthreads.gwt.client.event.SelectMainViewEventHandler;
import com.netthreads.gwt.client.presenter.MainViewPresenter;
import com.netthreads.gwt.client.presenter.Presenter;
import com.netthreads.gwt.client.service.TrafficDataService;
import com.netthreads.gwt.client.view.MainView;
import com.netthreads.gwt.client.view.MainViewImpl;

/**
 * MVP - Controller.
 * 
 */
public class AppController implements Presenter, ValueChangeHandler<String>
{
	private final HandlerManager eventBus;
	private HasWidgets container;
	private TrafficDataService service;

	private MainView mainView;

	/**
	 * Construct application controller.
	 * 
	 * @param service
	 * @param eventBus
	 */
	public AppController(TrafficDataService service, HandlerManager eventBus)
	{
		mainView = null;

		this.eventBus = eventBus;
		this.service = service;

		bind();
	}

	/**
	 * Bind handlers.
	 * 
	 */
	private void bind()
	{
		// Add this as handler for browser history changes.
		History.addValueChangeHandler(this);

		eventBus.addHandler(SelectMainViewEvent.TYPE, new SelectMainViewEventHandler()
		{
			public void select(SelectMainViewEvent event)
			{
				doSelectMainView();
			}
		});

		eventBus.addHandler(SelectItemEvent.TYPE, new SelectItemEventHandler()
		{
			public void onSelectItem(SelectItemEvent event)
			{
				doSelectItem(event.getData());
			}
		});
		
	}

	/**
	 * Handle select main view.
	 * 
	 */
	private void doSelectMainView()
	{
		History.newItem("main", false);
		if (mainView == null)
		{
			mainView = new MainViewImpl();
		}

		Presenter presenter = new MainViewPresenter(service, eventBus, mainView);

		presenter.go(container);
	}

	/**
	 * Handle item selection.
	 * 
	 * @param item
	 */
	private void doSelectItem(TrafficData item)
	{
		if (mainView == null)
		{
			mainView = new MainViewImpl();
		}

		// Pass selection to all view elements.
		mainView.setItemSelected(item);
	}
	
	/**
	 * Change view.
	 * 
	 */
	public void go(final HasWidgets container)
	{
		this.container = container;

		if (History.getToken().equals(""))
		{
			History.newItem("main");
		}
		else
		{
			History.fireCurrentHistoryState();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Called on history changed.
	 * 
	 * @see com.google.gwt.event.logical.shared.ValueChangeHandler#onValueChange(com.google.gwt.event.logical.shared.
	 * ValueChangeEvent)
	 */
	public void onValueChange(ValueChangeEvent<String> event)
	{
		String token = event.getValue();

		if (token != null)
		{
			if (token.equals("main"))
			{
				GWT.runAsync(new RunAsyncCallback()
				{
					public void onFailure(Throwable caught)
					{
						Window.alert(caught.getLocalizedMessage());
					}

					public void onSuccess()
					{
						doSelectMainView();
					}
				});
			}
			else if (token.equals("help"))
			{
				// TODO
			}
		}
	}
}
