package com.test.mi.testproject.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fcl on 18.1.3.
 */

public class StudentModel implements Parcelable {

    public String name;
    public int age;
    public boolean isJoined;

    public StudentModel() {
    }

    public StudentModel(Parcel in) {
        name = in.readString();
        age = in.readInt();
        isJoined = Boolean.valueOf(in.readString());
    }

    public static final Creator<StudentModel> CREATOR = new Creator<StudentModel>() {
        @Override
        public StudentModel createFromParcel(Parcel in) {
            return new StudentModel(in);
        }

        @Override
        public StudentModel[] newArray(int size) {
            return new StudentModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //注意顺序
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeString(String.valueOf(isJoined));
    }
}
