package sg.edu.nus.iss.phoenix.radioprogram.entity;

public class RadioProgram {

    private int radioProgramId;
    private String radioProgramName;
    private String radioProgramDescription;
    private String radioProgramDuration;

    public RadioProgram(int radioProgramId,String radioProgramName, String radioProgramDescription, String radioProgramDuration) {
        this.radioProgramName = radioProgramName;
        this.radioProgramDescription = radioProgramDescription;
        this.radioProgramDuration = radioProgramDuration;
        this.radioProgramId = radioProgramId;
    }

    public RadioProgram(String radioProgramName, String radioProgramDescription, String radioProgramDuration) {
        this.radioProgramName = radioProgramName;
        this.radioProgramDescription = radioProgramDescription;
        this.radioProgramDuration = radioProgramDuration;
    }

    public RadioProgram() {
        
    }

    public String getRadioProgramName() {
        return radioProgramName;
    }

    public int getRadioProgramId() {
        return radioProgramId;
    }

    public String getRadioProgramDescription() {
        return radioProgramDescription;
    }

    public String getRadioProgramDuration() {
        return radioProgramDuration;
    }

    public void setRadioProgramDescription(String radioProgramDescription) {
        this.radioProgramDescription = radioProgramDescription;
    }

    public void setRadioProgramId(int radioProgramId)
    {
        this.radioProgramId = radioProgramId;
    }

    public void setRadioProgramDuration(String radioProgramDuration) {
        this.radioProgramDuration = radioProgramDuration;
    }
}
