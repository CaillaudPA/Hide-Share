package com.hide_n_share.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

public class Vue_saisir_mdp_Activity extends Activity implements OnClickListener{
	
	private RadioButton radioOui;
	private RadioButton radioNon;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_saisir_mdp);
        
        radioOui = (RadioButton)findViewById(R.id.radioButton2);
		radioOui.setOnClickListener(this);
		
		radioNon = (RadioButton)findViewById(R.id.radioButton1);
		radioNon.setOnClickListener(this);
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
    
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	} 
}


