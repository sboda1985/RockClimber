package ro.ubbcluj.phys.comodi.rockclimber.Views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import ro.ubbcluj.phys.comodi.rockclimber.R;
import ro.ubbcluj.phys.comodi.rockclimber.Utils.GetLocation;
import ro.ubbcluj.phys.comodi.rockclimber.Utils.GradeValues;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class AddRoute extends AppCompatActivity {
    private View mLoginFormView;
    private AddRouteTask mRouteTask = null;
    private EditText groutename;
    private Spinner gdifficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mLoginFormView = findViewById(R.id.editText_note);
                super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_route);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        groutename = (EditText) findViewById(R.id.editText_routename);
        Button saveroute_btn = (Button) findViewById((R.id.save_route_button));
        saveroute_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attemptSave();
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();


            }


        });

        final Spinner gradesystem_spinner = (Spinner)  findViewById((R.id.spinner_gradeing));
        final Spinner difficulty_spinner = (Spinner)  findViewById((R.id.spinner_difficulty));
        gradesystem_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                                                          public void onItemSelected(AdapterView<?> parent, View view, int position,       long id) {
                                                              String selected_grade = gradesystem_spinner.getSelectedItem().toString();
                                                              ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(getApplicationContext(),  R.layout.spinner_properties, new GradeValues().returngrades(selected_grade));
                                                              difficulty_spinner.setAdapter(newAdapter);

                                                          }

                                                          @Override
                                                          public void onNothingSelected(AdapterView<?> parent) {
                                                              String selected_grade = "UIAA";
                                                              ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(getApplicationContext(),  R.layout.spinner_properties, new GradeValues().returngrades(selected_grade));

                                                              difficulty_spinner.setAdapter(newAdapter);

                                                          }
                                                      });


        FloatingActionButton gpsbutton = (FloatingActionButton) findViewById((R.id.gpsbuton));
        gpsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mayRequestGPSLocation() && !mayRequestNetworkLocation()) {
                    return;
                }

                Context context = getApplicationContext();
                GetLocation locate = new GetLocation(context);
                locate.updateGPSCoordinates();
                double longitude = locate.getLongitude();
                double latitude = locate.getLatitude();

                EditText ed_longitude=(EditText) findViewById(R.id.editText_longitude);
                String stringdouble= Double.toString(longitude);
                ed_longitude.setText(stringdouble);
                EditText ed_latitude=(EditText) findViewById(R.id.editText_latitude);
                stringdouble= Double.toString(latitude);
                ed_latitude.setText(stringdouble);

                locate.stopUsingGPS();

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

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptSave() {
        if (mRouteTask != null) {
            return;
        }

        // Reset errors.
        groutename.setError(null);

        // Store values at the time of the login attempt.
        String route_name = groutename.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid route name, if the user entered one.
        if (TextUtils.isEmpty(route_name) ) {
            groutename.setError(getString(R.string.error_field_required));
            focusView = groutename;
            cancel = true;
        }
    //TODO do the same for the remaining required items, exact list to be submitted in Mantis

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            Log.e(null, "asd");
            mRouteTask = new AddRouteTask(route_name, "asd");
            mRouteTask.execute((Void) null);
        }
    }
    /**
     * Represents an asynchronous add route task to save the entered route into the database
     *
     */
    public class AddRouteTask extends AsyncTask<Void, Void, Boolean> {

        private final String Routename;
        private final String gradename;

        AddRouteTask(String route, String grade) {
            Routename = route;
            gradename = grade;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mRouteTask = null;


            if (success) {
                //if the login was successful, go to the Overview class
                Log.d(null, "save successfull");
                startActivity(new Intent(AddRoute.this, OverView.class));
                //  finish();
//            } else {
//                groutename.setError(getString(R.string.error_field_required));
//                groutename.requestFocus();
//            }
            }
        }


        @Override
        protected void onCancelled() {
            mRouteTask = null;
           ;
        }
    }

}
