package com.programme;

import org.apache.commons.codec.binary.Base64;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageUtilities {

	public static Bitmap createBitmapFromString(String image){
		byte[] imageData = Base64.decodeBase64(image.getBytes());
		return BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
	}
}
