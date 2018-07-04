package de.irs.fopengine.web.model;

import javax.persistence.*;

@Entity
public class Font {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "font_name")
    private String fontName;

    public Font() {
    }

    public Font(String fontName) {
        this.fontName = fontName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Font{" +
                "id=" + id +
                ", fontName='" + fontName + '\'' +
                '}';
    }
}
