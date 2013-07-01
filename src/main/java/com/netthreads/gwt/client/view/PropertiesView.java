package com.netthreads.gwt.client.view;

import com.google.gwt.user.client.ui.Widget;
import com.netthreads.gwt.client.common.TrafficData;

public interface PropertiesView
{
	void setItemSelected(TrafficData item);
	
	Widget asWidget();
}
