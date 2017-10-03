package sg.edu.nus.iss.phoenix.schedule.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sg.edu.nus.iss.phoenix.R;

/**
 * Created by vimal on 03-Oct-17.
 */

public class CopySchedule extends Activity implements View.OnClickListener {
    private EditText newStartDate;
    private Button copyNewSchedule;
    private int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
        newStartDate = (EditText) findViewById(R.id.new_Copy_Schedule_Date);
        copyNewSchedule = (Button) findViewById(R.id.btn_Copy_Schedule);
        newStartDate.setOnClickListener(this);
        copyNewSchedule.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
