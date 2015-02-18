package com.hide_n_share.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Vue_choix_enveloppe_Activity extends Activity implements OnClickListener{
	
	private ImageButton galerie;
	private ImageButton photo;
	private ImageView mImageView;
	
	static final int REQUEST_IMAGE_CAPTURE = 1;

	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
	    }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
	        Bundle extras = data.getExtras();
	        Bitmap imageBitmap = (Bitmap) extras.get("data");
	        mImageView.setImageBitmap(imageBitmap);
	    }
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_enveloppe);
        
        galerie = (ImageButton)findViewById(R.id.repertoirephoto);
        photo = (ImageButton)findViewById(R.id.prendrePhoto);
        
        galerie.setOnClickListener(this);
        photo.setOnClickListener(this);
    }
	
	@Override
	public void onClick(View arg0) {
		
		if(arg0.equals(galerie))
		{
			Intent i = new Intent(Intent.ACTION_PICK,
	                   android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
	        final int ACTIVITY_SELECT_IMAGE = 1234;
	        startActivityForResult(i, ACTIVITY_SELECT_IMAGE); 
	        onActivityResult(REQUEST_IMAGE_CAPTURE, 1, i);
		}
		if(arg0.equals(photo))
		{
			this.dispatchTakePictureIntent();
		}

	}
}
