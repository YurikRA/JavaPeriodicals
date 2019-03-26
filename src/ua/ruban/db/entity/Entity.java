package ua.ruban.db.entity;

import java.io.Serializable;

public class Entity implements Serializable {

    private static final long serialVersionUID = -6957915876479719285L;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
