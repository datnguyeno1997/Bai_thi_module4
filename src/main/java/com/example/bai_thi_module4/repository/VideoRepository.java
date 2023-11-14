package com.example.bai_thi_module4.repository;


import com.example.bai_thi_module4.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VideoRepository extends JpaRepository<Video, Long> {
    @Query(value = "SELECT v FROM Video v " +
            "WHERE " +
            "v.title LIKE :search OR " +
            "v.description LIKE :search OR " +
            "EXISTS (SELECT 1 FROM VideoPlaylist vp WHERE vp.video = v AND vp.playlist.name LIKE :search)")


    Page<Video> searchEverything(String search, Pageable pageable);
}
