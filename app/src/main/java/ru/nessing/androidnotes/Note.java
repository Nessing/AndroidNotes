package ru.nessing.androidnotes;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private int id;
    private String title;
    private String date;
    private String description;
    private int[] image;

    public Note(int id, String title, String date, String description, int... image) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description + "\n";
        this.image = image;

    }

    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        date = in.readString();
        description = in.readString();
        image = in.createIntArray();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int[] getImage() {
        return image;
    }

    public void setImage(int[] image) {
        this.image = image;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(date);
        parcel.writeString(description);
        parcel.writeIntArray(image);
    }
}
