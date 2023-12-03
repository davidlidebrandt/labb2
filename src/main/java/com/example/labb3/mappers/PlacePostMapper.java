package com.example.labb3.mappers;
import java.time.LocalDateTime;


public record PlacePostMapper(Long id, String name, String userId, String category, String visibility,
                              LocalDateTime lastModified, String description,
                              Coordinate coordinate, LocalDateTime created) {
}
