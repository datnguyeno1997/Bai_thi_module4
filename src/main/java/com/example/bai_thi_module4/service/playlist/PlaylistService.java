package com.example.bai_thi_module4.service.playlist;

import com.example.bai_thi_module4.model.Playlist;
import com.example.bai_thi_module4.repository.PlaylistRepository;
import com.example.bai_thi_module4.service.response.SelectOptionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;


    public List<SelectOptionResponse> findAll(){
        return playlistRepository.findAll().stream()
                .map(playlist -> new SelectOptionResponse(playlist.getId().toString(), playlist.getName()))
                .collect(Collectors.toList());
    }

}
