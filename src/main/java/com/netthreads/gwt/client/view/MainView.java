package com.netthreads.gwt.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.netthreads.gwt.client.common.TrafficData;

public interface MainView
{
	public interface Presenter
	{
		void onItemSelected(TrafficData item);
	}

	void setPresenter(Presenter presenter);

	void setItemData(List<TrafficData> items);
	
	void setItemSelected(TrafficData item);

	Widget asWidget();
}
