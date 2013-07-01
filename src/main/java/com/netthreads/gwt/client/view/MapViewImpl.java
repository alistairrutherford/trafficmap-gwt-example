package com.netthreads.gwt.client.view;

import java.util.HashMap;
import java.util.List;

import org.discotools.gwt.leaflet.client.Options;
import org.discotools.gwt.leaflet.client.layers.raster.TileLayer;
import org.discotools.gwt.leaflet.client.map.Map;
import org.discotools.gwt.leaflet.client.map.MapOptions;
import org.discotools.gwt.leaflet.client.marker.Marker;
import org.discotools.gwt.leaflet.client.marker.MarkerOptions;
import org.discotools.gwt.leaflet.client.types.LatLng;
import org.discotools.gwt.leaflet.client.types.LatLngBounds;
import org.discotools.gwt.leaflet.client.widget.MapWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.netthreads.gwt.client.common.TrafficData;

/**
 * Map view.
 * 
 */
@SuppressWarnings(
{
	"unused"
})
public class MapViewImpl extends Composite implements MapView
{
	private static CentreViewUiBinder uiBinder = GWT.create(CentreViewUiBinder.class);
	
	@UiField
	protected HTMLPanel mainPanel;
	
	// -------------------------------------------------------------------
	// View stuff.
	// -------------------------------------------------------------------
	
	private MainView.Presenter presenter;
	
	@UiTemplate("MapView.ui.xml")
	interface CentreViewUiBinder extends UiBinder<Widget, MapViewImpl>
	{
	}
	
	private java.util.Map<String, Marker> markers;
	
	public MapViewImpl()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		markers = new HashMap<String, Marker>();
	}
	
	// -------------------------------------------------------------------
	// Map stuff.
	// -------------------------------------------------------------------
	
	private Map myMap;
	
	private static final String MAP_URL = "http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/{z}/{x}/{y}.png";
	private static final String MAP_ATTRIBUTION = "Map data &copy; <a href='http://openstreetmap.org'>OpenStreetMap</a> contributors, <a href='http://creativecommons.org/licenses/by-sa/2.0/'>CC-BY-SA</a>, Imagery <a href='http://cloudmade.com'>CloudMade</a>";
	private static final String MAP_DIV = "map";
	
	@Override
	protected void onLoad()
	{
		super.onLoad();
		
		// ---------------------------------------------------------------
		// Map Widget
		// ---------------------------------------------------------------
		
		MapWidget mapWidget = new MapWidget(MAP_DIV);
		mainPanel.add(mapWidget);
		mapWidget.setHeight("100%");
		mapWidget.setWidth("100%");
		
		// ---------------------------------------------------------------
		// Map Configuration
		// ---------------------------------------------------------------
		
		// Create Map instance
		MapOptions loptions = new MapOptions();
		loptions.setCenter(new LatLng(55.864237000000000000, -4.251805999999988000));
		loptions.setZoom(13);
		
		Options tileOptions = new Options();
		tileOptions.setProperty("attribution", MAP_ATTRIBUTION);
		TileLayer tileLayer = new TileLayer(MAP_URL, tileOptions);
		
		myMap = new Map(MAP_DIV, loptions);
		
		myMap.addLayer(tileLayer);
		
		myMap.invalidateSize(true);
	}
	
	/**
	 * Assign presenter.
	 * 
	 */
	@Override
	public void setPresenter(MainView.Presenter presenter)
	{
		this.presenter = presenter;
	}
	
	/**
	 * Add data to map.
	 * 
	 * @param items
	 */
	@Override
	public void setItemData(List<TrafficData> items)
	{
		markers.clear();
		
		LatLngBounds latLngBounds = new LatLngBounds();
		
		for (int i = 0; i < items.size(); i++)
		{
			TrafficData data = (TrafficData) items.get(i);
			
			double lat = Double.parseDouble(data.getLatitude());
			double lng = Double.parseDouble(data.getLongitude());
			
			LatLng latlng = new LatLng(lat, lng);
			
			latLngBounds.extend(latlng);
			
			MarkerOptions markerOptions = new MarkerOptions();
			markerOptions.setTitle(data.getCategoryClass());
			
			Marker marker = new Marker(latlng, markerOptions);
			
			marker.addTo(myMap);
			
			marker.bindPopup(data.getDescription());
			
			// Keep a note of the markers.
			markers.put(data.getId(), marker);
		}
		
		myMap.fitBounds(latLngBounds);
		myMap.setZoom(9);
		
		// Force a redraw
		myMap.invalidateSize(true);
		
	}
	
	/**
	 * Select item on map.
	 * 
	 */
	@Override
	public void setItemSelected(TrafficData item)
	{
		// Fetch marker.
		Marker marker = markers.get(item.getId());
		
		if (marker != null)
		{
			LatLng latLng = marker.getLatLng();
			
			myMap.panTo(latLng);
			
			marker.openPopup();
		}
	}
	
}
