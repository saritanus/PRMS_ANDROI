package sg.edu.nus.iss.phoenix.schedule.android.delegate;

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

import sg.edu.nus.iss.phoenix.radioprogram.android.controller.ProgramController;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.android.controller.ScheduleController;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_SCHEDULE;

public class CreateScheduleDelegate extends AsyncTask<ProgramSlot, Void, Boolean> {
	// Tag for logging
	private static final String TAG = CreateScheduleDelegate.class.getName();

	private final ScheduleController scheduleController;

	public CreateScheduleDelegate(ScheduleController scheduleController) {
		this.scheduleController = scheduleController;
	}

	@Override
	protected Boolean doInBackground(ProgramSlot... params) {
		Uri builtUri = Uri.parse(PRMS_BASE_URL_SCHEDULE).buildUpon().build();
		builtUri = Uri.withAppendedPath(builtUri,"create").buildUpon().build();
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
			json.put("startTime", params[0].getStartTime());
			json.put("duration", params[0].getDuration());

			//Setting the value of Presenter
			JSONArray arrayPresenter = new JSONArray();
			JSONObject arrayElementPresenter = new JSONObject();
			arrayElementPresenter.put("name",params[0].getPresenter().getName());
			arrayPresenter.put(arrayElementPresenter);
			json.put("presenter",arrayPresenter);

			//Setting the value of Producer
			JSONArray arrayProducer = new JSONArray();
			JSONObject arrayElementProducer = new JSONObject();
			arrayElementProducer.put("name",params[0].getProducer().getName());
			arrayProducer.put(arrayElementProducer);
			json.put("producer",arrayProducer);

			//Setting the value of Radio Program
			JSONArray arrayRadioProgram = new JSONArray();
			JSONObject arrayElementRadioProgram = new JSONObject();
			arrayElementRadioProgram.put("name",params[0].getRadioProgram().getRadioProgramName());
			arrayRadioProgram.put(arrayElementRadioProgram);
			json.put("radioProgram",arrayRadioProgram);

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
		scheduleController.scheduleCreated(result.booleanValue());
	}
}
