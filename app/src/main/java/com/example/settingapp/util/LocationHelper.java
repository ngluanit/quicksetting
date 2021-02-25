package com.example.settingapp.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;

public class LocationHelper {

    private static final String TAG = LocationHelper.class.getSimpleName();

    private long updateIntervalInMilliseconds = 10000;

    private long fastestUpdateIntervalInMilliseconds = updateIntervalInMilliseconds / 2;

    private FusedLocationProviderClient mFusedLocationClient;

    private SettingsClient mSettingsClient;

    private LocationRequest mLocationRequest;

    private LocationSettingsRequest mLocationSettingsRequest;

    private LocationCallback mLocationCallback;


    private Boolean mRequestingLocationUpdates = false;


    private int requiredGpsPriority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY;

    Context context;
    public LocationHelper(Context mContext) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(mContext);
        mSettingsClient = LocationServices.getSettingsClient(mContext);
        context=mContext;
    }

    /**
     * Sets required gps priority
     * <p>
     * Gps Priority can be
     * <ul>
     * <li>LocationRequest.PRIORITY_HIGH_ACCURACY</li>
     * <li>LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY</li>
     * <li>LocationRequest.PRIORITY_NO_POWER</li>
     * <li>LocationRequest.PRIORITY_LOW_POWER</li>
     * </ul>
     * <p>
     * default is LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
     *
     * @param requiredGpsPriority gps priority
     */
    public void setRequiredGpsPriority(int requiredGpsPriority) {
        this.requiredGpsPriority = requiredGpsPriority;
    }

    /**
     * Sets Update Interval also sets fastestUpdateIntervalInMilliseconds to half of updateIntervalInMilliseconds
     * default is 10 seconds
     *
     * @param updateIntervalInMilliseconds update Interval
     */
    public void setUpdateInterval(long updateIntervalInMilliseconds) {
        this.updateIntervalInMilliseconds = updateIntervalInMilliseconds;
        this.fastestUpdateIntervalInMilliseconds = updateIntervalInMilliseconds / 2;
    }

    /**
     * Sets fastest Update Interval
     * default is 5 seconds
     *
     * @param fastestUpdateIntervalInMilliseconds fastest update Interval
     */
    public void setFastestUpdateIntervalInMilliseconds(long fastestUpdateIntervalInMilliseconds) {
        this.fastestUpdateIntervalInMilliseconds = fastestUpdateIntervalInMilliseconds;
    }


    public void init() {
        createLocationRequest();
        buildLocationSettingsRequest();
    }


    public void setLocationCallback(LocationCallback locationCallback) {
        this.mLocationCallback = locationCallback;
    }

    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();

        mLocationRequest.setInterval(updateIntervalInMilliseconds);

        mLocationRequest.setFastestInterval(fastestUpdateIntervalInMilliseconds);

        mLocationRequest.setPriority(requiredGpsPriority);
    }


    private void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);
        mLocationSettingsRequest = builder.build();
    }


    public boolean isRequestingForLocation() {
        return mRequestingLocationUpdates;
    }


    public void checkForGpsSettings(GpsSettingsCheckCallback callback) {

        if (mLocationSettingsRequest == null) {
            throw new IllegalStateException("must call init() before check for gps settings");
        }

        // Begin by checking if the device has the necessary jobLocation settings.
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(locationSettingsResponse -> callback.requiredGpsSettingAreAvailable())
                .addOnFailureListener(e -> {

                    int statusCode = ((ApiException) e).getStatusCode();
                    switch (statusCode) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            Log.i(TAG, "SuggestedLocation settings are not satisfied. notifying back to the requesting object ");

                            ResolvableApiException rae = (ResolvableApiException) e;
                            callback.requiredGpsSettingAreUnAvailable(rae);

                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            Log.i(TAG, "Turn On SuggestedLocation From Settings. ");

                            callback.gpsSettingsNotAvailable();
                            break;
                    }

                });
    }


    /**
     * Starts location updates from the FusedLocationApi.
     * <p>
     *     Consider Calling {@link #stopLocationUpdates()} when you don't want location updates it helps in saving battery
     * </p>
     */
    public void startLocationUpdates() {

        if (mLocationRequest == null) {
            throw new IllegalStateException("must call init() before requesting location updates");
        }

//        if (mLocationCallback == null) {
//            throw new IllegalStateException("no callback provided for delivering location updates,use setLocationCallback() for setting callback");
//        }

        if (mRequestingLocationUpdates) {
            Log.d(TAG, "startLocationUpdates: already requesting location updates, no-op.");
            return;
        }

        Log.d(TAG, "startLocationUpdates: starting updates.");
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper())
                .addOnCompleteListener(task -> mRequestingLocationUpdates = true);

    }

    public void stopLocationUpdates() {
        if (!mRequestingLocationUpdates) {
            Log.d(TAG, "stopLocationUpdates: updates never requested, no-op.");
            return;
        }

        Log.d(TAG, "stopLocationUpdates: stopping location updates.");
        mFusedLocationClient.removeLocationUpdates(mLocationCallback)
                .addOnCompleteListener(task -> mRequestingLocationUpdates = false);
    }
}