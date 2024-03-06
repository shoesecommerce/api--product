package com.shoesclick.service.notification.service;


import com.shoesclick.service.notification.entity.Log;
import com.shoesclick.service.notification.repository.LogRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {

	private final LogRepository logRepository;

	public LogService(LogRepository logRepository) {
		this.logRepository = logRepository;
	}


	public void save(Log log) {
		logRepository.save(log);
	}
	

}