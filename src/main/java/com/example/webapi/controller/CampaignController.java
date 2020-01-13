package com.example.webapi.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webapi.model.Campaign;
import com.example.webapi.payload.CampaignRequest;
import com.example.webapi.repository.CampaignRepository;

@RestController
@RequestMapping("/api/campaign")
public class CampaignController {

	private static final Logger logger = LoggerFactory.getLogger(CampaignController.class);

	@Autowired
	CampaignRepository campaignRepository;

	@CrossOrigin
	@GetMapping("/all")
	public List<CampaignRequest> getAll() {
		List<CampaignRequest> res = new ArrayList<>();
		List<Campaign> resList = campaignRepository.findAll();

		if (!resList.isEmpty()) {
			res = getResponseMapping(resList);
		}
		return res;
	}

	@CrossOrigin
	@PostMapping("/create")
	public CampaignRequest create(@Valid @RequestBody CampaignRequest campaign) {
		if (campaign != null) {
			try {
				Campaign camp = new Campaign();
				camp.setTitle(campaign.getTitle());
				camp.setEmail(campaign.getEmail());
				camp.setStatus(campaign.getStatus());
				camp.setContent(campaign.getContent());
				camp.setImgfile(campaign.getFile().getBytes(StandardCharsets.UTF_8));
				camp = campaignRepository.save(camp);
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
		return campaign;
	}

	@CrossOrigin
	@PostMapping("/search")
	public List<CampaignRequest> search(@Valid @RequestBody CampaignRequest campaign) {
		List<CampaignRequest> res = new ArrayList<>();
		List<Campaign> resList = new ArrayList<>();

		// This search can be enhance using custom DAO class. FX: set CampaignRequest and base on fields can search
		
		if (campaign.getTitle() != null && campaign.getEmail() != null && campaign.getStatus() != null) {
			resList = campaignRepository.findByTitleAndEmailAndStatus(campaign.getTitle(), campaign.getEmail(),
					campaign.getStatus());
			logger.info(res.toString());
		} else if (campaign.getTitle() != null && campaign.getEmail() != null) {
			resList = campaignRepository.findByTitleAndEmail(campaign.getTitle(), campaign.getEmail());
		} else if (campaign.getTitle() != null && campaign.getStatus() != null) {
			resList = campaignRepository.findByTitleAndStatus(campaign.getTitle(), campaign.getStatus());
		} else if (campaign.getEmail() != null && campaign.getStatus() != null) {
			resList = campaignRepository.findByEmailAndStatus(campaign.getEmail(), campaign.getStatus());
		} else if (campaign.getTitle() != null) {
			resList = campaignRepository.findByTitle(campaign.getTitle());
		} else if (campaign.getEmail() != null) {
			resList = campaignRepository.findByEmail(campaign.getEmail());
		} else if (campaign.getStatus() != null) {
			resList = campaignRepository.findByStatus(campaign.getStatus());
		}

		if (!resList.isEmpty()) {
			res = getResponseMapping(resList);
		}

		return res;
	}

	private List<CampaignRequest> getResponseMapping(List<Campaign> cl) {
		List<CampaignRequest> res = new ArrayList<>();
		for (Campaign c : cl) {
			CampaignRequest req = new CampaignRequest();
			req.setTitle(c.getTitle());
			req.setEmail(c.getEmail());
			req.setStatus(c.getStatus());
			req.setContent(c.getContent());
			req.setFile(new String(c.getImgfile(), StandardCharsets.UTF_8));
			res.add(req);
		}

		return res;
	}
}
