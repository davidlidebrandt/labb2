package com.example.labb3.entities;
import com.example.labb3.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import javax.management.BadAttributeValueExpException;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @Column(name = "user_id")
    private String userId;

    private String visibility;

    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @Column(name = "description_")
    private String description;

    @JsonSerialize(using = PointSerializer.class)
    private Point<G2D> coordinate;

    private LocalDateTime created;

    public Place() {
        this.created = LocalDateTime.now();
    }


    public LocalDateTime getCreated() {
        return created;
    }

    public Point<G2D> getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point<G2D> coordinate) {
        this.coordinate = coordinate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        boolean correctValue = visibility.equalsIgnoreCase("public") || visibility.equalsIgnoreCase("private");
        if(correctValue) {
            this.visibility = visibility;;
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
