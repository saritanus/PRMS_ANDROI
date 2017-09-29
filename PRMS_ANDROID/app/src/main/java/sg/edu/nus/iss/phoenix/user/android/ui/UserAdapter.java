package sg.edu.nus.iss.phoenix.user.android.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.user.entity.User;

public class UserAdapter extends ArrayAdapter<User> {

    public UserAdapter(@NonNull Context context,  ArrayList<User> users) {
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_add_user, parent, false);
        }
        //    Word currentWord = getItem(position);
        User currentUser = getItem(position);

        TextView userName = (TextView)listItemView.findViewById(R.id.user_name);
        userName.setText(currentUser.getName(), TextView.BufferType.NORMAL);
        userName.setKeyListener(null); // This disables editing.

        TextView userEmailId = (TextView) listItemView.findViewById(R.id.user_email_id);
        userEmailId.setText(currentUser.getEmailID(), TextView.BufferType.NORMAL);
        userEmailId.setKeyListener(null);

       /* TextView radioPMDuration = (TextView) listItemView.findViewById(R.id.maintain_program_duration_text_view);
        radioPMDuration.setText(currentUser.getRadioProgramDuration(), TextView.BufferType.NORMAL);
        radioPMDuration.setKeyListener(null);*/

        return listItemView;
    }
}
