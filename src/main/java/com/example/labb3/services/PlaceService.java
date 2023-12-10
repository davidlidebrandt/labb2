package com.example.labb3.services;
import com.example.labb3.entities.Place;
import com.example.labb3.exceptions.InvalidDataException;
import com.example.labb3.exceptions.UnAuthorizedException;
import com.example.labb3.mappers.CategoryMapper;
import com.example.labb3.mappers.PlaceGetMapper;
import com.example.labb3.mappers.PlacePostMapper;
import com.example.labb3.mappers.PlacePutMapper;
import com.example.labb3.repositories.CategoryRepository;
import com.example.labb3.repositories.PlaceRepository;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;
import org.geolatte.geom.codec.Wkt;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;
    private CategoryRepository categoryRepository;

    public PlaceService(PlaceRepository placeRepository, CategoryRepository categoryRepository) {
        this.placeRepository = placeRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<PlaceGetMapper> getAllPlaces() {
        if(getCurrentUser() instanceof AnonymousAuthenticationToken) {
            return placeRepository.findAllByVisibility("public").stream().map(
                    PlaceService::mapPlaceToPlaceGetMapper
            ).toList();
        }
        return placeRepository.findAllByVisibilityOrUserId("public", getCurrentUser().getName()).stream().map(
                PlaceService::mapPlaceToPlaceGetMapper
            ).toList();
    }

    public List<PlaceGetMapper> getAllPlacesByUser() {
        return placeRepository.findAllByUserId(getCurrentUser().getName()).stream().map(
                PlaceService::mapPlaceToPlaceGetMapper
                ).toList();
    }

    public List<PlaceGetMapper> getAllPlacesByRadius(Point<G2D> point, double distance ) {
        if(getCurrentUser() instanceof AnonymousAuthenticationToken) {
            return placeRepository.findByDistance(point, distance).stream().filter(p -> Objects.equals(p.getVisibility(), "public")).map(
                    PlaceService::mapPlaceToPlaceGetMapper
            ).toList();
        }
        return placeRepository.findByDistance(point, distance).stream().filter(p -> Objects.equals(p.getVisibility(), "public") || Objects.equals(p.getUserId(), getCurrentUser().getName())).map(
                PlaceService::mapPlaceToPlaceGetMapper
        ).toList();
    }

    public List<PlaceGetMapper> getPlace(Long id) {
        if(getCurrentUser() instanceof AnonymousAuthenticationToken) {
            return placeRepository.findByIdAndVisibility(id, "public").stream().map(
                    PlaceService::mapPlaceToPlaceGetMapper
            ).toList();
        }
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
        var coordinate = placeMapper.coordinate();
        if(coordinate.lat() < -90.0 || coordinate.lat() > 90.0 || coordinate.lon() < -180.0 || coordinate.lon() > 180.0) {
            throw new InvalidDataException();
        }

        String userName = getCurrentUser().getName();
        var place = new Place();
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
        place.setLastModified(LocalDateTime.now());
        place.setName(placeMapper.name());
        place.setUserId(userName);
        place.setVisibility(placeMapper.visibility());
        place.setDescription(placeMapper.description());
        var category = categoryRepository.findCategoryByName(placeMapper.category());
        place.setCategory(category);
        String text = "POINT(" + placeMapper.coordinate().lon() + " " + placeMapper.coordinate().lat() + ")";
        Point<G2D> newPoint = (Point<G2D>) Wkt.fromWkt(text, WGS84);
        place.setCoordinate(newPoint);
        placeRepository.save(place);
    }

    public void updatePlace(PlacePutMapper newPlaceData) {
        var coordinate = newPlaceData.coordinate();
        if(coordinate.lat() < -90.0 || coordinate.lat() > 90.0 || coordinate.lon() < -180.0 || coordinate.lon() > 180.0) {
            throw new InvalidDataException();
        }

        var placeOrNull = placeRepository.findById(newPlaceData.id());
        var place = placeOrNull.get();
        if(!Objects.equals(place.getUserId(), getCurrentUser().getName())) {
            throw new UnAuthorizedException();
        }
        place.setLastModified(LocalDateTime.now());
        place.setName(newPlaceData.name());
        place.setVisibility(newPlaceData.visibility());
        place.setDescription(newPlaceData.description());
        var category = categoryRepository.findCategoryByName(newPlaceData.category());
        place.setCategory(category);
        String text = "POINT(" + newPlaceData.coordinate().lon() + " " + newPlaceData.coordinate().lat() + ")";
        Point<G2D> newPoint = (Point<G2D>) Wkt.fromWkt(text, WGS84);
        place.setCoordinate(newPoint);
        placeRepository.save(place);
    }

    public void deletePlace(Long id) {
        var placeOrNull = placeRepository.findById(id);
        var place = placeOrNull.get();
        if(!Objects.equals(place.getUserId(), getCurrentUser().getName())) {
            throw new UnAuthorizedException();
        }
        placeRepository.deleteById(id);
    }

    public static PlaceGetMapper mapPlaceToPlaceGetMapper(Place place) {
        return new PlaceGetMapper(place.getId(), place.getName(), place.getUserId(), new CategoryMapper(place.getCategory().getId(), place.getCategory().getName(),place.getCategory().getSymbol(), place.getCategory().getDescription()), place.getVisibility(), place.getLastModified(), place.getDescription(), place.getCoordinate(), place.getCreated());
    }

    public Authentication getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}


