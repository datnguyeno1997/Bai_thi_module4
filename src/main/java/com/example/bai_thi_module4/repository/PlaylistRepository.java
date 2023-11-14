package com.example.bai_thi_module4.repository;


import com.example.bai_thi_module4.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
