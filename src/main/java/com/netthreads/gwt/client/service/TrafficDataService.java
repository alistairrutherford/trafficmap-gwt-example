package com.netthreads.gwt.client.service;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.netthreads.gwt.client.common.TrafficData;
import com.netthreads.gwt.client.view.MainView;

public class TrafficDataService
{
	private static final String JSON_URL = GWT.getHostPageBaseURL();

	public static final String DATA_M25 = "M60.json";

	/**
	 * Fetch data.
	 * 
	 * @param view
	 */
	public void getTrafficData(final MainView view)
	{
		String url = JSON_URL + DATA_M25;

		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);

		try
		{
			builder.sendRequest(null, new RequestCallback()
			{
				public void onError(Request request, Throwable exception)
				{
					Window.alert("Couldn't retrieve JSON");
				}

				public void onResponseReceived(Request request, Response response)
				{
					if (200 == response.getStatusCode())
					{
						List<TrafficData> list = new LinkedList<TrafficData>();

						JsArray<TrafficData> trafficData = asArrayOfTrafficData(response.getText());

						for (int i = 0; i < trafficData.length(); i++)
						{
							TrafficData data = trafficData.get(i);
							list.add(data);
						}

						// Add data to view.
						view.setItemData(list);
					}
					else
					{
						Window.alert("Couldn't retrieve JSON (" + response.getStatusText() + ")");
					}
				}
			});
		}
		catch (RequestException e)
		{
			Window.alert("Couldn't retrieve JSON");
		}
	}

	/**
	 * Convert json data to array of data.
	 * 
	 * @param json
	 *            The json
	 * 
	 * @return The array of data.
	 */
	private final native JsArray<TrafficData> asArrayOfTrafficData(String json) /*-{
		return eval(json);
	}-*/;

}
