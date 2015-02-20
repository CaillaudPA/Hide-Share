package com.hide_n_share.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

public class Vue_saisir_mdp_Activity extends Activity implements OnClickListener{
	
	private RadioButton radioOui;
	private RadioButton radioNon;
	private Button boutonValider;
	
	String image_a_devoiler = "";
	String mdp="";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_saisir_mdp);
        
        boutonValider = (Button)findViewById(R.id.boutonVueCacherCacherTexte);
        boutonValider.setOnClickListener(this);
        
        radioOui = (RadioButton)findViewById(R.id.radioButton2);
		radioNon = (RadioButton)findViewById(R.id.radioButton1);

		image_a_devoiler = getIntent().getStringExtra(Data.EXTRA_IMG_A_DEVOILER);
		
    }
	
    public void onRadioButtonClicked(View view){
        // Check which radio button was clicked
        if(view.equals(radioOui) && radioNon.isChecked()) {
        	radioNon.setChecked(false);
        	radioOui.setChecked(false);
        }else
        {
        	radioOui.setChecked(false);
        }
    }
    
	public void onClick(View arg0) {
		if (arg0.equals(boutonValider)) {
			Intent intent = new Intent(this, Vue_choix_enveloppe_Activity.class);
			
			intent.putExtra(Data.EXTRA_IMG_A_DEVOILER,image_a_devoiler);
			intent.putExtra(Data.EXTRA_MDP, mdp);
			startActivity(intent);
			finish();
		}
		
	} 
}


