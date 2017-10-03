package sg.edu.nus.iss.phoenix.schedule.android.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.core.android.util.PhoenixUtil;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class ScheduleAdapter extends ArrayAdapter<ProgramSlot> {

    public ScheduleAdapter(@NonNull Context context,  ArrayList<ProgramSlot> programSlots) {
        super(context, 0, programSlots);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_create_schedule, parent, false);
        }
        //    Word currentWord = getItem(position);
        ProgramSlot programSlot = getItem(position);

        TextView radioProgramName = (TextView)listItemView.findViewById(R.id.edittext_radioprogram);
        radioProgramName.setText(programSlot.getRadioProgram().getRadioProgramName(),TextView.BufferType.NORMAL);
        radioProgramName.setKeyListener(null); // This disables editing.

        TextView presenter = (TextView) listItemView.findViewById(R.id.edittext_presenter);
        presenter.setText(programSlot.getPresenter().getName(), TextView.BufferType.NORMAL);
        presenter.setKeyListener(null);

        TextView producer = (TextView) listItemView.findViewById(R.id.edittext_producer);
        producer.setText(programSlot.getProducer().getName(), TextView.BufferType.NORMAL);
        producer.setKeyListener(null);

        TextView scheduleStartDate = (TextView) listItemView.findViewById(R.id.schedule_start_date);
        scheduleStartDate.setText(programSlot.getStartTime(), TextView.BufferType.NORMAL);
        scheduleStartDate.setKeyListener(null);

        TextView duartion = (TextView) listItemView.findViewById(R.id.schedule_duration);
        duartion.setText(programSlot.getDuration(), TextView.BufferType.NORMAL);
        producer.setKeyListener(null);



        return listItemView;
    }
}
