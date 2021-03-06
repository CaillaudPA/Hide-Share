package com.hide_n_share.android;
import java.io.File;

import com.hide_n_share.android.utilitaire.Data;
import com.hide_n_share.android.utilitaire.FonctionUtile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Vue_devoiler_Activity extends Activity implements OnClickListener {
	private Button selectImage;
	private Uri imageUri;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vue_devoile);

		selectImage = (Button)findViewById(R.id.boutonVueCacherPhotoAPrendre);
		selectImage.setOnClickListener(this);
	}
	
	
	public void onClick(View arg0) {
		if(arg0.equals(selectImage)){
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent,"Select exisiting image"), Data.photoAPrendre);
		}
	}
	
	
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
        	if (requestCode == Data.photoAPrendre) {
                imageUri = data.getData();
                String path = "";
                try{
                	path = FonctionUtile.getRealPathFromURI(this,imageUri);
                	if((new File(path)).exists()){
            			Intent intent = new Intent(this, Vue_saisir_mdp_Activity.class);
            			intent.putExtra(Data.EXTRA_IMG_A_DEVOILER, path);
            			startActivity(intent);
                	}else{
                		Toast.makeText(this, "la selection du fichier ne marche pas, utilisé un autre gestionnaire de fichier", Toast.LENGTH_LONG).show();
                	}
                }catch(java.lang.RuntimeException e){
                	Toast.makeText(this, "la selection du fichier ne marche pas, utilisé un autre gestionnaire de fichier", Toast.LENGTH_LONG).show();
                }
			}
        }
    }
}