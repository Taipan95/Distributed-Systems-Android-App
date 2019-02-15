package omada6.katanemimena.katanemimenaapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadMarkerPhoto extends AsyncTask<String, Void, Bitmap> {
    private static String TAG = "DownloadMarkerPhoto";
    private ImageView image;

    public DownloadMarkerPhoto(ImageView image){
            this.image = image;
    }
    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap bmpImg = null;

        try {
            Log.d(TAG, "doInBackground: PHOTOO " + url);
            InputStream in = new java.net.URL(url).openStream();
            bmpImg = BitmapFactory.decodeStream(in);

        } catch (Exception e) {

            Log.e(TAG, e.toString());
        }

        return bmpImg;
    }

    protected void onPostExecute(Bitmap bmpImage){
        try {
            image.setImageBitmap(bmpImage);

        } catch(Exception e) {

            Log.e(TAG, "onPostExecute: ", e );
        }
    }
}
