package com.example.livecards;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity 
{

	private final Handler mHandler = new Handler();

	@Override
	public void onAttachedToWindow() 
	{
		super.onAttachedToWindow();
		openOptionsMenu();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.livecard, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection.
		switch (item.getItemId()) 
		{
		case R.id.stop:
			
			mHandler.post(new Runnable()
			{

				@Override
				public void run() 
				{
					stopService(new Intent(MainActivity.this, GlService.class));
				}
			});
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onOptionsMenuClosed(Menu menu) 
	{
		// Nothing else to do, closing the Activity.
		finish();
	}
}
