package lee.vioson.nicepicservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ListData {
    @Id
    @GeneratedValue
    private Integer id;

    private String href;

    private String src;

    private String alt;


    private String type;

    private int typeId;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    @Override
    public String toString() {
        return "ListData{" +
                "id=" + id +
                ", href='" + href + '\'' +
                ", src='" + src + '\'' +
                ", alt='" + alt + '\'' +
                ", type='" + type + '\'' +
                ", typeId=" + typeId +
                '}';
    }
}
