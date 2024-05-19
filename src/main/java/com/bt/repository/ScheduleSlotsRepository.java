package com.bt.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bt.entities.ScheduleSlots;

@Repository
public interface ScheduleSlotsRepository extends JpaRepository<ScheduleSlots, Long> {
	
	@Query(value = "SELECT sst.time FROM schedule_slots ss JOIN schedule_slots_time sst ON ss.id = sst.schedule_slots_id WHERE ss.vet_id = :vetId AND ss.date = :date", nativeQuery = true)
	List<String> findTimeSlotsForVetandDate(@Param("vetId") Long vetId, @Param("date") LocalDate date);

	@Query(value = "SELECT id FROM VetsDB.schedule_slots WHERE date =:date AND vet_id =:vetId", nativeQuery = true)
    Long findIdByDateAndVetId(@Param("date") String date, @Param("vetId") Long vetId);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE from VetsDB.schedule_slots_time WHERE schedule_slots_id =:slotId AND time =:time", nativeQuery = true)
	int deleteBySlotIdAndTime(@Param("slotId") Long slotId, @Param("time") String time);
}
