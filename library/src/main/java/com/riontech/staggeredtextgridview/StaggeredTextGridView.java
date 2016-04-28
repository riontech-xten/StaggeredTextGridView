package com.riontech.staggeredtextgridview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;


public class StaggeredTextGridView extends ScrollView {

	private BaseAdapter mAdapter;
	// Row full width
	private int mDeviceWidth;
	// Width of added child in single row.
	private int mRowWidth;
	// HORIZONTAL LinearLayout row
	private LinearLayout mRow;
	// VERTICAL LinearLayout Parent
	private LinearLayout mParent;
	// Activity context
	private Context mContext;
	
	public StaggeredTextGridView(Context context) {
		super(context);
		this.mContext = context;
		init();
	}
	
	
	@SuppressLint("NewApi") 
	public StaggeredTextGridView(Context context, AttributeSet attrs,
								 int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		this.mContext = context;
		init();
	}


	@SuppressLint("NewApi") 
	public StaggeredTextGridView(Context context, AttributeSet attrs,
								 int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.mContext = context;
		init();
	}


	public StaggeredTextGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		init();
	}


	private void init(){
		DisplayMetrics displaymetrics = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);

		mDeviceWidth = displaymetrics.widthPixels;
		
		// ScrollView params
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		setLayoutParams(params);
		
		// LinearLayout params
		mParent = new LinearLayout(mContext);
		mParent.setOrientation(LinearLayout.VERTICAL);
		params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		mParent.setLayoutParams(params);
		addView(mParent);
	}
	
	public void setmAdapter(BaseAdapter mAdapter){
		this.mAdapter = mAdapter;
		generateSpannableTextGridView();
	}
	
	private void generateSpannableTextGridView(){
		for (int i = 0; i < mAdapter.getCount(); i++) {
			// get textview from adapter
			TextView textView = (TextView) mAdapter.getView(i, null, this);
            //int padding = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
            // padding calculation
			int padding;
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
				padding = textView.getPaddingEnd() + textView.getPaddingStart();
			} else {
				padding = textView.getPaddingLeft() + textView.getPaddingRight();
			}

			// get string object
			String item = (String) mAdapter.getItem(i);
			// init item width base on its text width and padding
            int itemWidth = (int) (textView.getPaint().measureText(item) + padding);

			// init first row
            if(i == 0){
				mRow = getRow();
            	addChildView(textView, itemWidth);
            } else {
				// add TextView into row as columns
            	if(mRowWidth + itemWidth <= mDeviceWidth){
            		addChildView(textView, itemWidth);
            	} else {
            		setFullWidthRow();
            		mParent.addView(mRow);
					mRow = getRow();
                	addChildView(textView, itemWidth);
            	}
				// add last row into parent view
				if(i == (mAdapter.getCount() - 1)){
					mParent.addView(mRow);
				}
            }            
		}
	}

	/**
	 * Distribute and append equally remaining 
	 * free space width to all row's child and fill row
	 * base on device width   
	 */
	private void setFullWidthRow(){
		// Difference between row with child and device width
		int remainWidth = mDeviceWidth - mRowWidth;
		// Distributes equally remaining space between child
		int childSpace = remainWidth / mRow.getChildCount();
		int spaceReminder = remainWidth % mRow.getChildCount();

		// reset width of all child
		for (int i = 0; i < mRow.getChildCount(); i++) {
			
			if(spaceReminder > 0 && i == (mRow.getChildCount() - 1)){
				childSpace = childSpace + spaceReminder;
			}
			
			View view = mRow.getChildAt(i);
			resetChildWidth(view, childSpace);
		}
		
		mRowWidth = 0;
	}
	
	/**
	 * Append child space with child width
	 * @param view child TextView
	 * @param childSpace remaining space
	 */
	private void resetChildWidth(final View view, final int childSpace) {
		
		view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
	        @SuppressWarnings("deprecation")
			@SuppressLint("NewApi") 
	        @Override
	        public void onGlobalLayout() {
	            int childWidth = view.getWidth();

				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT);
	    		params.width = childWidth + childSpace + 10;
	    		view.setLayoutParams(params);
	    		
	    		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
	    			view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
	    		else 
	    			view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
	        }
	    });
		
	}

	/**
	 * Add child into row
	 * @param view child TextView
	 * @param newWidth width of child TextView
	 */
	private void addChildView(View view, int newWidth){
		mRow.addView(view);
		resizeRow(newWidth, view);
	}

	/**
	 * Resize row width base on child
	 * @param width incremental width row
	 * @param view child view will add into row
	 */
	private void resizeRow(int width, View view){
		// LinearLayout row params
		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mRow
				.getLayoutParams();
		mRowWidth = mRowWidth + width;
	    params.weight = mRow.getChildCount();
		mRow.setLayoutParams(params);
	}

	/**
	 * Generate new row
	 * @return row LinearLayout
	 */
	@SuppressLint("InflateParams") 
	private LinearLayout getRow() {
		final LinearLayout lRow = (LinearLayout) LayoutInflater.from(getContext()).inflate(
				R.layout.row_item_spanneble, null);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lRow.setLayoutParams(params);
        lRow.setOrientation(LinearLayout.HORIZONTAL);
		return lRow;
	}
	
}
