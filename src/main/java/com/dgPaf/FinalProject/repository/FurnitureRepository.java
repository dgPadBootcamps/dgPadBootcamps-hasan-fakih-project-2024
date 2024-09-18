package com.dgPaf.FinalProject.repository;

import com.dgPaf.FinalProject.model.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Long> {

    List<Furniture> findByOwnerId(Long ownerId);

    @Query("SELECT f FROM Furniture f WHERE " +
            "(:name IS NULL OR f.title LIKE %:name%) AND " +
            "(:category IS NULL OR f.category = :category) AND " +
            "(:city IS NULL OR f.address.city = :city)")
    List<Furniture> searchFurniture(@Param("name") String name,
                                    @Param("category") String category,
                                    @Param("city") String city);

    @Query("SELECT COUNT(f) FROM Furniture f WHERE f.owner.id = :userId")
    long countDonatedItems(@Param("userId") Long userId);

    @Query("SELECT COUNT(f) FROM Furniture f WHERE f.owner.id = :userId")
    long countUploadedItems(@Param("userId") Long userId);

    @Query("SELECT COUNT(DISTINCT r.requester.id) FROM Reservation r WHERE r.furniture.owner.id = :userId")
    long countDistinctPeopleHelped(@Param("userId") Long userId);


}
