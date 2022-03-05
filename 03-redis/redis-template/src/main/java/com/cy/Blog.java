package com.cy;

import java.io.Serializable;

public class Blog implements Serializable {
    private static final long serialVersionUID = -6759784470567108013L;
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
