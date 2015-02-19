package com.hide_n_share.android;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Vue_cacher_Activity extends Activity implements OnClickListener {
	
	private Button photoExist;
	private Button photoAPrendre;
	private Button fichierQuelconque;
	private Button cacherTexte;
	private Uri imageUri;
	
	final String EXTRA_PATH_CHEMIN = "path_chemin";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_cacher);
        
        photoExist = (Button)findViewById(R.id.boutonVueCacherPhotoExistant);
        photoExist.setOnClickListener(this);
        
        photoAPrendre = (Button)findViewById(R.id.boutonVueCacherPhotoAPrendre);
        photoAPrendre.setOnClickListener(this);
        
        fichierQuelconque = (Button)findViewById(R.id.boutonVueCacherFichierQuelconque);
        fichierQuelconque.setOnClickListener(this);
        
        cacherTexte = (Button)findViewById(R.id.boutonVueCacherCacherTexte);
        cacherTexte.setOnClickListener(this);
    }

    
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.equals(photoExist)){
			          
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent,"Select exisiting image"), 1);
        	}
		
		else if(arg0.equals(photoAPrendre)){
			
		/*
			String fileName = "imageCamera.jpg";
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
			*/
	
			Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		    // Ensure that there's a camera activity to handle the intent
		    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
		        // Create the File where the photo should go
		        File photoFile = null;
		        try {
		            photoFile = createImageFile();
		        } catch (IOException ex) {
		           
		        }
		        // Continue only if the File was successfully created
		        if (photoFile != null) {
		            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
		                    Uri.fromFile(photoFile));
		            startActivityForResult(takePictureIntent, 3);
		        }
		    }
				
		}else if(arg0.equals(fichierQuelconque)){
			 Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		     intent.setType("*/*");
             startActivityForResult(Intent.createChooser(intent,"Select File"), 2);
			
		}else{
			Intent intent = new Intent(this, Vue_cacher_texte_Activity.class);
			startActivity(intent);
			finish();		
		}
	} 
	
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (resultCode == RESULT_OK) {
            if (requestCode == 1) {	
                imageUri = data.getData();
                
                FragmentManager fm = getFragmentManager();
        		PopupErreur a = new PopupErreur();
        		a.setMsg(imageUri.getPath());
        		a.show(fm, "test");
            }
            if (requestCode == 2) {	
                imageUri = data.getData();
                
                FragmentManager fm = getFragmentManager();
        		PopupErreur a = new PopupErreur();
        		a.setMsg(imageUri.getPath());
        		a.show(fm, "test");
            }
            if (requestCode == 3) {	
            	imageUri = data.getData();
                
            	FragmentManager fm = getFragmentManager();
        		PopupErreur a = new PopupErreur();
        		a.setMsg(imageUri.getPath());
        		a.show(fm, "test");
            }
        }
    }
    
    private File createImageFile() throws IOException {
        // Create an image file name
    	String mCurrentPhotoPath;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",         /* suffix */
            storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

}
