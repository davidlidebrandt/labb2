package com.example.labb3.repositories;

import com.example.labb3.entities.Category;
import com.example.labb3.entities.Place;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends ListCrudRepository<Place, Long> {

    List<Place> findAllByCategory(Category category);

    List<Place> findAllByUserId(String user);

    List<Place> findAllByVisibility(String visibility);

    List<Place> findByIdAndVisibility(Long id, String visibility);

    List<Place> findAllByVisibilityOrUserId(String visibility, String user);

    @Query(value = """SELECT * FROM playgroundWHERE ST_Distance_Sphere(coordinate, :location) < :distance""", nativeQuery = true)
    List<Place> filterOnDistance(@Param("location") Point<G2D> location, @Param("distance") double distance);
}


}
