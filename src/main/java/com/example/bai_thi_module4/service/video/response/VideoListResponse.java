package com.example.bai_thi_module4.service.video.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class VideoListResponse {
    private Long id;

    private String title;

    private String description;

    private String videoPlaylist;

}
