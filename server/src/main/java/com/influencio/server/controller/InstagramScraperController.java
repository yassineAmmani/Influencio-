package com.influencio.server.controller;

import com.influencio.server.dto.ApifyRequest;
import com.influencio.server.dto.InfluencerListRequest;
import com.influencio.server.dto.InfluencerListResponse;
import com.influencio.server.service.ApifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instagram")
@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping("/scrape-list")
    public ResponseEntity<List<InfluencerListResponse>> scrapeInfluencerList(@RequestBody InfluencerListRequest request) {
        List<InfluencerListResponse> response = apifyService.scrapeAndSaveInfluencerList(request);
        return ResponseEntity.ok(response);
    }
}