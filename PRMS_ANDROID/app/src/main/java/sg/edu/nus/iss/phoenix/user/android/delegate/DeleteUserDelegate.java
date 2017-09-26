package sg.edu.nus.iss.phoenix.user.android.delegate;

import android.os.AsyncTask;

import sg.edu.nus.iss.phoenix.radioprogram.android.delegate.DeleteProgramDelegate;
import sg.edu.nus.iss.phoenix.user.android.controller.UserController;


/**
 * @author sujit ambore
 * @version 1.0
 * @created 24-Sep-2017 2:09:53 AM
 */
public class DeleteUserDelegate extends AsyncTask<String, Void, Boolean> {
	// Tag for logging
	private static final String TAG = DeleteProgramDelegate.class.getName();

	private final UserController userController;

	public DeleteUserDelegate(UserController userController) {
		this.userController = userController;
	}



	public void finalize() throws Throwable {
		super.finalize();
	}
	public void DeleteUserDelegate(){

	}

	public void doInBackground(){

	}

	public void execute(){

	}

	public void onPostExecute(){

	}


	@Override
	protected Boolean doInBackground(String... params) {
		return null;
	}
}//end DeleteUserDelegate