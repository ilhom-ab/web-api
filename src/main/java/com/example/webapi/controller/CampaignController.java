package com.example.webapi.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

	@Autowired
	CampaignRepository campaignRepository;

	@CrossOrigin
	@GetMapping("/all")
	public List<Campaign> getAll() {
		List<CampaignRequest> res = new ArrayList<>();
		List<Campaign> resList = campaignRepository.findAll();

		for (Campaign c : resList) {
			CampaignRequest req = new CampaignRequest();
			req.setTitle(c.getTitle());
			req.setEmail(c.getEmail());
			req.setStatus(c.getStatus());
			req.setContent(c.getContent());
			req.setFile(new String(c.getImgfile(), StandardCharsets.UTF_8));
		}

		return resList;
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
				System.out.println(e.getMessage());
			}
		}
		return campaign;
	}

	@CrossOrigin
	@PostMapping("/search")
	public Optional<Campaign> search(@Valid @RequestBody CampaignRequest campaign) {
		Optional<Campaign> res = null;

		if (campaign != null) {
			res = campaignRepository.findByTitleAndEmailAndStatus(campaign.getTitle(), campaign.getEmail(),
					campaign.getStatus());
		}
		return res;
	}
}
