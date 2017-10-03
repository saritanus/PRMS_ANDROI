package sg.edu.nus.iss.phoenix.user.android.delegate;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sg.edu.nus.iss.phoenix.user.android.controller.UserController;
import sg.edu.nus.iss.phoenix.user.entity.User;
import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_USER;

/**
 * @author Sujit Ambore
 * @version 1.0
 */
public class RetrieveUsersDelegate extends AsyncTask<String, Void, String>  {

	private static final String TAG = RetrieveUsersDelegate.class.getName();
	public UserController m_UserController;

	private UserController userController= null;
//	private ReviewSelectUserController reviewSelectUserController = null;

	public RetrieveUsersDelegate(UserController userController) {
		this.userController = userController;
	}


	@Override
	protected String doInBackground(String... params) {
		Uri builtUri1 = Uri.parse( PRMS_BASE_URL_USER).buildUpon().build();
		Uri builtUri = Uri.withAppendedPath(builtUri1, params[0]).buildUpon().build();
		Log.v(TAG, builtUri.toString());
		URL url = null;
		try {
			url = new URL(builtUri.toString());
		} catch (MalformedURLException e) {
			Log.v(TAG, e.getMessage());
			return e.getMessage();
		}

		String jsonResp = null;
		HttpURLConnection urlConnection = null;
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
			InputStream in = urlConnection.getInputStream();

			Scanner scanner = new Scanner(in);
			scanner.useDelimiter("\\A");
			if (scanner.hasNext()) jsonResp = scanner.next();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (urlConnection != null) urlConnection.disconnect();
		}

		return jsonResp;
	}

	@Override
	protected void onPostExecute(String result) {
		List<User> users = new ArrayList<>();

		if (result != null && !result.equals("")) {
			try {
				JSONObject reader = new JSONObject(result);
				JSONArray userArray = reader.getJSONArray("userList");

				for (int i = 0; i < userArray.length(); i++) {
					JSONObject userJson = userArray.getJSONObject(i);
					String name = userJson.getString("name");
					int userId = userJson.getInt("userId");
					String emailID = userJson.getString("emailID");
					//date
					String joiningDate = userJson.getString("joiningDate");


					User temp = new User();
					temp.setName(name);
					temp.setUserId(userId);
					temp.setEmailID(emailID);
					//date
					temp.setJoiningDate(joiningDate);
					users.add(temp);
				}
			} catch (JSONException e) {
				Log.v(TAG, e.getMessage());
			}

		}
		else { Log.v(TAG, "JSON response error.");}

		if (userController != null)
			userController.userRetrieved(users);
			}

	public void finalize() throws Throwable {super.finalize();}

}//end RetrieveUsersDelegate