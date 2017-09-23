package sg.edu.nus.iss.phoenix.schedule.entity;

import java.util.Date;
import sg.edu.nus.iss.phoenix.user.entity.Presenter;
import sg.edu.nus.iss.phoenix.user.entity.Producer;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.user.entity.Producer;

/**
 * This entity object represents a time slot in a weekly schedule as defined by
 * the radio station manager in which a radio program is broadcast.
 * @author Sarita
 * @created 20-Sep-2017 1:02:59 AM
 */
public final class ProgramSlot {

	private String assignedBy;
	private Date dateOfProgram;
	private Integer duration;
	private Date startTime;
	public Presenter m_Presenter;
	public Producer m_Producer;
	public RadioProgram m_RadioProgram;



	public void finalize() throws Throwable {

	}
	public void ProgramSlot(){

	}

	public void getAttribute(){

	}
}//end ProgramSlot