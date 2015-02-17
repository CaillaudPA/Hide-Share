package com.hide_n_share.android;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Acceuil extends Activity implements OnClickListener{

	private Button boutonCacher;
	private Button boutonDevoiler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acceuil);

		 boutonCacher = (Button)findViewById(R.id.boutonAcceuilDissimuler);
		 boutonCacher.setOnClickListener(this);
		 
		 boutonDevoiler = (Button)findViewById(R.id.boutonAcceuilDevoiler);
		 boutonDevoiler.setOnClickListener(this);
	        
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.acceuil, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_acceuil,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onClick(View arg0) {
		
		if(arg0.equals(boutonCacher)){
			Intent intent = new Intent(Acceuil.this, Vue_cacher_Activity.class);
			startActivity(intent);
		}else
		{
			Intent intent = new Intent(Acceuil.this, Vue_saisir_mdp_Activity.class);
			startActivity(intent);
		}
		
	}

}
