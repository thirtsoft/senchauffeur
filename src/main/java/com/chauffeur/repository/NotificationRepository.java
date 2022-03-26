package com.chauffeur.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chauffeur.models.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	List<Notification> findTop3ByOrderByCreatedDateDesc();

    @Query("select count(c) from Notification c where month(c.createdDate) = month(current_date)")
    BigDecimal countNumberOfNotification();
    
    List<Notification> findByOrderByIdDesc();

    @Query("select count(c) from Notification c where c.chauffeur.reference =:chauff")
    BigDecimal countNumberOfNotificationByChauffeurId(@Param("chauff") String chauffRef);

    @Query("select n from Notification n where n.chauffeur.id =:num")
    List<Notification> findTop4NotificationOrderByCreatedDateDesc(@Param("num") Long chauffRef);

}
