package ro.ubbcluj.phys.comodi.rockclimber.Views;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import ro.ubbcluj.phys.comodi.rockclimber.R;
import ro.ubbcluj.phys.comodi.rockclimber.Utils.GetLocation;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class AddRoute extends AppCompatActivity {
    private View mLoginFormView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLoginFormView = findViewById(R.id.editText5);
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button saveroute_btn = (Button) findViewById((R.id.save_route_button));
        saveroute_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mayRequestGPSLocation() && !mayRequestNetworkLocation()) {
                    return;
                }
                Context context = getApplicationContext();
                GetLocation locate = new GetLocation(context);

                double longitude = locate.getLongitude();
                double latitude = locate.getLatitude();
                Snackbar.make(view, "Replace with your own action" + longitude + " " + latitude, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private boolean mayRequestGPSLocation() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
         final int REQUEST_GPS_CLOCATION = 0;

        if (checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
            Snackbar.make(mLoginFormView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{ACCESS_FINE_LOCATION}, REQUEST_GPS_CLOCATION);
                        }
                    });
        } else {
            requestPermissions(new String[]{ACCESS_FINE_LOCATION}, REQUEST_GPS_CLOCATION);

        }
        return false;
    }


    private boolean mayRequestNetworkLocation() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        final int REQUEST_NET_CLOCATION = 0;

        if (checkSelfPermission(ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(ACCESS_COARSE_LOCATION)) {
            Snackbar.make(mLoginFormView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{ACCESS_COARSE_LOCATION}, REQUEST_NET_CLOCATION);
                        }
                    });
        } else {
            requestPermissions(new String[]{ACCESS_COARSE_LOCATION}, REQUEST_NET_CLOCATION);

        }
        return false;
    }

}
