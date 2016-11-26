package ro.ubbcluj.phys.comodi.rockclimber.Views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ro.ubbcluj.phys.comodi.rockclimber.R;
import ro.ubbcluj.phys.comodi.rockclimber.Utils.ServerConnect;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class ResetPasswordWithPin extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;


    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mPinView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_with_pin);
        // Set up the login form.
        mPinView = (EditText) findViewById(R.id.resetpasswordpin);

        mPasswordView = (EditText) findViewById(R.id.resetpassword);

        mConfirmPasswordView = (EditText) findViewById(R.id.confirmresetpassword);


        Button resetpasswordbtn = (Button) findViewById(R.id.resetpasswordbutton);
        resetpasswordbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptPasswordReset();
            }
        });

    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptPasswordReset() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mPinView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String pin = mPinView.getText().toString();
        String password = mPasswordView.getText().toString();
        String confirmpassword = mConfirmPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!password.equals(confirmpassword)){
            mConfirmPasswordView.setError(getString(R.string.error_password_not_match));
            focusView = mConfirmPasswordView;
            cancel = true;
        }



        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(pin)) {
            mPinView.setError(getString(R.string.error_field_required));
            focusView = mPinView;
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
            mAuthTask = new UserLoginTask(pin, password);
            mAuthTask.execute((Void) null);
        }
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }




    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }
*/
    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mPin;
        private final String mPassword;
        private  String mRegerror;
        UserLoginTask(String email, String password) {
            mPin = email;
            mPassword = password;
            mRegerror=null;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            return changepassword();

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            //showProgress(false);

            if (success) {
                //if the registration was successful, go to the Overview class
                startActivity(new Intent(ResetPasswordWithPin.this, OverView.class));
            } else {
                if(mRegerror.contains("password recovery not requested")){
                    mPinView.setError("Incorrect PIN, please try again ");
                }
                if(mRegerror.contains("to many attempts")){
                    mPinView.setError("You tried to many times to reset your password, wait a day");
                }

                mPasswordView.requestFocus();
            }
        }
        protected boolean changepassword(){
            ServerConnect pw = new ServerConnect(getApplicationContext());
            HashMap hm = new HashMap();
            hm.put("pin", mPin);
            hm.put("password", mPassword);
            Intent myIntent = getIntent(); // gets the previously created intent
            String id = myIntent.getStringExtra("id");
            hm.put("id",  id);
            String url = "http://comodi.phys.ubbcluj.ro:8000/resetpasswordwithpin/";
            String match = pw.performPostCall(url, hm);
            mRegerror = match;
            Log.e("asd", match);
            if(match.contains("success")){return true;}
           return false;
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
          //  showProgress(false);
        }
    }
}

