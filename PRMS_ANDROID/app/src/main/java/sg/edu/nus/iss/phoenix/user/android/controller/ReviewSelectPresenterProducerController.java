package sg.edu.nus.iss.phoenix.user.android.controller;

import android.content.Intent;

import java.util.List;

import sg.edu.nus.iss.phoenix.core.android.controller.MainController;
import sg.edu.nus.iss.phoenix.radioprogram.android.ui.ReviewSelectProgramScreen;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;
import sg.edu.nus.iss.phoenix.user.entity.User;

/**
 * @author sujit ambore
 * @version 1.0
 * @created 24-Sep-2017 2:09:55 AM
 */
public class ReviewSelectPresenterProducerController {

	public Presenter m_Presenter;
	public Producer m_Producer;
	public Producer m_System_Admin;
	public Producer m_System_Manager;

	private static final String TAG = ReviewSelectPresenterProducerController.class.getName();


	private User rpSelected = null;

	public ReviewSelectPresenterProducerController(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param displayScreen
	 */
	/*public void onDisplay(PresenterProducerScreen displayScreen){

	}

	/**
	 * 
	 * @param presenterList
	 */
	/*public void presentersRetrieved(List<Presenter> presenterList){

	}

	/**
	 * 
	 * @param producerList
	 */
	public void producersRetrieved(List<Producer> producerList){

	}

	public void reviewSelectPresenter(){

	}

	public void reviewSelectProducer(){

	}

	public void searchPresenter(){

	}

	public void searchProducer(){

	}

	public void selectCancel(){

	}

	/**
	 * 
	 * @param presenter
	 */
	public void selectPresenter(Presenter presenter){

	}

	/*public selectPresenterProducer(){

	}

	/**
	 * 
	 * @param producer
	 */
	public void selectProducer(Producer producer){

	}

	public void showPresenters(){

	}

	public void startUsecase(){

	}
}//end ReviewSelectPresenterProducerController