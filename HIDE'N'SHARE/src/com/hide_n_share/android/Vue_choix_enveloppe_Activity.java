package com.hide_n_share.android;


import com.hide_n_share.android.utilitaire.Data;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
			startActivityForResult(intent,3);
		}
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			System.out.println(imageUri.toString());
			
   			Intent intent = new Intent(this, Vue_chiffrement_compression.class);
			intent.putExtra(Data.EXTRA_ENVELOPPE,getRealPathFromURI(imageUri));
			intent.putExtra(Data.EXTRA_LETTRE,pathDonneeCachee);		
			
			startActivity(intent);
			finish();
		}		
	}
	
	public String getRealPathFromURI (Uri contentUri) {
	    String path = null;
	    String[] proj = { MediaStore.MediaColumns.DATA };
	    Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
	    if (cursor.moveToFirst()) {
	       int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
	       path = cursor.getString(column_index);
	    }
	    cursor.close();
	    return path;
	}

}
