package com.influencio.server.repository;

import com.influencio.server.model.InfluencerListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfluencerListItemRepository extends JpaRepository<InfluencerListItem, Long> {
}