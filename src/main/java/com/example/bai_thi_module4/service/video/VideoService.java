package com.example.bai_thi_module4.service.video;

import com.example.bai_thi_module4.model.Playlist;
import com.example.bai_thi_module4.model.Video;
import com.example.bai_thi_module4.model.VideoPlaylist;
import com.example.bai_thi_module4.repository.VideoPlaylistRepository;
import com.example.bai_thi_module4.repository.VideoRepository;
import com.example.bai_thi_module4.service.video.request.VideoSaveRequest;
import com.example.bai_thi_module4.service.video.response.VideoDetailResponse;
import com.example.bai_thi_module4.service.video.response.VideoListResponse;
import com.example.bai_thi_module4.util.AppUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class VideoService {

    private final VideoRepository videoRepository;

    private final VideoPlaylistRepository videoPlaylistRepository;

    public void create(VideoSaveRequest request){
        var video = AppUtil.mapper.map(request, Video.class);
        video = videoRepository.save(video);
        Video finalVideo = video;
        videoPlaylistRepository.saveAll(request
                .getIdPlaylists()
                .stream()
                .map(id -> new VideoPlaylist(finalVideo, new Playlist(Long.valueOf(id))))
                .collect(Collectors.toList()));
    }

    public VideoDetailResponse findById(Long id){
        var video = videoRepository.findById(id).orElse(new Video());

        var result = AppUtil.mapper.map(video, VideoDetailResponse.class);
        result.setPlaylistIds(video
                .getVideoPlaylists()
                .stream().map(videoPlaylist -> videoPlaylist.getPlaylist().getId())
                .collect(Collectors.toList()));
        return result;
    }

    public Page<VideoListResponse> getVideos(Pageable pageable, String search){
        search = "%" + search + "%";
        return videoRepository.searchEverything(search ,pageable).map(e -> {
            var result = AppUtil.mapper.map(e, VideoListResponse.class);
            result.setVideoPlaylist(e.getVideoPlaylists()
                    .stream().map(c -> c.getPlaylist().getName())
                    .collect(Collectors.joining(", ")));
            return result;
        });
    }

    public void update(VideoSaveRequest request, Long id){
        var videoDb = videoRepository.findById(id).orElse(new Video());
        AppUtil.mapper.map(request,videoDb);
        videoPlaylistRepository.deleteAll(videoDb.getVideoPlaylists());


        var videoPlaylists = new ArrayList<VideoPlaylist>();
        for (String idPlaylist : request.getIdPlaylists()) {
            Playlist playlist = new Playlist(Long.valueOf(idPlaylist));
            videoPlaylists.add(new VideoPlaylist(videoDb, playlist));
        }
        videoPlaylistRepository.saveAll(videoPlaylists);
        videoRepository.save(videoDb);
    }
    public Boolean delete(Long id) {
        videoPlaylistRepository.deleteVideoPlaylistsByVideoId(id);
        videoRepository.deleteById(id);
        return true;
    }
}
