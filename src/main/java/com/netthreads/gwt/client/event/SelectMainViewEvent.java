package com.netthreads.gwt.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class SelectMainViewEvent extends GwtEvent<SelectMainViewEventHandler>
{
	public static Type<SelectMainViewEventHandler> TYPE = new Type<SelectMainViewEventHandler>();

	@Override
	public Type<SelectMainViewEventHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch(SelectMainViewEventHandler handler)
	{
		handler.select(this);
	}
}
