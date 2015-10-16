package com.google.samples.quickstart.signin;

import android.accounts.Account;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import java.io.IOException;


public class GetTokenTask extends AsyncTask {
    Activity mActivity;
    String mScope;
    Account mEmail;

    GetTokenTask(Activity activity, Account name, String scope) {
        this.mActivity = activity;
        this.mScope = scope;
        this.mEmail = name;
    }
    private static final String TAG = "GetTokenTask";

    /**
     * Executes the asynchronous job. This runs when you call execute()
     * on the AsyncTask instance.
     */
    @Override
    protected Object doInBackground(Object[] params) {
        try {
            String token = fetchToken();
            Log.d(TAG, "token is: "+token);
           /*
           This part has not been tested yet.
            if (token != null) {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://yourserver/tokenauth");

                try {
                    List nameValuePairs = new ArrayList(1);
                    nameValuePairs.add(new BasicNameValuePair("idToken", token));
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);
                    int statusCode = response.getStatusLine().getStatusCode();
                    final String responseBody = EntityUtils.toString(response.getEntity());
                    Log.i(TAG, "Signed in as: " + responseBody);
                } catch (ClientProtocolException e) {
                    Log.e(TAG, "Error sending ID token to backend.", e);
                } catch (IOException e) {
                    Log.e(TAG, "Error sending ID token to backend.", e);
                }

            }*/
        } catch (IOException e) {
            Log.d(TAG, "exception: "+e);
            // The fetchToken() method handles Google-specific exceptions,
            // so this indicates something went wrong at a higher level.
            // TIP: Check for network connectivity before starting the AsyncTask.

        }

        return null;
    }

    /**
     * Gets an authentication token from Google and handles any
     * GoogleAuthException that may occur.
     */
    protected String fetchToken() throws IOException {
        try {
            return GoogleAuthUtil.getToken(mActivity, mEmail, mScope);
        } catch (IOException e) {
            Log.e(TAG, "Error retrieving ID token. IOException", e);
            return null;
        } catch (GoogleAuthException e) {
            Log.e(TAG, "Error retrieving ID token. Auth Exception", e);
            return null;
        }
    }

}

