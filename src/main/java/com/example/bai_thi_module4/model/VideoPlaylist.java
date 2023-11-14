package com.example.bai_thi_module4.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "video_playlists")
@Entity
@Data
@NoArgsConstructor
public class VideoPlaylist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Video video;

    @ManyToOne
    private Playlist playlist;

    public VideoPlaylist(Video video, Playlist playlist) {
        this.video = video;
        this.playlist = playlist;
    }

}
