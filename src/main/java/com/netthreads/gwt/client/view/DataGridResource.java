package com.netthreads.gwt.client.view;

import com.google.gwt.user.cellview.client.DataGrid;

/**
 * Override the datagrid style resources.
 * 
 * http://stackoverflow.com/questions/7394151/datagrid-celltable-styling-frustration-overriding-row-styles
 *
 */
public interface DataGridResource extends DataGrid.Resources
{
	@Source(
	{
	        DataGrid.Style.DEFAULT_CSS, "DataGridOverride.css"
	})
	DataGrid.Style dataGridStyle();
};