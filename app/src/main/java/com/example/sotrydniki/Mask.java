package com.example.sotrydniki;

import android.os.Parcel;
import android.os.Parcelable;

public class Mask implements Parcelable {

    private int ID;
    private String Name;
    private String Surname;
    private String Img;
    private String Job_title;

    protected Mask(Parcel in) {
        ID = in.readInt();
        Name = in.readString();
        Surname = in.readString();
        Img = in.readString();
        Job_title = in.readString();
    }

    public static final Creator<Mask> CREATOR = new Creator<Mask>() {
        @Override
        public Mask createFromParcel(Parcel in) {
            return new Mask(in);
        }

        @Override
        public Mask[] newArray(int size) {
            return new Mask[size];
        }
    };

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setImg(String img) {
        Img = img;
    }

    public void setJob_title(String job_title) {
        Job_title = job_title;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getImg() {
        return Img;
    }

    public String getJob_title() {
        return Job_title;
    }

    public Mask(int ID, String name, String surname, String img, String job_title) {
        this.ID = ID;
        Name = name;
        Surname = surname;
        Img = img;
        Job_title = job_title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(Name);
        dest.writeString(Surname);
        dest.writeString(Img);
        dest.writeString(Job_title);
    }
}
