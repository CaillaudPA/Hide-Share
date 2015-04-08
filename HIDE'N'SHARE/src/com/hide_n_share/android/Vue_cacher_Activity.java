package com.hide_n_share.android;


import java.io.File;

import com.hide_n_share.android.utilitaire.Data;
import com.hide_n_share.android.utilitaire.FonctionUtile;
import com.hide_n_share.android.utilitaire.PopupErreur;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Vue_cacher_Activity extends Activity implements OnClickListener {
	
	private Button photoExist;
	private Button photoAPrendre;
	private Button fichierQuelconque;
	private Button cacherTexte;
	private Uri imageUri;
	
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

    
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.equals(photoExist)){
            
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent,"Select exisiting image"), Data.photoExistante);
		}
	
		else if(arg0.equals(photoAPrendre)){
				String fileName = "imageCamera.png";
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
			
				
		}else if(arg0.equals(fichierQuelconque)){
			 Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		     intent.setType("*/");
             startActivityForResult(Intent.createChooser(intent,"Select File"), Data.fichierQuelconque);
			
		}else{
			Intent intent = new Intent(this, Vue_cacher_texte_Activity.class);
			startActivity(intent);

		}
	} 
	
	
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (resultCode == RESULT_OK) {
            if (requestCode == Data.photoExistante) {	
                imageUri = data.getData();
                String path = "";
                try{
                	 path = FonctionUtile.getRealPathFromURI(this,imageUri);
                     if((new File(path)).exists()){
         				Intent intent = new Intent(this, Vue_choix_enveloppe_Activity.class);
         				intent.putExtra(Data.EXTRA_LETTRE,path);
         				startActivity(intent);
                     }else{
                     	Toast.makeText(this, "la selection du fichier ne marche pas, utilisé un autre gestionnaire de fichier", Toast.LENGTH_LONG).show();
                     }
                }catch(java.lang.RuntimeException e){
                	Toast.makeText(this, "la selection du fichier ne marche pas, utilisé un autre gestionnaire de fichier", Toast.LENGTH_LONG).show();
                }

            }
            if (requestCode == Data.fichierQuelconque) {	
                imageUri = data.getData();
                
                try{
                	String path = FonctionUtile.getRealPathFromURI(this,imageUri);
                    
                    if((new File(path)).exists()){
        				Intent intent = new Intent(this, Vue_choix_enveloppe_Activity.class);
        				intent.putExtra(Data.EXTRA_LETTRE,path);
        				startActivity(intent);
                    }else{
                    	Toast.makeText(this, "la selection du fichier ne marche pas, utilisé un autre gestionnaire de fichier", Toast.LENGTH_LONG).show();
                    }
                }catch(java.lang.RuntimeException e){
                	Toast.makeText(this, "la selection du fichier ne marche pas, utilisé un autre gestionnaire de fichier", Toast.LENGTH_LONG).show();
                }
                
            }
            if (requestCode == Data.photoAPrendre){
    			Intent intent = new Intent(this, Vue_choix_enveloppe_Activity.class);
				intent.putExtra(Data.EXTRA_LETTRE,FonctionUtile.getRealPathFromURI(this,imageUri));
				startActivity(intent);

            }
        }
    }

}
