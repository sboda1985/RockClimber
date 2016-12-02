package ro.ubbcluj.phys.comodi.rockclimber.Views;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
            
            
            }

            


        });
    }

    
}
