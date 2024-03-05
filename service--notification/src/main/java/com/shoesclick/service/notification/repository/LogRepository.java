package com.shoesclick.service.notification.repository;


import com.shoesclick.service.notification.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {



}
