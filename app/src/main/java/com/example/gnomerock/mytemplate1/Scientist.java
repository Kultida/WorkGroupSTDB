package com.example.gnomerock.mytemplate1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gnomerock on 9/17/15.
 */
public class Scientist implements Parcelable {


    String name;
    String position;
    String organization;
    String address;
    String phone;
    String email;
    String image_url;

    protected Scientist(Parcel in) {
        name = in.readString();
        position = in.readString();
        organization = in.readString();
        address = in.readString();
        phone = in.readString();
        email = in.readString();
        image_url = in.readString();
    }

    public static final Creator<Scientist> CREATOR = new Creator<Scientist>() {
        @Override
        public Scientist createFromParcel(Parcel in) {
            return new Scientist(in);
        }

        @Override
        public Scientist[] newArray(int size) {
            return new Scientist[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                this.name,
                this.position,
                this.organization,
                this.address,
                this.phone,
                this.email,
                this.image_url

        });
    }



}
