package com.example.playWorld.play.controller;

import com.example.playWorld.play.dto.PlayDetailResultDTO;
import com.example.playWorld.play.dto.PlayListRequestDTO;
import com.example.playWorld.play.dto.PlayListResultDTO;
import com.example.playWorld.play.dto.PlayPlaceDetailDTO;
import com.example.playWorld.play.service.PlayService;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PlayController {

   private final PlayService playService;

    @PostMapping("/playlist")
    public ResponseEntity playList(PlayListRequestDTO requestDTO) throws JAXBException {

        PlayListResultDTO playList = playService.getPlayList(requestDTO);

        return new ResponseEntity(playList, HttpStatus.OK);
    }

    @GetMapping("/playDetail/{playId}")
    public ResponseEntity playDetail(@PathVariable("playId") String playId){

        PlayDetailResultDTO playDetail = playService.getPlayDetail(playId);

        return new ResponseEntity(playDetail, HttpStatus.OK);
    }

    @GetMapping("/playPlace/{placeId}")
    public ResponseEntity placeDetail(@PathVariable("placeId") String placeId){

        PlayPlaceDetailDTO placeDetail = playService.getPlaceDetail(placeId);

        return new ResponseEntity(placeDetail, HttpStatus.OK);
    }

}
