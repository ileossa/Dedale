package babar.application.ileossa.babar.gallery.cutomGridView;

/**
 * Created by ileossa on 18/08/2017.
 */

public class Items {

    String title;
    Integer image;

    public Items(String title, Integer image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
