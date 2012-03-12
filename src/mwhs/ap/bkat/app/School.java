package mwhs.ap.bkat.app;

import android.os.Parcel;
import android.os.Parcelable;

public class School implements Parcelable{
	private String schoolName;
	private String schoolType;
	private String setting;
	private int totalUndergrads;
	private int tuitionInState;
	private int tuitionOutOfState;
	private int roomAndBoardCost;
	private String[] majors;
	private String[] sports;
	
	public String[] getMajors() {
		return majors;
	}

	public String[] getSports() {
		return sports;
	}

	public School(String schoolName, String schoolType, String setting,
			String totalUndergrads, String tuitionInState,
			String tuitionOutOfState, String roomAndBoardCost)  {
		
			this.schoolName = schoolName;
			this.schoolType = schoolType;
			this.setting = setting;
			this.totalUndergrads = Integer.parseInt(totalUndergrads);
			this.tuitionInState = Integer.parseInt(tuitionInState);
			this.tuitionOutOfState = Integer.parseInt(tuitionOutOfState);
			this.roomAndBoardCost = Integer.parseInt(roomAndBoardCost);
	}

	public School(Parcel in) {
		this.schoolName = in.readString();
		this.schoolType = in.readString();
		this.setting = in.readString();
		this.totalUndergrads = in.readInt();
		this.tuitionInState = in.readInt();
		this.tuitionOutOfState = in.readInt();
		this.roomAndBoardCost = in.readInt();
	}
	
	public School(){
		
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

	@Override
	public int describeContents() {
		return 0;
	}
	
	public String toString(){
		return "SCHOOL!";
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(schoolName);
		dest.writeString(schoolType);
		dest.writeString(setting);
		dest.writeInt(totalUndergrads);
		dest.writeInt(tuitionInState);
		dest.writeInt(tuitionOutOfState);
		dest.writeInt(roomAndBoardCost);
		
	}
	   public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
	        public School createFromParcel(Parcel in)
	        {
	            return new School(in);
	        }
	 
	        public School[] newArray(int size)
	        {
	            return new School[size];
	        }
	    };
	
}

