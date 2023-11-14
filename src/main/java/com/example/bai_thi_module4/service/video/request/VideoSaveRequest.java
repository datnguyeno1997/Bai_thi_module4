package com.example.bai_thi_module4.service.video.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class VideoSaveRequest {
    private String title;

    private String description;

    private List<String> idPlaylists;

    private String url;
}
