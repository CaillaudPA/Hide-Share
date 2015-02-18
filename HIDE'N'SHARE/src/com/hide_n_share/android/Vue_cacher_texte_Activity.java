package com.hide_n_share.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Vue_cacher_texte_Activity extends Activity implements OnClickListener{

	private Button valider;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_cacher_texte);
        
		valider = (Button)findViewById(R.id.boutonVueCacherTexteValide);
		valider.setOnClickListener(this);
    }
	
	@Override
	public void onClick(View arg0) {
		
		if(arg0.equals(valider)){
			Intent intent = new Intent(this, Vue_choix_enveloppe_Activity.class);
			startActivity(intent);
			finish();
		}
	}
    
}
