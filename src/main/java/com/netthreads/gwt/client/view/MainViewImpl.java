package com.netthreads.gwt.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.netthreads.gwt.client.common.TrafficData;

@SuppressWarnings(
{
	"unused"
})
public class MainViewImpl extends Composite implements MainView
{
	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
	@UiField
	protected HTMLPanel testLabel;

	@UiField
	protected MapViewImpl mapView;

	@UiField
	protected ListViewImpl listView;

	@UiField
	protected PropertiesViewImpl propertiesView;
	
	@UiTemplate("MainView.ui.xml")
	interface MainViewUiBinder extends UiBinder<Widget, MainViewImpl>
	{
	}

	private MainView.Presenter presenter;

	public MainViewImpl()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(MainView.Presenter presenter)
	{
		this.presenter = presenter;

		mapView.setPresenter(presenter);
		listView.setPresenter(presenter);
	}

	/**
	 * Assign data to views.
	 * 
	 */
	@Override
	public void setItemData(List<TrafficData> items)
	{
		mapView.setItemData(items);
		listView.setItemData(items);
	}

	@Override
	public void setItemSelected(TrafficData item)
	{
	    mapView.setItemSelected(item);
	    listView.setItemSelected(item);
	    propertiesView.setItemSelected(item);
	}
}
