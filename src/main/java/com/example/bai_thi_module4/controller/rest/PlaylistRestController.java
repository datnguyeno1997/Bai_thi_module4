package com.example.bai_thi_module4.controller.rest;

import com.example.bai_thi_module4.repository.PlaylistRepository;
import com.example.bai_thi_module4.service.response.SelectOptionResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/playlists")
@AllArgsConstructor
public class PlaylistRestController {
    private final PlaylistRepository playlistRepository;

    @GetMapping
    public List<SelectOptionResponse> getSelectOption() {
        return playlistRepository.findAll().stream().map(playlist -> new SelectOptionResponse(playlist.getId().toString(), playlist.getName())).collect(Collectors.toList());
    }
}
