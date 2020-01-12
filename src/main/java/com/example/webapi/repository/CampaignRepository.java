package com.example.webapi.repository;

import java.util.Optional;

import com.example.webapi.config.GenericRepository;
import com.example.webapi.model.Campaign;

public interface CampaignRepository extends GenericRepository<Campaign> {
	
	Optional<Campaign> findByTitleAndEmailAndStatus(String title, String email, String status);

}
