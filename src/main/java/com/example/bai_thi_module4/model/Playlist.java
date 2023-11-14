package com.example.bai_thi_module4.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "playlists")
@Data
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "playlist")
    private List<VideoPlaylist> videoPlaylists;

    public Playlist(Long id) {
        this.id = id;
    }

    public Playlist(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
