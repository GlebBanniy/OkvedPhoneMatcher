package com.example.okvedphonematcher.service.impl;

import com.example.okvedphonematcher.config.properties.OkvedPhoneMatchProperties;
import com.example.okvedphonematcher.domain.OkvedData;
import com.example.okvedphonematcher.domain.OkvedNode;
import com.example.okvedphonematcher.exception.OkvedDataDownloadException;
import com.example.okvedphonematcher.service.OkvedDownloader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class OkvedDownloadService implements OkvedDownloader {

    private final OkvedPhoneMatchProperties properties;
    private final ObjectMapper objectMapper;

    @Override
    public OkvedData download() {
        String url = properties.getOkvedSource().getUrl() + properties.getOkvedSource().getFileName();

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new OkvedDataDownloadException(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new OkvedDataDownloadException(e);
        }

        if (response.statusCode() == 200) {
            try {
                return new OkvedData(
                        objectMapper.readValue(
                                response.body(),
                                objectMapper.getTypeFactory().constructCollectionType(List.class, OkvedNode.class)
                        )
                );
            } catch (JsonProcessingException e) {
                log.error("Ошибка парсинга документа");
                throw new OkvedDataDownloadException(e);
            }
        } else {
            log.error("Ошибка: HTTP " + response.statusCode());
            throw new OkvedDataDownloadException("Ошибка: HTTP " + response.statusCode());
        }
    }
}
