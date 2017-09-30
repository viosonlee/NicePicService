package lee.vioson.nicepicservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pic {
    @Id
    @GeneratedValue
    private Integer id;
    private String src;
    private String alt;
    private String type;
    private int typeId;
    private String listHref;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getListHref() {
        return listHref;
    }

    public void setListHref(String listHref) {
        this.listHref = listHref;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "Pic{" +
                "id=" + id +
                ", src='" + src + '\'' +
                ", alt='" + alt + '\'' +
                ", type='" + type + '\'' +
                ", typeId=" + typeId +
                ", listHref='" + listHref +
                '}';
    }
}
