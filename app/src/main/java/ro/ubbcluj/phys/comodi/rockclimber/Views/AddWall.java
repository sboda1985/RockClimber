package ro.ubbcluj.phys.comodi.rockclimber.Views;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ro.ubbcluj.phys.comodi.rockclimber.R;

import static android.R.string.cancel;


public class AddWall extends AppCompatActivity {

    private EditText gwallname;
    private EditText gwalldescription;
    private EditText gwallapproach;
    private Button savewallbutton;
    private AddWallTask mWallTask = null;
 


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wall);
        gwallname = (EditText) findViewById(R.id.wallname_editText);
        gwalldescription = (EditText) findViewById(R.id.walldescription_editText);
        gwallapproach = (EditText) findViewById(R.id.wallapproach_editText);
        savewallbutton = (Button) findViewById(R.id.savewall_button);
        savewallbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attemptSave();
//                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();


            }

            private void attemptSave() {

                if (mWallTask != null) {
                    return;
                }


                // Reset errors.
                gwallname.setError(null);
                gwalldescription.setError(null);
                gwallapproach.setError(null);

                boolean cancel = false;
                View focusView = null;
                 
                String wall_name = gwallname.getText().toString();
                String wall_description = gwalldescription.getText().toString();
                String wall_approach = gwallapproach.getText().toString();



                // Check for a valid wall  name, if the user entered one.
                if (TextUtils.isEmpty(wall_name)) {
                    gwallname.setError(getString(R.string.error_field_required));
                    focusView = gwallname;
                    cancel = true;
                }

                // Check for a valid wall description, if the user entered one.
                if (TextUtils.isEmpty(wall_description)) {
                    gwalldescription.setError(getString(R.string.error_field_required));
                    focusView = gwalldescription;
                    cancel = true;
                }


                // Check for a valid wall approach, if the user entered one.
                if (TextUtils.isEmpty(wall_approach)) {
                    gwallapproach.setError(getString(R.string.error_field_required));
                    focusView = gwallapproach;
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
                    mWallTask = new AddWall.AddWallTask(wall_name,wall_description,wall_approach);
                    mWallTask.execute((Void) null);

                }


            }

            


        });
    }

    public class AddWallTask extends AsyncTask<Void, Void, Boolean> {

        private final String wallname;
        private final String walldescription;
        private final String wallapproach;

        AddWallTask(String name, String description , String approach) {
            wallname = name;
            walldescription = description;
            wallapproach = approach;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mWallTask = null;


            if (success) {
                //if the login was successful, go to the Overview class
                Log.d(null, "save successfull");
                startActivity(new Intent(AddWall.this, OverView.class));
                //  finish();
//            } else {
//                groutename.setError(getString(R.string.error_field_required));
//                groutename.requestFocus();
//            }
            }
        }


        @Override
        protected void onCancelled() {
            mWallTask = null;
            ;
        }
    }

    
}
