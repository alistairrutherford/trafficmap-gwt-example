package com.netthreads.gwt.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TopView extends Composite
{
	
	private static TopViewUiBinder uiBinder = GWT.create(TopViewUiBinder.class);
	
	interface TopViewUiBinder extends UiBinder<Widget, TopView>
	{
	}
	
	public TopView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
	
}
