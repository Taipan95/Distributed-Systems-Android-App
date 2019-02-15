package omada6.katanemimena.katanemimenaapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindow implements GoogleMap.InfoWindowAdapter {
    private static final String TAG = "CustomInfoWindow";
    private View window;
    private Context context;
    private POI place;
    private ImageView img;
    private Marker lastMarker = null;
    public CustomInfoWindow(Context context,POI place) {
        this.context = context;
        this.place = place;
        Log.d(TAG, "CustomInfoWindow: PHOTO by POI "+place.getID()+" "+ place.getPhotos());
    }

    @Override
    public View getInfoWindow(Marker marker) {
        if(marker != null && marker.isInfoWindowShown()){
            marker.hideInfoWindow();
            marker.showInfoWindow();
        }

        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        if (window == null){
            window = LayoutInflater.from(context).inflate(R.layout.custom_marker_info,null);
        }

        TextView tv_Title = (TextView)window.findViewById(R.id.marker_title);

        tv_Title.setText(marker.getTitle());


        TextView tv_Snippet = (TextView)window.findViewById(R.id.marker_snippet);
        tv_Snippet.setText( marker.getSnippet());

        img = (ImageView)window.findViewById(R.id.marker_image);

        new DownloadMarkerPhoto(img).execute(place.getPhotos());


        return window;
    }

}
