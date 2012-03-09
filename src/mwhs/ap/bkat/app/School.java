package mwhs.ap.bkat.app;

public class School {
	private String schoolName;
	private String schoolType;
	private String setting;
	private int totalUndergrads;
	private int tuitionInState;
	private int tuitionOutOfState;
	private int roomAndBoardCost;
	
	public School(String schoolName, String schoolType, String setting,
			String totalUndergrads, String tuitionInState,
			String tuitionOutOfState, String roomAndBoardCost) {
		
			this.schoolName = schoolName;
			this.schoolType = schoolType;
			this.setting = setting;
			this.totalUndergrads = Integer.parseInt(totalUndergrads);
			this.tuitionInState = Integer.parseInt(tuitionInState);
			this.tuitionOutOfState = Integer.parseInt(tuitionOutOfState);
			this.roomAndBoardCost = Integer.parseInt(roomAndBoardCost);
	}

	public String getSchoolName() {
		return schoolName;
	}

	public String getSchoolType() {
		return schoolType;
	}

	public String getSetting() {
		return setting;
	}

	public int getTotalUndergrads() {
		return totalUndergrads;
	}

	public int getTuitionInState() {
		return tuitionInState;
	}

	public int getTuitionOutOfState() {
		return tuitionOutOfState;
	}

	public int getRoomAndBoardCost() {
		return roomAndBoardCost;
	}

}
