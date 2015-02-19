package com.hide_n_share.android;

import java.io.File;

import com.hide_n_share.modele.classeStatic.GestionFichier;
import com.hide_n_share.modele.steganographie.Stegano_image;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
            image.setImageBitmap(null);
            note.setText("by Returned Uri");
            
            try {
             Bitmap bm = BitmapFactory.decodeStream(
               getContentResolver().openInputStream(orgUri));
             image.setImageBitmap(bm); 
            } catch (FileNotFoundException e) {
             e.printStackTrace(); 
            }
            
        	}
		
		else if(arg0.equals(photoAPrendre)){
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
				//finish();
			
				
		}else if(arg0.equals(fichierQuelconque)){
			 Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		     intent.setType("*/*");
             startActivityForResult(Intent.createChooser(intent,"Select File"), 2);
			
		}else{
			/*Intent intent = new Intent(this, Vue_cacher_texte_Activity.class);
			startActivity(intent);
			finish();*/
			
			File file = new  File("storage/emulated/0/download/test_file.txt");
    		GestionFichier.fluxEnFichier(file.getPath(),"sdfzff".getBytes());
    		new PopupErreur().display(this,new String(GestionFichier.fichierEnFlux(file.getPath())));
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
	
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (resultCode == RESULT_OK) {
            if (requestCode == 1) {	
                //imageUri = data.getData();
            	image.setImageBitmap(null);
            	  
            	   convertedPath = getRealPathFromURI(orgUri);
            	   text2.setText("Real Path: " + convertedPath + "\n");
            	   
            	   //Uri convert back again from path
            	   uriFromPath = Uri.fromFile(new File(convertedPath));
            	   text3.setText("Back Uri: " + uriFromPath.toString() + "\n");
                
                FragmentManager fm = getFragmentManager();
        		PopupErreur a = new PopupErreur();
        		a.setMsg(getRealPathFromURI(imageUri));
        		a.show(fm, "test");

            }
            if (requestCode == 2) {	
                imageUri = data.getData();
                
                FragmentManager fm = getFragmentManager();
        		PopupErreur a = new PopupErreur();
        		a.setMsg(getRealPathFromURI(imageUri));
        		a.show(fm, "test");
            }
            if (requestCode == 3) {	
                
                FragmentManager fm = getFragmentManager();
        		PopupErreur a = new PopupErreur();
        		a.setMsg(getRealPathFromURI(imageUri));
        		a.show(fm, "test");
            }
        }
    }

}
