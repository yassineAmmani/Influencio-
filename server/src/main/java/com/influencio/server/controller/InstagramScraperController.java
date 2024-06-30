package com.influencio.server.controller;

import com.influencio.server.dto.ApifyRequest;
import com.influencio.server.service.ApifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/instagram")
public class InstagramScraperController {

    private final ApifyService apifyService;

    @Autowired
    public InstagramScraperController(ApifyService apifyService) {
        this.apifyService = apifyService;
    }

    @PostMapping("/scrape")
    public ResponseEntity<String> scrapeInstagram(@RequestBody ApifyRequest request) {
        apifyService.scrapeAndSaveInfluencerData(request);
        return ResponseEntity.ok("Scraping process initiated");
    }

}