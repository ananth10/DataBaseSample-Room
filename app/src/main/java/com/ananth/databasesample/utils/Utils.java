package com.ananth.databasesample.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by anantha on 3/26/2016.
 */
public class Utils {
    public static Bitmap mBitmap = null;
    public static final int ACTION_TAKE_PICTURE = 110;
    public static final int ACTION_GALLERY = 120;
    public static final int CAMERA_FACING_FRONT = 130;
    public static final String FOLDER_NAME = "DbImage";
    public static final String FOLDER_PATH = Environment
            .getExternalStorageDirectory()  + "/" + FOLDER_NAME;
    public static final String IMG_PREFIX = "IMG_";
    public static final String VID_PREFIX = "VID_";
    public static String mPhone="";

    static {
        File file = new File(FOLDER_PATH);
        if (!file.exists())
            file.mkdirs();
    }

    public static String getID() {
        SimpleDateFormat format = new SimpleDateFormat("ddMMyy_HHmmssSSS",
                Locale.ENGLISH);
        Date date = new Date(Calendar.getInstance().getTimeInMillis());
        String str =IMG_PREFIX + format.format(date);
        return str;
    }
    public static Bitmap getLolliPopBitmap(String path) {
        Bitmap bitmapImage = BitmapFactory.decodeFile(path);
        int nh = (int) (bitmapImage.getHeight() * (512.0 / bitmapImage.getWidth()));
        Bitmap scaled = Bitmap.createScaledBitmap(bitmapImage, 512, nh, true);
        return scaled;
    }
}
