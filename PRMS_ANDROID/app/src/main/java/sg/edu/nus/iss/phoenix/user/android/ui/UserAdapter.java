package sg.edu.nus.iss.phoenix.user.android.ui;

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
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * Created by User on 24/9/2017.
 */

public class UserAdapter extends ArrayAdapter<User> {
    private UserAdapter mUSAdapter;

    public UserAdapter(@NonNull MaintainUserList maintainUserList, ArrayList<User> users) {
        super(maintainUserList, 0, users);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_user_list, parent, false);
        }
        //    Word currentWord = getItem(position);
        User currentRP = getItem(position);

        EditText userPMName = (EditText)listItemView.findViewById(R.id.maintain_user_name_text_view);
        userPMName.setText(currentRP.getUserName(), TextView.BufferType.NORMAL);
        userPMName.setKeyListener(null); // This disables editing

        EditText userPMDesc = (EditText)listItemView.findViewById(R.id.maintain_user_desc_text_view);
        userPMDesc.setText(currentRP.getUserRoleDescription(), TextView.BufferType.NORMAL);
        userPMDesc.setKeyListener(null);

        return listItemView;
    }

    public void add(User user) {
    }

    public void clear() {

    }
}
