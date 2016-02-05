package com.onemorebit.readthis.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Euro on 2/5/16 AD.
 */
public class ImageTextModel implements Parcelable {
    public Uri imageResUri;
    public String text;

    public ImageTextModel(Uri imageResId, String text) {
        this.imageResUri = imageResId;
        this.text = text;
    }

    @Override public String toString() {
        return "ImageTextModel{" +
            "text='" + text + '\'' +
            '}';
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.imageResUri, 0);
        dest.writeString(this.text);
    }

    protected ImageTextModel(Parcel in) {
        this.imageResUri = in.readParcelable(Uri.class.getClassLoader());
        this.text = in.readString();
    }

    public static final Parcelable.Creator<ImageTextModel> CREATOR = new Parcelable.Creator<ImageTextModel>() {
        public ImageTextModel createFromParcel(Parcel source) {
            return new ImageTextModel(source);
        }

        public ImageTextModel[] newArray(int size) {
            return new ImageTextModel[size];
        }
    };
}
