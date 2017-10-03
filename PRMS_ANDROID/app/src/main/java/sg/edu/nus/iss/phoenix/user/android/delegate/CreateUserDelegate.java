package sg.edu.nus.iss.phoenix.user.android.delegate;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import sg.edu.nus.iss.phoenix.user.android.controller.UserController;
import sg.edu.nus.iss.phoenix.user.entity.User;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_USER;

/**
 * @author sujit ambore
 * @version 1.0
 */
public class CreateUserDelegate extends AsyncTask<User, Void, Boolean> {
	// Tag for logging
	private static final String TAG = CreateUserDelegate.class.getName();
	private final UserController userController;
	int createdUserID =0;

	public CreateUserDelegate(UserController userController) {
		this.userController = userController; }

	@Override
	protected Boolean doInBackground(User... params) {
		Uri builtUri = Uri.parse(PRMS_BASE_URL_USER).buildUpon().build();
		builtUri = Uri.withAppendedPath(builtUri, "create").buildUpon().build();
		Log.v(TAG, builtUri.toString());
		URL url = null;
		try {
			url = new URL(builtUri.toString());
		} catch (MalformedURLException e) {
			Log.v(TAG, e.getMessage());
			return new Boolean(false);
		}

		JSONObject json = new JSONObject();
		try {

			json.put("emailID", params[0].getEmailID());
			json.put("joiningDate",params[0].getJoiningDate());
			json.put("name", params[0].getName());
			json.put("password",params[0].getPassword());

		} catch (JSONException e) {
			Log.v(TAG, e.getMessage());
		}



		boolean success = false;
		HttpURLConnection httpURLConnection = null;
		DataOutputStream dos = null;
		try {
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setInstanceFollowRedirects(false);
			httpURLConnection.setRequestMethod("PUT");
			httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			dos = new DataOutputStream(httpURLConnection.getOutputStream());
			dos.writeUTF(json.toString());
			dos.write(256);

			//gcode
			String resp;
			String result=null;
			Log.v(TAG, "Http PUT response " + httpURLConnection.getResponseCode());
			Log.v(TAG, "Print the user id inserted" + httpURLConnection.getResponseMessage());
			resp = httpURLConnection.getResponseMessage();
			BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			result = sb.toString();
			Log.v(TAG,"Response string"+result);
			this.createdUserID = Integer.parseInt(result);
			Log.v(TAG,"Response created userID"+this.createdUserID);

			//old code
			//Log.v(TAG, "Http PUT response " + httpURLConnection.getResponseCode());

			success = true;
		} catch (IOException exception) {
			Log.v(TAG, exception.getMessage());
		} finally {
			if (dos != null) {
				try {
					dos.flush();
					dos.close();
				} catch (IOException exception) {
					Log.v(TAG, exception.getMessage());
				}
			}
			if (httpURLConnection != null) httpURLConnection.disconnect();
		}
		return new Boolean(success);
	}

	@Override
	protected void onPostExecute(Boolean result) {
		userController.userCreated(this.createdUserID);

		//old code
		//userController.userCreated(result.booleanValue());
	}
	}


//end CreateUserDelegate


