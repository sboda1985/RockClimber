package org.hopto.bodaszilard.rockclimber.Views;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.hopto.bodaszilard.rockclimber.R;


public class AddRegion extends AppCompatActivity {

    private EditText gregionname;
    private EditText gregiondescription;
    private EditText gregionapproach;
    private Button saveregionbutton;
    private AddRegionTask mRegionTask = null;
 


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_region);
        gregionname = (EditText) findViewById(R.id.regionname_editText);
        gregiondescription = (EditText) findViewById(R.id.regiondescription_editText);
        gregionapproach = (EditText) findViewById(R.id.regionapproach_editText);
        saveregionbutton = (Button) findViewById(R.id.saveregion_button);
        saveregionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attemptSave();
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();


            }

            private void attemptSave() {

                if (mRegionTask != null) {
                    return;
                }


                // Reset errors.
                gregionname.setError(null);
                gregiondescription.setError(null);
                gregionapproach.setError(null);

                boolean cancel = false;
                View focusView = null;
                 
                String region_name = gregionname.getText().toString();
                String region_description = gregiondescription.getText().toString();
                String region_approach = gregionapproach.getText().toString();



                // Check for a valid wall  name, if the user entered one.
                if (TextUtils.isEmpty(region_name)) {
                    gregionname.setError(getString(R.string.error_field_required));
                    focusView = gregionname;
                    cancel = true;
                }

                // Check for a valid wall description, if the user entered one.
                if (TextUtils.isEmpty(region_description)) {
                    gregiondescription.setError(getString(R.string.error_field_required));
                    focusView = gregiondescription;
                    cancel = true;
                }


                // Check for a valid wall approach, if the user entered one.
                if (TextUtils.isEmpty(region_approach)) {
                    gregionapproach.setError(getString(R.string.error_field_required));
                    focusView = gregionapproach;
                    cancel = true;
                }


                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    focusView.requestFocus();
                } else {
                    // Show a progress spinner, and kick off a background task to
                    // perform the user login attempt.
                    Log.e(null, "asd");
                    mRegionTask = new AddRegion.AddRegionTask(region_name,region_description,region_approach);
                    mRegionTask.execute((Void) null);

                }


            }

            


        });
    }

    public class AddRegionTask extends AsyncTask<Void, Void, Boolean> {

        private final String regionname;
        private final String regiondescription;
        private final String regionapproach;

        AddRegionTask(String name, String description , String approach) {
            regionname = name;
            regiondescription = description;
            regionapproach = approach;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mRegionTask = null;


            if (success) {
                //if the login was successful, go to the Overview class
                Log.d(null, "save successfull");
                startActivity(new Intent(AddRegion.this, OverView.class));
                //  finish();
//            } else {
//                groutename.setError(getString(R.string.error_field_required));
//                groutename.requestFocus();
//            }
            }
        }


        @Override
        protected void onCancelled() {
            mRegionTask = null;
            ;
        }
    }

    
}
