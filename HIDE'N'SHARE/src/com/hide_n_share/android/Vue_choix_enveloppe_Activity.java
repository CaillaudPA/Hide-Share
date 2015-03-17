package com.hide_n_share.android;


import com.hide_n_share.android.utilitaire.Data;
import com.hide_n_share.android.utilitaire.FonctionUtile;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Vue_choix_enveloppe_Activity extends Activity implements OnClickListener{
	
	private Button galerie;
	private Button photo;
	
	String pathDonneeCachee = "";
	
	private Uri imageUri;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_enveloppe);
        
        pathDonneeCachee= getIntent().getStringExtra(Data.EXTRA_LETTRE);
        
        galerie = (Button)findViewById(R.id.repertoirephoto);
        galerie.setOnClickListener(this);
        
        photo = (Button)findViewById(R.id.prendrePhoto);
        photo.setOnClickListener(this);


	}
	
	public void onClick(View arg0) {
		if(arg0.equals(galerie)){
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent,"Select exisiting image"), Data.photoExistante);
		}else if(arg0.equals(photo)){
			String fileName = "HIDE_N_SHARE_ChoixEnvellope.png";
			//create parameters for Intent with filename
			ContentValues values = new ContentValues();
			values.put(MediaStore.Images.Media.TITLE, fileName);
			values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");
			values.put(MediaStore.Images.Media.DISPLAY_NAME,fileName);
			//imageUri is the current activity attribute, define and save it for later usage (also in onSaveInstanceState)
			imageUri = getContentResolver().insert(
			        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
			//create new Intent
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			startActivityForResult(intent,Data.photoAPrendre);
		}
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (resultCode == RESULT_OK) {
			if (requestCode == Data.photoAPrendre) {
	   			Intent intent = new Intent(this, Vue_chiffrement_compression.class);
				intent.putExtra(Data.EXTRA_ENVELOPPE, FonctionUtile.getRealPathFromURI(this, imageUri));
				intent.putExtra(Data.EXTRA_LETTRE,pathDonneeCachee);		
				
				startActivity(intent);
			}else if(requestCode == Data.photoExistante){
                imageUri = data.getData();
                
				Intent intent = new Intent(this, Vue_chiffrement_compression.class);
				intent.putExtra(Data.EXTRA_ENVELOPPE,FonctionUtile.getRealPathFromURI(this,imageUri));
				intent.putExtra(Data.EXTRA_LETTRE,pathDonneeCachee);
				
				startActivity(intent);
			}
		}		
	}
}
