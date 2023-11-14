package com.example.bai_thi_module4.repository;

import com.example.bai_thi_module4.model.VideoPlaylist;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoPlaylistRepository extends JpaRepository<VideoPlaylist, Long> {
    @Transactional
    void deleteVideoPlaylistsByVideoId(Long id);
}
