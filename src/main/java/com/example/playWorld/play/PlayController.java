package com.example.playWorld.play;

import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PlayController {

   private final PlayService playService;

    @PostMapping("/play")
    public ResponseEntity playList(PlayListRequestDTO requestDTO) throws JAXBException {

        PlayListResultDTO playList = playService.getPlayList(requestDTO);

        return new ResponseEntity(playList, HttpStatus.OK);

    }
}
