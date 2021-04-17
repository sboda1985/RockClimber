package org.hopto.bodaszilard.rockclimber.Views;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import org.hopto.bodaszilard.rockclimber.R;
import org.hopto.bodaszilard.rockclimber.Utils.ServerConnect;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText f_email;

    private String userID;
    private ForgotPasswordTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        f_email = (EditText) findViewById(R.id.f_p_email);
        android.widget.Button f_button = (Button) findViewById(R.id.f_p_ok);

        f_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("asd", "button pushed");
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
        Log.d("attemptforgotpass", "ok authtask");
        // Reset errors.
        f_email.setError(null);


        // Store values at the time of the login attempt.
        String email = f_email.getText().toString();
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
            Log.d("asd", "attempt to start reset");
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
            boolean value = checkemail();
            Log.d("doinback", String.valueOf(value));
            return value;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            //showProgress(false);

            if (success) {
                Intent myIntent = new Intent(ForgotPasswordActivity.this, ResetPasswordWithPin.class);
                myIntent.putExtra("id", userID);
                Log.e("asd", "start next intent");
                startActivity(myIntent);
            }
        }

        protected boolean checkemail() {
            ServerConnect pw = new ServerConnect(getApplicationContext());
            HashMap hm = new HashMap();
            hm.put("email", mEmail);
            String url = "https://bodaszilard.hopto.org/rockclimber/forgotpassword/";
            String match = pw.performPostCall(url, hm);
            Log.e("asd", match);
            if (match.contains("email sent")) {
                try {
                    JSONObject json = new JSONObject(match);
                    userID = json.getString("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
