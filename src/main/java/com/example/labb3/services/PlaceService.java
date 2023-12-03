package com.example.labb3.services;
import com.example.labb3.entities.Category;
import com.example.labb3.entities.Place;
import com.example.labb3.mappers.CategoryMapper;
import com.example.labb3.mappers.PlaceGetMapper;
import com.example.labb3.mappers.PlacePostMapper;
import com.example.labb3.mappers.PlacePutMapper;
import com.example.labb3.repositories.CategoryRepository;
import com.example.labb3.repositories.PlaceRepository;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;
import org.geolatte.geom.codec.Wkt;

import javax.management.BadAttributeValueExpException;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;
    private CategoryRepository categoryRepository;

    public PlaceService(PlaceRepository placeRepository, CategoryRepository categoryRepository) {
        this.placeRepository = placeRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<PlaceGetMapper> getAllPlaces() {
        return placeRepository.findAll().stream().map((place -> {
            return new PlaceGetMapper(place.getId(), place.getName(), place.getUserId(), new CategoryMapper(place.getCategory().getId(), place.getCategory().getName(),place.getCategory().getSymbol(), place.getCategory().getDescription()), place.getVisibility(), place.getLastModified(), place.getDescription(), place.getCoordinate(), place.getCreated());
        })).toList();
    }

    public List<PlaceGetMapper> getPlace(Long id) {
        return placeRepository.findById(id).stream().map(
            PlaceService::mapPlaceToPlaceGetMapper
        ).toList();
    }

    public List<PlaceGetMapper> getPlacesByCategory(String categoryName) {
        var category = categoryRepository.findCategoryByName(categoryName);
        return placeRepository.findAllByCategory(category).stream().map(
                PlaceService::mapPlaceToPlaceGetMapper
        ).toList();
    }

    public void addPlace(PlacePostMapper placeMapper) {
        var place = new Place();
        place.setLastModified(LocalDateTime.now());
        place.setName(placeMapper.name());
        place.setUserId("dsdsd");
        place.setVisibility(placeMapper.visibility());
        place.setDescription(placeMapper.description());
        var category = categoryRepository.findCategoryByName(placeMapper.category());
        place.setCategory(category);
        String text = "POINT(" + placeMapper.coordinate().lat() + " " + placeMapper.coordinate().lon() + ")";
        Point<G2D> newPoint = (Point<G2D>) Wkt.fromWkt(text, WGS84);
        place.setCoordinate(newPoint);
        placeRepository.save(place);
    }

    public void updatePlace(PlacePutMapper newPlaceData) {
        var placeOrNull = placeRepository.findById(newPlaceData.id());
        var place = placeOrNull.get();
        place.setLastModified(LocalDateTime.now());
        place.setName(newPlaceData.name());
        place.setVisibility(newPlaceData.visibility());
        place.setDescription(newPlaceData.description());
        var category = categoryRepository.findCategoryByName(newPlaceData.category());
        place.setCategory(category);
        String text = "POINT(" + newPlaceData.coordinate().lat() + " " + newPlaceData.coordinate().lon() + ")";
        Point<G2D> newPoint = (Point<G2D>) Wkt.fromWkt(text, WGS84);
        place.setCoordinate(newPoint);
        placeRepository.save(place);
    }

    public void deletePlace(Long id) {
        placeRepository.deleteById(id);
    }

    public static PlaceGetMapper mapPlaceToPlaceGetMapper(Place place) {
        return new PlaceGetMapper(place.getId(), place.getName(), place.getUserId(), new CategoryMapper(place.getCategory().getId(), place.getCategory().getName(),place.getCategory().getSymbol(), place.getCategory().getDescription()), place.getVisibility(), place.getLastModified(), place.getDescription(), place.getCoordinate(), place.getCreated());
    }
}


