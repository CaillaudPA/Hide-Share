package com.hide_n_share.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Vue_choix_enveloppe_Activity extends Activity implements OnClickListener{
	
	private Button galerie;
	private Button photo;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_enveloppe);
    }
	
	@Override
	public void onClick(View arg0) {

	}
}
