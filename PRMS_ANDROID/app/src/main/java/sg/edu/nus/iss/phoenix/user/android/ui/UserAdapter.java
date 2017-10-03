package sg.edu.nus.iss.phoenix.user.android.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sg.edu.nus.iss.phoenix.R;
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

        User currentUser = getItem(position);

        TextView userName = (TextView)listItemView.findViewById(R.id.user_name);
        userName.setText(currentUser.getName(), TextView.BufferType.NORMAL);
        userName.setKeyListener(null); // This disables editing text.

        TextView userEmailId = (TextView) listItemView.findViewById(R.id.user_email_id);
        userEmailId.setText(currentUser.getEmailID(), TextView.BufferType.NORMAL);
        userEmailId.setKeyListener(null);

        //Date
        TextView userJoiningDate= (TextView) listItemView.findViewById(R.id.user_joining_date);
        userJoiningDate.setText(currentUser.getJoiningDate(), TextView.BufferType.NORMAL);
        userJoiningDate.setKeyListener(null);
//        return listItemView;

        //password
        TextView userPassword= (TextView) listItemView.findViewById(R.id.passworduser);
        userPassword.setText(currentUser.getPassword(), TextView.BufferType.NORMAL);
        userPassword.setKeyListener(null);


        return listItemView;

    }
}
