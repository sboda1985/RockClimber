package ro.ubbcluj.phys.comodi.rockclimber.Utils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by sboda on 11/8/16.
 */

public class PasswordCheck extends
        AsyncTask<Void, Void, Boolean> {
        private final String USER_AGENT = "Mozilla/5.0";

        String urlString = "http://www.yoursite.com/";

        private final String TAG = "post json example";
        private Context context;



        public PasswordCheck(Context contex) {

            this.context = contex;

        }

        @Override
        protected void onPreExecute() {
            Log.e(TAG, "1 - RequestVoteTask is about to start...");

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean status = false;

            String response = "";
            Log.e(TAG, "2 - pre Request to response...");

            try {
                response = performPostCall(urlString, new HashMap<String, String>() {

                    private static final long serialVersionUID = 1L;

                    {
                        put("Accept", "application/json");
                        put("Content-Type", "application/json");
                    }
                });
                Log.e(TAG, "3 - give Response...");
                Log.e(TAG, "4 " + response.toString());
            } catch (Exception e) {
                // displayLoding(false);

                Log.e(TAG, "Error ...");
            }
            Log.e(TAG, "5 - after Response...");

            if (!response.equalsIgnoreCase("")) {
                try {
                    Log.e(TAG, "6 - response !empty...");
                    //
                    JSONObject jRoot = new JSONObject(response);
                    JSONObject d = jRoot.getJSONObject("d");

                    int ResultType = d.getInt("ResultType");
                    Log.e("ResultType", ResultType + "");

                    if (ResultType == 1) {

                        status = true;

                    }

                } catch (JSONException e) {
                    // displayLoding(false);
                    // e.printStackTrace();
                    Log.e(TAG, "Error " + e.getMessage());
                } finally {

                }
            } else {
                Log.e(TAG, "6 - response is empty...");

                status = false;
            }

            return status;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            //
            Log.e(TAG, "7 - onPostExecute ...");

            if (result) {
                Log.e(TAG, "8 - Update UI ...");

                // setUpdateUI(adv);
            } else {
                Log.e(TAG, "8 - Finish ...");

                // displayLoding(false);
                // finish();
            }

        }

        public String performPostCall(String requestURL,
                                      HashMap<String, String> postDataParams) {

            URL url;
            String response = "";
            try {
                url = new URL(requestURL);

                String postData = "";
                for (Map.Entry<String,String> param : postDataParams.entrySet()) {
                    if (postData.length() != 0) postData+=("&");
//                    postData.append(URLEncoder.encode(, "UTF-8"));
//                    postData.append('=');
//                    postData.append(URLEncoder.encode(String.valueOf(), "UTF-8"));


                        postData+=(URLEncoder.encode(param.getKey(),"utf-8")+"="+URLEncoder.encode(param.getValue(),"utf-8"));
                        Log.e(TAG, URLEncoder.encode(param.getKey(),"utf-8")+"="+URLEncoder.encode(param.getValue(),"utf-8"));

                }
                Log.e(TAG, "11 - url :  " +postData);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("User-Agent", USER_AGENT);
                conn.setRequestProperty("Accept-Language", "UTF-8");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                //conn.getOutputStream().write(postDataBytes);

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
                outputStreamWriter.write(postData);
                outputStreamWriter.flush();
                Log.e(TAG, "11 - url : " + requestURL + " " +postData);

            /*
             * JSON
             */


                //
//                String token = Static.getPrefsToken(context);
//
//                root.put("securityInfo", getSecurityInfo(context));
//                root.put("advertisementId", 11);

//                String str = root.toString();
//                byte[] outputBytes = str.getBytes("UTF-8");
//                OutputStream os = conn.getOutputStream();
//                os.write(outputBytes);
               // Log.e(TAG, "12 - fullurl  : " + str + " - " + os);
                int responseCode = conn.getResponseCode();

                Log.e(TAG, "13 - responseCode : " + responseCode);

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    Log.e(TAG, "14 - HTTP_OK");

                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } else {
                    Log.e(TAG, "14 - False - HTTP_OK");
                    response = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }
    }

