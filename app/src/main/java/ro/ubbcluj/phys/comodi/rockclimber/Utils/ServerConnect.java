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

public class ServerConnect extends
        AsyncTask<Void, Void, Boolean> {
        private final String USER_AGENT = "Mozilla/5.0";

        String urlString = "http://www.yoursite.com/";

        private final String TAG = "post json example";
        private Context context;



        public ServerConnect(Context contex) {

            this.context = contex;

        }

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean status = false;

            String response = "";


            try {
                response = performPostCall(urlString, new HashMap<String, String>() {

                    private static final long serialVersionUID = 1L;

                    {
                        put("Accept", "application/json");
                        put("Content-Type", "application/json");
                    }
                });

            } catch (Exception e) {
                // displayLoding(false);


            }


            if (!response.equalsIgnoreCase("")) {
                try {

                    //
                    JSONObject jRoot = new JSONObject(response);
                    JSONObject d = jRoot.getJSONObject("d");

                    int ResultType = d.getInt("ResultType");


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


                status = false;
            }

            return status;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            //


            if (result) {
                Log.e(TAG, "8 - Update UI ...");
            } else {
                Log.e(TAG, "8 - Finish ...");

            }

        }

    /**
     * the function which does the post and returns the JSonobject
     *
     * @param requestURL
     * @param postDataParams
     * @return - server response to the post
     */
        public String performPostCall(String requestURL,
                                      HashMap<String, String> postDataParams) {

            URL url;
            String response = "";
            try {
                url = new URL(requestURL);

                //creating the post parameters from the passed hashmap
                String postData = "";
                for (Map.Entry<String,String> param : postDataParams.entrySet()) {
                    if (postData.length() != 0) postData+=("&");
                        postData+=(URLEncoder.encode(param.getKey(),"utf-8")+"="+URLEncoder.encode(param.getValue(),"utf-8"));

                }

                //open connection to the server
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                //specifying that this will be a post method
                conn.setRequestMethod("POST");
                conn.setRequestProperty("User-Agent", USER_AGENT);
                conn.setRequestProperty("Accept-Language", "UTF-8");
                conn.setDoOutput(true);
                conn.setDoInput(true);
                //parsing the POST parameters
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
                outputStreamWriter.write(postData);
                outputStreamWriter.flush();

                //get the response code, useful for later debugging, if something does not go well
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    //get the response and return it
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } else {
                    //something was not right, return an empty response
                    response = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }
    }

