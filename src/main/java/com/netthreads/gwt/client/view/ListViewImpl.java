package com.netthreads.gwt.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.netthreads.gwt.client.common.TrafficData;
import com.netthreads.gwt.client.view.MainView.Presenter;

/**
 * Data list view with custom cell selection styles.
 * 
 */
public class ListViewImpl extends Composite implements ListView
{
	private static LeftViewUiBinder uiBinder = GWT.create(LeftViewUiBinder.class);
	
	/**
	 * We have to override the styling in order to get our custom style where we
	 * suppress the cell selection border. I cannot get this to work in the UI
	 * binder style mechanism. The page size parameter here appears to be
	 * ignored or overridden by the UIBinder settings.
	 */
	private DataGridResource resource = GWT.create(DataGridResource.class);
	
	@UiField(provided = true)
	protected DataGrid<TrafficData> dataGrid = new DataGrid<TrafficData>(50, resource);
	
	@UiTemplate("ListView.ui.xml")
	interface LeftViewUiBinder extends UiBinder<Widget, ListViewImpl>
	{
	}
	
	// -------------------------------------------------------------------
	// View stuff.
	// -------------------------------------------------------------------
	
	private MainView.Presenter presenter;
	
	private ListDataProvider<TrafficData> dataProvider;
	
	public ListViewImpl()
	{
		initWidget(uiBinder.createAndBindUi(this));
		
		dataProvider = new ListDataProvider<TrafficData>();
	}
	
	@Override
	protected void onLoad()
	{
		// ---------------------------------------------------------------
		// Create Columns
		// ---------------------------------------------------------------
		
		// Create name column.
		TextColumn<TrafficData> descriptionColumn = new TextColumn<TrafficData>()
		{
			@Override
			public String getValue(TrafficData data)
			{
				return data.getDescription();
			}
		};
		
		// Create address column.
		TextColumn<TrafficData> categoryColumn = new TextColumn<TrafficData>()
		{
			@Override
			public String getValue(TrafficData data)
			{
				return data.getCategoryClass();
			}
		};
		
		// Make the column sort-able.
		categoryColumn.setSortable(true);
		
		// Add the columns.
		dataGrid.addColumn(descriptionColumn, "Description");
		dataGrid.addColumn(categoryColumn, "Category");
		
		// ---------------------------------------------------------------
		// Configure selection model
		// ---------------------------------------------------------------
		final SingleSelectionModel<TrafficData> singleSelectionModel = new SingleSelectionModel<TrafficData>();
		
		dataGrid.setSelectionModel(singleSelectionModel);
		singleSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler()
		{
			@Override
			public void onSelectionChange(SelectionChangeEvent event)
			{
				final TrafficData selected = singleSelectionModel.getSelectedObject();
				
				presenter.onItemSelected(selected);
			}
		});
		
		// ---------------------------------------------------------------
		// Associate data provider with table.
		// ---------------------------------------------------------------
		dataProvider.addDataDisplay(dataGrid);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.netthreads.gwt.client.view.ListView#setPresenter(com.netthreads.gwt
	 * .client.view.MainView.Presenter)
	 */
	@Override
	public void setPresenter(Presenter presenter)
	{
		this.presenter = presenter;
	}
	
	/**
	 * Assign item data.
	 * 
	 */
	@Override
	public void setItemData(List<TrafficData> rowData)
	{
		List<TrafficData> list = dataProvider.getList();
		
		list.clear();
		
		for (TrafficData data : rowData)
		{
			list.add(data);
		}
	}
	
	/**
	 * Select item in grid.
	 * 
	 */
	@Override
	public void setItemSelected(TrafficData item)
	{
		dataGrid.getSelectionModel().setSelected(item, true);
	}
}
