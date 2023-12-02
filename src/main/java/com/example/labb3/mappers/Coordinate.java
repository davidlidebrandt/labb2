package com.example.labb3.mappers;

import com.example.labb3.PointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = PointSerializer.class)
public record Coordinate(int lat, int lon) {
}
