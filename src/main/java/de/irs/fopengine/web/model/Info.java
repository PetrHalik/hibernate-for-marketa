package de.irs.fopengine.web.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
public class Info {

    public Info() {
    }

    public Info(String text) {
        this.txt = text;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="txt")
    private String txt;

    @OneToOne (mappedBy="info")
    private Project project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", txt='" + txt + '\'' +
                ", project=" + project +
                '}';
    }
}
