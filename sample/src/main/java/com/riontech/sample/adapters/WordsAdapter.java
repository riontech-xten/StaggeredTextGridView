package com.riontech.sample.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.riontech.sample.R;

import org.json.JSONArray;


public class WordsAdapter extends BaseAdapter {

	private Context context;
	private JSONArray jArray;

	public WordsAdapter(Context context, JSONArray jArray) {
		this.context = context;
		this.jArray = jArray;
	}

	@Override
	public int getCount() {
		return jArray.length();
	}

	@Override
	public String getItem(int position) {
		try {
			return jArray.getString(position);
		} catch (Exception e) {
			return "Sample";
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView view = (TextView) LayoutInflater.from(context).inflate(R.layout.row_item_word, null);

		Drawable drawable = ContextCompat.getDrawable(context, R.drawable.selector_items);
		drawable.setColorFilter(com.riontech.staggeredtextgridview.utils.ColorGenerator.MATERIAL.getRandomColor(), PorterDuff.Mode.SRC_ATOP);
		if(com.riontech.sample.utils.CompatibilityUtil.isJellyBean())
			view.setBackground(drawable);
		else
			view.setBackgroundDrawable(drawable);

		try {
			view.setText(jArray.getString(position));
			view.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(context, ((TextView) v).getText().toString(), Toast.LENGTH_LONG).show();
				}
			});
		} catch (Exception e){
			e.printStackTrace();
		}

		return view;
	}
}
