package com.netthreads.gwt.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.netthreads.gwt.client.common.TrafficData;

public class SelectItemEvent extends GwtEvent<SelectItemEventHandler>
{
	public static Type<SelectItemEventHandler> TYPE = new Type<SelectItemEventHandler>();
	private final TrafficData data;
	
	public SelectItemEvent(TrafficData data)
	{
		this.data = data;
	}
	
	public TrafficData getData()
	{
		return data;
	}
	
	@Override
	public Type<SelectItemEventHandler> getAssociatedType()
	{
		return TYPE;
	}
	
	@Override
	protected void dispatch(SelectItemEventHandler handler)
	{
		handler.onSelectItem(this);
	}
}
