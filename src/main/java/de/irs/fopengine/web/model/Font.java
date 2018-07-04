package de.irs.fopengine.web.model;

import javax.persistence.*;

@Entity
public class Font {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="project_id", nullable=false, unique=false, length=11)
    private Long projectId;
    @Column(name="font_name")
    private String fontName;

    public Font() {
    }

    public Font(String fontName) {
        this.fontName = fontName;
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

//    public Project getProject() {
//        return project;
//    }
//
//    public void setProject(Project project) {
//        this.project = project;
//    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
