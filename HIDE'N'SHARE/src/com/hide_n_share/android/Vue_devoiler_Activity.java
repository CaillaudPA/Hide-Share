package com.hide_n_share.android;
import com.hide_n_share.android.utilitaire.Data;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
            startActivityForResult(Intent.createChooser(intent,"Select exisiting image"), 1);
		}
	}
	
	
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            imageUri = data.getData();
            
			Intent intent = new Intent(this, Vue_saisir_mdp_Activity.class);
			intent.putExtra(Data.EXTRA_IMG_A_DEVOILER,getRealPathFromURI(imageUri));
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