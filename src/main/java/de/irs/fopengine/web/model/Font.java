package de.irs.fopengine.web.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Font {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = true)
    private Project project;

    @Column(name = "font_name")
    private String fontName;

    @ManyToMany(targetEntity = Triplet.class, fetch = FetchType.EAGER)
    @JoinTable(name = "font_triplet",
            joinColumns = { @JoinColumn(name = "id") },
            inverseJoinColumns = { @JoinColumn(name = "triplet_id")})
    private Set<Triplet> triplets;

    public Font() {
        triplets = new HashSet<>();
    }

    public Font(String fontName) {
        this();
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

    public Set<Triplet> getTriplets() {
        return triplets;
    }

    public void setTriplets(Set<Triplet> triplets) {
        this.triplets = triplets;
    }

    @Override
    public String toString() {
        return "Font{" +
                "id=" + id +
                ", project=" + project +
                ", fontName='" + fontName + '\'' +
                ", triplets=" + triplets +
                '}';
    }
}
