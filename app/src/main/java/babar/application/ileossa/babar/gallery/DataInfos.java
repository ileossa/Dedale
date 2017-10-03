package babar.application.ileossa.babar.gallery;

import java.io.Serializable;

/**
 * Created by ileossa on 04/10/2017.
 */
@SuppressWarnings("serial")
class DataInfos implements Serializable {

    private int id;
    private String name;
    private String tag;
    private String classpath;

    protected DataInfos() {
    }

    public DataInfos(int id, String name, String tag, String classpath) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.classpath = classpath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getClasspath() {
        return classpath;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }
}
