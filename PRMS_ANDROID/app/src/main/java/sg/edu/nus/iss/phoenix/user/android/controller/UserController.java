package sg.edu.nus.iss.phoenix.user.android.controller;

import android.content.Intent;
import android.util.Log;

import java.util.List;
import sg.edu.nus.iss.phoenix.core.android.controller.ControlFactory;
import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.radioprogram.android.delegate.CreateProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.android.delegate.DeleteProgramDelegate;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.user.android.delegate.CreateUserDelegate;
import sg.edu.nus.iss.phoenix.user.android.delegate.DeleteUserDelegate;
import sg.edu.nus.iss.phoenix.user.android.delegate.RetrieveUsersDelegate;
import sg.edu.nus.iss.phoenix.user.android.delegate.UpdateUserDelegate;
import sg.edu.nus.iss.phoenix.user.android.ui.MaintainUserList;
import sg.edu.nus.iss.phoenix.user.android.ui.UserListScreen;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 *
 */

public class UserController {
    private static final String TAG = UserController.class.getName();

    private UserListScreen userListScreen;
    private MaintainUserList maintainUserScreen;
    private User us2edit = null;

    public void startUseCase() {
        us2edit = null;
        Intent intent = new Intent(MainController.getApp(), UserListScreen.class);
        MainController.displayScreen(intent);
    }

    public void onDisplayUserList(UserListScreen userListScreen) {
        this.userListScreen = userListScreen;
        new  RetrieveUsersDelegate(this).execute("all");
    }

    public void userRetrieved(List<User> users) {
        userListScreen.showUsers(users);
    }

    public void selectCreateUser(User rp) {
        us2edit = null;
        new CreateUserDelegate(this).execute(rp);
        Intent intent = new Intent(MainController.getApp(), MaintainUserList.class);
        MainController.displayScreen(intent);

    }

    public void selectEditUser(User users) {
        us2edit = users;
        Log.v(TAG, "Editing user name: " + users.getUserName() + "...");

        Intent intent = new Intent(MainController.getApp(), MaintainUserList.class);
        MainController.displayScreen(intent);
    }

    public void onDisplayProgram(MaintainUserList maintainUserScreen) {
        this.maintainUserScreen = maintainUserScreen;
        if (us2edit == null)
            maintainUserScreen.createUser();
        else
            maintainUserScreen.editUser(us2edit);
    }

    public void selectUpdateUser(User rp) {
        new UpdateUserDelegate(this).execute(rp);
    }

    public void selectDeleteUser(User rp) {
        new DeleteUserDelegate(this).execute(rp.getUserName());
    }

    public void userDeleted(boolean success) {
        // Go back to UserList screen with refreshed users.
        startUseCase();
    }

    public void userUpdated(boolean success) {
        // Go back to UserList screen with refreshed users.
        startUseCase();
    }

    public void selectCreateUsers(User rp) {
        new CreateUserDelegate(this).execute(rp);
    }

    public void userCreated(boolean success) {
        // Go back to UserList screen with refreshed users.
        startUseCase();
    }

    public void selectCancelCreateEditUser() {
        // Go back to UserList screen with refreshed users.
        startUseCase();
    }

    public void maintainedUser() {
        ControlFactory.getMainController().maintainedUser();
    }

}
