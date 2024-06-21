package com.example.proiect.database;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "conversion_history")
public class ConversionHistory implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int userId;
    public String conversionType;
    public String inputValue;
    public String outputValue;
    public long timestamp;

    public ConversionHistory() {}

    protected ConversionHistory(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        conversionType = in.readString();
        inputValue = in.readString();
        outputValue = in.readString();
        timestamp = in.readLong();
    }

    public static final Creator<ConversionHistory> CREATOR = new Creator<ConversionHistory>() {
        @Override
        public ConversionHistory createFromParcel(Parcel in) {
            return new ConversionHistory(in);
        }

        @Override
        public ConversionHistory[] newArray(int size) {
            return new ConversionHistory[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(userId);
        dest.writeString(conversionType);
        dest.writeString(inputValue);
        dest.writeString(outputValue);
        dest.writeLong(timestamp);
    }
}
