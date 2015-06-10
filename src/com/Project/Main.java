package com.Project;

import java.util.List;


import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;



import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends MapActivity {
    /** Called when the activity is first created. */
	
	private MapController mapController;
	
	private LocationManager locationManager;
	private LocationListener locationListener;
	private MapView mapView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);  
        locationListener = new GPSlocation();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.setBuiltInZoomControls(true);
        mapController = mapView.getController();
        mapController.setZoom(16);
        
                         
    }
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	public class GPSlocation implements LocationListener
	{

		@Override
		public void onLocationChanged(Location location) {
			if (location != null) {
			      GeoPoint point = new GeoPoint(
			          (int) (location.getLatitude() * 1E6), 
			          (int) (location.getLongitude() * 1E6));
			      
			      mapController.animateTo(point);
			      mapController.setZoom(16);	
			      
			      
			      MapOverlay mapOverlay = new MapOverlay();
			      mapOverlay.setPointToDraw(point);
			      List<Overlay> listOfOverlays = mapView.getOverlays();
			      listOfOverlays.clear();
			      listOfOverlays.add(mapOverlay);
			      
			      Toast.makeText(getBaseContext(), //Was added later. Delete when using the point to location converter
				          "Latitude: " + location.getLatitude() + 
				          " Longitude: " + location.getLongitude(), 
				          Toast.LENGTH_SHORT).show();
			      double lat = location.getLatitude();
			      double lon = location.getLongitude();
			      String user_ID = "1";
			      Map_Database entry = new Map_Database(Main.this);
			      entry.open();
			      String result = entry.getMapData();
			      entry.createMapEntry(user_ID, lat, lon);			      
			      entry.close();
			      
			      String dBaselat = result.substring(0, result.indexOf("*")-1);
			    //  String dBaseLon = result.substring(result.indexOf("*"));
			      Toast.makeText(getBaseContext(), dBaselat, Toast.LENGTH_SHORT).show();
			      
			      double lat1 = location.getLatitude();
			      double lat2 = 37.422006;
			      double lon1 =	location.getLongitude();
			      double lon2 = -122.084095;
			      double dLat = Math.toRadians(lat2-lat1);
			      double dLon = Math.toRadians(lon2-lon1);
			      double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			         Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
			         Math.sin(dLon/2) * Math.sin(dLon/2);
			      double c = 2 * Math.asin(Math.sqrt(a));
			      double Radius = 6371;
			      c = c*Radius;
			      String distance = Double.toString(c);
			      
			      Dialog d = new Dialog(Main.this);
			      d.setTitle("The distance covered");
			      TextView tv = new TextView(Main.this);
			      tv.setText(distance + "Km" +" \n");
			      d.setContentView(tv);
			      d.show();
//			      String address = ConvertPointToLocation(point);
//			      Toast.makeText(getBaseContext(), address, Toast.LENGTH_SHORT).show();
			      		     		      
			      mapView.invalidate();				      
			      
			}
		}
		
//		 public String ConvertPointToLocation(GeoPoint point) {   
//			    String address = "";
//			    Geocoder geoCoder = new Geocoder(
//			        getBaseContext(), Locale.getDefault());
//			    try {
//			      List<Address> addresses = geoCoder.getFromLocation(
//			        point.getLatitudeE6()  / 1E6, 
//			        point.getLongitudeE6() / 1E6, 1);
//			 
//			      if (addresses.size() > 0) {
//			        for (int index = 0; 
//				index < addresses.get(0).getMaxAddressLineIndex(); index++)
//			          address += addresses.get(0).getAddressLine(index) + " ";
//			      }
//			    }
//			    catch (IOException e) {        
//			      e.printStackTrace();
//			    }   
//			    
//			    return address;
//			  } 

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.main_menu, menu);
		return true;
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.exit:
			//this.finish();
			finish();
			
			break;

		case R.id.refresh:
			Intent i = new Intent ("com.Project.Main");
			startActivity(i);
			break;
			
		case R.id.settings:			
			Intent s = new Intent ("com.Project.Http_data");
			startActivity(s);			
			break;
		}
		return false;
	}
	public class DistanceCalculator {

	   private double Radius;

	   // R = earth's radius (mean radius = 6,371km)
	   // Constructor
	   DistanceCalculator(double R) { Radius = R; }

	   public double CalculationByDistance(GeoPoint StartP, GeoPoint EndP) {
	      double lat1 = StartP.getLatitudeE6()/1E6;
	      double lat2 = EndP.getLatitudeE6()/1E6;
	      double lon1 = StartP.getLongitudeE6()/1E6;
	      double lon2 = EndP.getLongitudeE6()/1E6;
	      double dLat = Math.toRadians(lat2-lat1);
	      double dLon = Math.toRadians(lon2-lon1);
	      double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	         Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
	         Math.sin(dLon/2) * Math.sin(dLon/2);
	      double c = 2 * Math.asin(Math.sqrt(a));
	      return Radius * c;
	   }
	}

		
		class MapOverlay extends Overlay
		{
		  private GeoPoint pointToDraw;

		  public void setPointToDraw(GeoPoint point) {
		    pointToDraw = point;
		  }

		  public GeoPoint getPointToDraw() {
		    return pointToDraw;
		  }
		  
		  @Override
		  public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		    super.draw(canvas, mapView, shadow);           

		    // convert point to pixels
		    Point screenPts = new Point();
		    mapView.getProjection().toPixels(pointToDraw, screenPts);

		    // add marker
		    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.red_small_car);
		    canvas.drawBitmap(bmp, screenPts.x, screenPts.y - 24, null);    
		    return true;
		  }
	}


		

}
