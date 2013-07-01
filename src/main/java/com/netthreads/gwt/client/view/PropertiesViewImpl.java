package com.netthreads.gwt.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.netthreads.gwt.client.common.TrafficData;

/**
 * Properties view.
 *
 */
public class PropertiesViewImpl extends Composite implements PropertiesView
{
	
	private static RightViewUiBinder uiBinder = GWT.create(RightViewUiBinder.class);
	@UiField
	protected Label category;
	@UiField
	protected Label road;
	@UiField
	protected Label region;
	@UiField
	protected Label publishdate;
	@UiField
	protected Label county;
	
	@UiTemplate("PropertiesView.ui.xml")
	interface RightViewUiBinder extends UiBinder<Widget, PropertiesViewImpl>
	{
	}
	
	// -------------------------------------------------------------------
	// View stuff.
	// -------------------------------------------------------------------
	
	public PropertiesViewImpl()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	public void setItemSelected(TrafficData item)
	{
		// Assign properties.
		category.setText(item.getCategoryClass());
		road.setText(item.getRoad());
		region.setText(item.getRegion());
		county.setText(item.getCounty());
		publishdate.setText(item.getPubDate());
	}
	
}
