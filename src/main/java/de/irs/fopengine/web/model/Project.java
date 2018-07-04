package de.irs.fopengine.web.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=true, unique=true, length=11)
    private Long id;
    private String name;
    private String description;
    @Column(name="url", nullable=true)
    private String gitUrl;

    private String status;

    @OneToOne
    @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
    private Maven maven;

    @OneToOne
    @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
    private Info info;


    @Transient
    private List<Font> fonts;

    public Project() {
        fonts = new ArrayList<>();
    }

    public Project(String name, String gitUrl) {
        this();
        this.name = name;
        this.gitUrl = gitUrl;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Font> getFonts() {
        return fonts;
    }

    public void setFonts(List<Font> fonts) {
        this.fonts = fonts;
    }

    public Maven getMaven() {
        return maven;
    }

    public void setMaven(Maven maven) {
        this.maven = maven;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", gitUrl='" + gitUrl + '\'' +
                ", status='" + status + '\'' +
                ", maven=" + maven +
                ", info=" + info +
                ", fonts=" + fonts +
                '}';
    }
}
