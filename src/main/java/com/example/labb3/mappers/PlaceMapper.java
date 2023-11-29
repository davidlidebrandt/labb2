package com.example.labb3.mappers;

import com.example.labb3.entities.Category;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import java.time.LocalDateTime;
import java.util.UUID;

public record PlaceMapper(UUID id, String name, String userId, Category category, String visibility,
                          LocalDateTime lastModified, String description, Point<G2D> coordinate, LocalDateTime created) {
}
