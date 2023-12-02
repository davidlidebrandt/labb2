package com.example.labb3.mappers;
import com.example.labb3.PointSerializer;
import com.example.labb3.entities.Category;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import java.time.LocalDateTime;
import java.util.UUID;

public record PlaceGetMapper(UUID id, String name, String userId, Category category, String visibility,
                             LocalDateTime lastModified, String description, @JsonSerialize(using = PointSerializer.class)
                          Point<G2D> coordinate, LocalDateTime created) {
}
