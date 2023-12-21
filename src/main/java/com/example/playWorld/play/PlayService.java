package com.example.playWorld.play;

import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class PlayService {

    @Value("${kopis.key}")
    private String kopisKey;

    private WebClient kopisWebClient = WebClient.builder()
            .baseUrl("http://www.kopis.or.kr/openApi/restful")
            .exchangeStrategies(ExchangeStrategies.builder() // xml 데이터 바로 파싱
                    .codecs(configure -> configure.defaultCodecs().jaxb2Decoder(new Jaxb2XmlDecoder()))
                    .build())
            .build();

    public PlayListResultDTO getPlayList(PlayListRequestDTO requestDTO) throws JAXBException {

        /*
        // 파라미터를 MultiValueMap으로 변환하는 방법
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> requestParams = objectMapper.convertValue(requestDTO, new TypeReference<Map<String, String>>() {});
        params.setAll(requestParams);
        log.info(requestParams.toString());
        log.info(params);
        */

        PlayListResultDTO playListDTO = kopisWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/pblprfr")
                        .queryParam("service", kopisKey)
                        .queryParam("rows", requestDTO.getRows())
                        .queryParam("cpage", requestDTO.getCpage()) // TODO : 추후 @PathVariable 활용
                        .queryParam("shcate", requestDTO.getShcate())
                        .queryParam("prfstate", requestDTO.getPrfstate())
                        .queryParam("stdate", requestDTO.getStdate())
                        .queryParam("eddate", requestDTO.getEddate())
//                        .queryParams((params)) // TODO : value가 List형태로 들어가서 작동 안 함. 좀 더 알아봐야할 듯
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    throw new RuntimeException("4XX Error, 잘못된 요청입니다");
                })
                .bodyToMono(PlayListResultDTO.class)
                .block();

        /*
        // webclient 선언 시 설정으로 xml 바로 파싱되게끔 변경.
        // xml 데이터를 Object로 변환
        JAXBContext jaxbContext = JAXBContext.newInstance(PlayListResultDTO.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        PlayListResultDTO playListDTO = (PlayListResultDTO) unmarshaller.unmarshal(new StringReader(result));
         */

        return playListDTO;
    }
}
