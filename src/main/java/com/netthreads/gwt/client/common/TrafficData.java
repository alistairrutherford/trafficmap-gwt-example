package com.netthreads.gwt.client.common;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Traffic data data class which matched HA xml definition.
 * 
 */
public class TrafficData extends JavaScriptObject
{
	protected TrafficData()
	{
		// Overlay types always have protected, zero-arg ctors
	}

	public final native String getId() /*-{
		return this.id;
	}-*/;

	public final native String getTitle() /*-{
		return this.title;
	}-*/;

	public final native String getLink() /*-{
		return this.link;
	}-*/;

	public final native String getDescription() /*-{
		return this.description;
	}-*/;

	public final native String getCategory() /*-{
		return this.category;
	}-*/;

	public final native String getPubDate() /*-{
		return this.pubDate;
	}-*/;

	public final native String getCategoryClass() /*-{
		return this.categoryClass;
	}-*/;

	public final native String getRoad() /*-{
		return this.road;
	}-*/;

	public final native String getRegion() /*-{
		return this.region;
	}-*/;

	public final native String getCounty() /*-{
		return this.county;
	}-*/;

	public final native String getLatitude() /*-{
		return this.latitude;
	}-*/;

	public final native String getLongitude() /*-{
		return this.longitude;
	}-*/;

	public final native String getOverAllStart() /*-{
		return this.overAllStart;
	}-*/;

	public final native String getOverAllEnd() /*-{
		return this.overAllEnd;
	}-*/;

	public final native String getEventStart() /*-{
		return this.eventStart;
	}-*/;

	public final native String getEventEnd() /*-{
		return this.eventEnd;
	}-*/;

}
