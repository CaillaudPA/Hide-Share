<<<<<<< HEAD
package com.hide_n_share.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Vue_devoiler_Activity extends Activity implements OnClickListener {

	private Button selectImage;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vue_devoile);
		
		new PopupErreur().display(this, "test erreur");
	
	}
	
	@Override
	public void onClick(View arg0) {

	}

}
=======
package com.hide_n_share.android;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Vue_devoiler_Activity extends Activity implements OnClickListener {

	private Button selectImage;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vue_devoile);
		
		new PopupErreur().display(this, "test erreur");
	
	}
	
	@Override
	public void onClick(View arg0) {

	}

}
>>>>>>> e9d22a1a85e9af51e7d93f631fab7522397c9ca3
