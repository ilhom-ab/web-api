package com.example.webapi.repository;

import java.util.List;

import com.example.webapi.config.GenericRepository;
import com.example.webapi.model.Campaign;

public interface CampaignRepository extends GenericRepository<Campaign> {
	
	List<Campaign> findByTitleAndEmailAndStatus(String title, String email, String status);
	
	List<Campaign> findByTitleAndEmail(String title, String email);
	
	List<Campaign> findByTitleAndStatus(String title, String status);
	
	List<Campaign> findByEmailAndStatus(String email, String status);
	
	List<Campaign> findByTitle(String title);
	
	List<Campaign> findByEmail(String email);
	
	List<Campaign> findByStatus(String status);

}
