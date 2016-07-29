/** 
 * Texture Class for Android Game Engine
 * Teach Yourself Android 4.0 Game Programming in 24 Hours
 * Copyright (c)2012 by Jonathan S. Harbour
 */

package game.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class Texture {

    private Context p_context;
    private Bitmap p_bitmap;
    
    public Texture(Context context) {
        p_context = context;
        p_bitmap = null;
    }

    public Texture(Context context, Bitmap bitmap) {
        this(context);
        p_bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return p_bitmap;
    }

    public void setBitmap(Bitmap b) {
        p_bitmap = b;
    }
    
    public boolean loadFromAsset(String filename) {
        InputStream istream=null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        try {
            istream = p_context.getAssets().open(filename);
            p_bitmap = BitmapFactory.decodeStream(istream,null,options);
            istream.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }


}
