package ro.ubbcluj.phys.comodi.rockclimber.Views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import java.util.HashMap;

import ro.ubbcluj.phys.comodi.rockclimber.R;
import ro.ubbcluj.phys.comodi.rockclimber.Utils.ServerConnect;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText f_email;
    private Button f_button;
    private ForgotPasswordTask mAuthTask = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        f_email =(EditText) findViewById(R.id.f_p_email);
        f_button =(Button) findViewById(R.id.f_p_ok);

        f_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d(null, "Email sent...");
                //TODO write method to send an email containing a secret code
                attemptForgotPassword();
            }
        });




    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }
    private void attemptForgotPassword() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        f_email.setError(null);


        // Store values at the time of the login attempt.
        String email =f_email.getText().toString();
       // String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;




        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            f_email.setError(getString(R.string.error_field_required));
            focusView = f_email;
            cancel = true;
        } else if (!isEmailValid(email)) {
            f_email.setError(getString(R.string.error_invalid_email));
            focusView = f_email;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
           // showProgress(true);
            mAuthTask = new ForgotPasswordActivity.ForgotPasswordTask(email);
            mAuthTask.execute((Void) null);
        }
    }
    public class ForgotPasswordTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;


        ForgotPasswordTask(String email) {
            mEmail = email;

        }

        @Override
        protected Boolean doInBackground(Void... params) {

            return  checkemail();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            //showProgress(false);

            if (success) {
                //if the login was successful, go to the Overview class
                //startActivity(new Intent(, OverView.class));
                //  finish();
            } else {



            }
        }

        protected boolean checkemail(){
            ServerConnect pw = new ServerConnect(getApplicationContext());
            HashMap hm = new HashMap();
            hm.put("email", mEmail);

            String url = "http://comodi.phys.ubbcluj.ro:8000/forgotpassword/";
            String match = pw.performPostCall(url, hm);
            if (match.contains("true")){
                return true;
            } else {
                return false;
            }

        }
        @Override
        protected void onCancelled() {
            mAuthTask = null;

        }
    }
}
