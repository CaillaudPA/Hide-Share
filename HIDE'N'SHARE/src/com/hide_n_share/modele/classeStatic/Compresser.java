package com.hide_n_share.modele.classeStatic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Compresser{
	
	public static byte[] compression(byte[] fluxACompresser){
		   Deflater deflater = new Deflater();  
		   deflater.setInput(fluxACompresser);  
		   
		   ByteArrayOutputStream outputStream = new ByteArrayOutputStream(fluxACompresser.length);   

		   deflater.finish();  
		   byte[] buffer = new byte[1024];   
		   while (!deflater.finished()) {  
			   int count = deflater.deflate(buffer);
			   outputStream.write(buffer, 0, count);   
		   }  
		   try {
			   outputStream.close();
		   } catch (IOException e) {
			   e.printStackTrace();
		   }
		   
		   byte[] output = outputStream.toByteArray();  
		   
		   deflater.end();
		   return output;  
	}

	public static byte[] decompression(byte[] fluxADecompresser){
		   Inflater inflater = new Inflater();   
		   inflater.setInput(fluxADecompresser);  
		   
		   ByteArrayOutputStream outputStream = new ByteArrayOutputStream(fluxADecompresser.length);  
		   byte[] buffer = new byte[1024];  
		   while (!inflater.finished()) {  
			   int count = 0;
			   try {
				   count = inflater.inflate(buffer);
			   } catch (DataFormatException e) {
				   e.printStackTrace();
			   }
			   outputStream.write(buffer, 0, count);  
		   }  
		   try {
			   outputStream.close();
		   } catch (IOException e) {
			   e.printStackTrace();
		   }  
		   byte[] output = outputStream.toByteArray();  
		   
		   inflater.end();

		   return output;  
	}
}

//javac -d bin -cp bin src/classeStatic/*.java