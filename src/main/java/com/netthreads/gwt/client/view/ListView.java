package com.netthreads.gwt.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.netthreads.gwt.client.common.TrafficData;

public interface ListView
{
	void setPresenter(MainView.Presenter presenter);

	void setItemData(List<TrafficData> items);

	void setItemSelected(TrafficData item);
	
	Widget asWidget();
}
