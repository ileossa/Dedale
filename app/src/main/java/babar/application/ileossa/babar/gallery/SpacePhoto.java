package babar.application.ileossa.babar.gallery;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ileossa on 16/08/2017.
 */

public class SpacePhoto implements Parcelable {

    private String mUrl;
    private String mTitle;

    public SpacePhoto(String mUrl, String mTitle) {
        this.mUrl = mUrl;
        this.mTitle = mTitle;
    }

    protected SpacePhoto(Parcel in) {
        mUrl = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<SpacePhoto> CREATOR = new Creator<SpacePhoto>() {

        @Override
        public SpacePhoto createFromParcel(Parcel source) {
            return new SpacePhoto(source);
        }

        @Override
        public SpacePhoto[] newArray(int size) {
            return new SpacePhoto[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUrl);
        dest.writeSerializable(mTitle);
    }


    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public static SpacePhoto[] getSpacePhotos(){
        return new SpacePhoto[]{
                new SpacePhoto("http://i.imgur.com/zuG2bGQ.jpg", "Galaxy"),
                new SpacePhoto("http://i.imgur.com/ovr0NAF.jpg", "Space Shuttle"),
                new SpacePhoto("http://i.imgur.com/n6RfJX2.jpg", "Galaxy Orion"),
                new SpacePhoto("http://i.imgur.com/qpr5LR2.jpg", "Earth"),
                new SpacePhoto("http://i.imgur.com/pSHXfu5.jpg", "Astronaut"),
                new SpacePhoto("http://i.imgur.com/3wQcZeY.jpg", "Satellite"),
        };
    }
}
