package sg.edu.nus.iss.phoenix.user.android.delegate;



import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import sg.edu.nus.iss.phoenix.user.android.controller.UserController;
import sg.edu.nus.iss.phoenix.user.entity.User;

import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_USER;


public class AssignRoleDelegate extends AsyncTask<User, Void, Boolean> {
	// Tag for logging
	private static final String TAG = AssignRoleDelegate.class.getName();
	private final UserController userController;
	int edit;

//	public AssignRoleDelegate(UserController userController) {
//		this.userController = userController; }

	public AssignRoleDelegate(UserController userController,int editIn) {
		this.userController = userController;
		this.edit = editIn;
	}

	@Override
	protected Boolean doInBackground(User... params) {
		Uri builtUri = Uri.parse(PRMS_BASE_URL_USER).buildUpon().build();
		if (edit == 0) {
			builtUri = Uri.withAppendedPath(builtUri, "assignrole").buildUpon().build();
		} else {
			builtUri = Uri.withAppendedPath(builtUri, "updaterole").buildUpon().build();
		}
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
			JSONArray roleId = new JSONArray();
			for ( int role : params[0].getRoleId())
			{
				roleId.put(role);
			}
			json.put("userId", params[0].getUserId());
			Log.v(TAG, "UserID parsing:" + params[0].getUserId() + "...");
			json.put("roleId",roleId);
			Log.v(TAG, "RoleID parsing:" + params[0].getRoleId() + "...");

		} catch (JSONException e) {
			Log.v(TAG, e.getMessage());
		}



		boolean success = false;
		HttpURLConnection httpURLConnection = null;
		DataOutputStream dos = null;
		try {
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setInstanceFollowRedirects(false);
			if ( edit == 0) {
				httpURLConnection.setRequestMethod("PUT");
			} else {
				httpURLConnection.setRequestMethod("POST");
			}
			httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=utf8");
			httpURLConnection.setDoInput(true);
			httpURLConnection.setDoOutput(true);
			dos = new DataOutputStream(httpURLConnection.getOutputStream());
			dos.writeUTF(json.toString());
			Log.v(TAG, "The JSON Data is"+json.toString());
			dos.write(256);
			Log.v(TAG, "Http PUT response " + httpURLConnection.getResponseCode());
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
		userController.roleAssigned(result.booleanValue());
	}
}


//end CreateUserDelegate


