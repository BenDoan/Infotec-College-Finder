package mwhs.ap.bkat.app;

import android.os.Parcel;
import android.os.Parcelable;

public class School implements Parcelable {
	private String schoolName;
	private String schoolType;
	private String setting;
	private String totalUndergrads;
	private String tuitionInState;
	private String tuitionOutOfState;
	private String roomAndBoardCost;
	
	public School(String schoolName, String schoolType, String setting,
			String totalUndergrads, String tuitionInState,
			String tuitionOutOfState, String roomAndBoardCost) {
		
			this.schoolName = schoolName;
			this.schoolType = schoolType;
			this.setting = setting;
			this.totalUndergrads = totalUndergrads;
			this.tuitionInState = tuitionInState;
			this.tuitionOutOfState = tuitionOutOfState;
			this.roomAndBoardCost = roomAndBoardCost;
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

	public String getTotalUndergrads() {
		return totalUndergrads;
	}

	public String getTuitionInState() {
		return tuitionInState;
	}

	public String getTuitionOutOfState() {
		return tuitionOutOfState;
	}

	public String getRoomAndBoardCost() {
		return roomAndBoardCost;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(schoolName);
		dest.writeString(schoolType);
		dest.writeString(setting);
		dest.writeString(totalUndergrads);
		dest.writeString(tuitionInState);
		dest.writeString(tuitionOutOfState);
		dest.writeString(roomAndBoardCost);
		
	}

}
