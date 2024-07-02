package com.influencio.server.service;

import com.influencio.server.dto.ApifyRequest;
import com.influencio.server.dto.ApifyResponse;
import com.influencio.server.dto.InfluencerListRequest;
import com.influencio.server.dto.InfluencerListResponse;
import com.influencio.server.model.Influencer;
import com.influencio.server.model.InfluencerListItem;
import com.influencio.server.repository.InfluencerRepository;
import com.influencio.server.repository.InfluencerListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ApifyService {

    @Value("${apify.api.token}")
    private String apifyToken;

    @Value("${apify.actor.id}")
    private String apifyActorId;
    @Value("${apify.actor.id2}")
    private String apifyActorId2;

    @Value("${image.storage.path}")
    private String imageStoragePath;

    private final RestTemplate restTemplate;
    private final InfluencerRepository influencerRepository;
    private final InfluencerListItemRepository influencerListItemRepository;

    @Autowired
    public ApifyService(RestTemplate restTemplate, InfluencerRepository influencerRepository, InfluencerListItemRepository influencerListItemRepository) {
        this.restTemplate = restTemplate;
        this.influencerRepository = influencerRepository;
        this.influencerListItemRepository = influencerListItemRepository;
    }

    public void scrapeAndSaveInfluencerData(ApifyRequest request) {
        String url = String.format("https://api.apify.com/v2/acts/%s/run-sync-get-dataset-items?token=%s", apifyActorId, apifyToken);

        ApifyResponse[] response = restTemplate.postForObject(url, request, ApifyResponse[].class);

        if (response != null) {
            List<ApifyResponse> responseList = Arrays.asList(response);
            for (ApifyResponse item : responseList) {
                Influencer influencer = new Influencer();
                influencer.setUsername(item.getOwnerUsername());
                influencer.setFullName(item.getOwnerFullName());
                influencer.setLastPostUrl(item.getUrl());
                influencer.setLastPostCaption(item.getCaption());
                influencer.setLastPostLikes(item.getLikesCount());
                influencer.setLastPostComments(item.getCommentsCount());
                influencer.setLastPostTimestamp(String.valueOf(java.time.Instant.parse(item.getTimestamp())));

                // Download and save the image if imageUrl is not null
                if (item.getImageUrl() != null && !item.getImageUrl().isEmpty()) {
                    try {
                        String imagePath = downloadAndSaveImage(item.getImageUrl(), item.getOwnerUsername());
                        influencer.setPathLastPostImage(imagePath);
                    } catch (IOException e) {
                        System.err.println("Failed to download image: " + e.getMessage());
                    }
                }

                influencerRepository.save(influencer);
            }
        }
    }
    public List<InfluencerListResponse> scrapeAndSaveInfluencerList(InfluencerListRequest request) {
        String url = String.format("https://api.apify.com/v2/acts/%s/run-sync-get-dataset-items?token=%s", apifyActorId2, apifyToken);

        InfluencerListResponse[] response = restTemplate.postForObject(url, request, InfluencerListResponse[].class);

        if (response != null) {
            List<InfluencerListResponse> responseList = Arrays.asList(response);
            for (InfluencerListResponse item : responseList) {
                InfluencerListItem influencerListItem = new InfluencerListItem();
                influencerListItem.setSearchTerm(item.getSearchTerm());
                influencerListItem.setInputUrl(item.getInputUrl());
                influencerListItem.setUsername(item.getUsername());
                influencerListItem.setFollowersCount(item.getFollowersCount());

                influencerListItemRepository.save(influencerListItem);
            }

            return responseList;
        }

        return List.of();
    }

    private String downloadAndSaveImage(String imageUrl, String username) throws IOException {
        URL url = new URL(imageUrl);
        String fileName = username + "_" + UUID.randomUUID().toString() + ".jpg";
        Path destinationPath = Paths.get(imageStoragePath, fileName);

        try (InputStream in = url.openStream()) {
            Files.copy(in, destinationPath);
        }

        return destinationPath.toString();
    }
}