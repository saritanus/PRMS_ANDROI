package sg.edu.nus.iss.phoenix.schedule.android.delegate;

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
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sg.edu.nus.iss.phoenix.core.android.util.PhoenixUtil;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.android.controller.ScheduleController;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.user.entity.User;
import static sg.edu.nus.iss.phoenix.core.android.delegate.DelegateHelper.PRMS_BASE_URL_SCHEDULE;

public class RetrieveSchedulesDelegate extends AsyncTask<String, Void, String> {
    // Tag for logging
    private static final String TAG = RetrieveSchedulesDelegate.class.getName();
    String toDate =null;
    String fromDate= null;

    private ScheduleController scheduleController = null;
    //private ReviewSelectProgramController reviewSelectProgramController = null;

    public RetrieveSchedulesDelegate(String toDate, String fromDate) {
        this.toDate = toDate;
        this.fromDate =fromDate;
        //this.scheduleController = scheduleController;
    }

   public RetrieveSchedulesDelegate(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;

    }



    @Override
    protected String doInBackground(String... params) {
        Uri builtUri1 = Uri.parse( PRMS_BASE_URL_SCHEDULE).buildUpon().build();
        Uri builtUri2 = Uri.withAppendedPath(builtUri1, params[0]).buildUpon().build();
        Uri builtUri3 = Uri.withAppendedPath(builtUri2,toDate).buildUpon().build();
        Uri builtUri = Uri.withAppendedPath(builtUri3,fromDate).buildUpon().build();

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
        List<ProgramSlot> programSlots = new ArrayList<>();

        if (result != null && !result.equals("")) {
            try {
                JSONObject reader = new JSONObject(result);
                JSONArray psArray = reader.getJSONArray("slotList");

                for (int i = 0; i < psArray.length(); i++) {
                    JSONObject psJson = psArray.getJSONObject(i);
                    ProgramSlot programSlot = new ProgramSlot();
                    programSlot.setId( psJson.getInt("id"));
                    JSONObject rpJson = psJson.getJSONObject("radioProgram");
                    RadioProgram rp = new RadioProgram();
                    rp.setRadioProgramId(rpJson.getInt("radioId"));
                    rp.setRadioProgramName(rpJson.getString("name"));
                    programSlot.setRadioProgram(rp);
                    programSlot.setDuration(psJson.getString("duration"));
                    JSONObject presenter = psJson.getJSONObject("presenter");
                    JSONObject producer = psJson.getJSONObject("producer");
                    programSlot.setPresenter(new User(presenter.getInt("userId"),presenter.getString("name")));
                    programSlot.setProducer(new User(producer.getInt("userId"),producer.getString("name")));
                    programSlot.setStartTime(psJson.getString("startTime"));
                    programSlot.setEndTime(psJson.getString("endTime"));
                    programSlots.add(programSlot);
                }
            } catch (JSONException e) {
                Log.v(TAG, e.getMessage());
            }

        } else {
            Log.v(TAG, "JSON response error.");
        }
        if (scheduleController != null)
            scheduleController.scheduleRetrieved(programSlots);


    }
}
