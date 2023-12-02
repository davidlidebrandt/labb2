package com.example.labb3.services;
import com.example.labb3.entities.Place;
import com.example.labb3.mappers.PlaceGetMapper;
import com.example.labb3.mappers.PlacePostMapper;
import com.example.labb3.repositories.PlaceRepository;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;
import org.geolatte.geom.codec.Wkt;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<PlaceGetMapper> getAllPlaces() {
        return placeRepository.findAll().stream().map((place -> {
            return new PlaceGetMapper(place.getId(), place.getName(), place.getUserId(), place.getCategory(), place.getVisibility(), place.getLastModified(), place.getDescription(), place.getCoordinate(), place.getCreated());
        })).toList();
    }

    public void addPlace(PlacePostMapper placeMapper) {
        var place = new Place();
        place.setLastModified(LocalDateTime.now());
        place.setName(placeMapper.name());
        place.setUserId("dsdsd");
        place.setVisibility(placeMapper.visibility());
        place.setDescription(placeMapper.description());
        place.setCategory(placeMapper.category());
        String text = "POINT (" + placeMapper.coordinate().lon() + placeMapper.coordinate().lat() + ")";
        Point<G2D> newPoint = (Point<G2D>) Wkt.fromWkt(text, WGS84);
        place.setCoordinate(newPoint);
        placeRepository.save(place);
    }
}
