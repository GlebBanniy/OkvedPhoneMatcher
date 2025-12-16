package com.example.okvedphonematcher.service;

import com.example.okvedphonematcher.domain.OkvedData;

/**
 * Загрузка данных ОКВЭД
 */
public interface OkvedDownloader {

    /**
     * Загрузить данные ОКВЭД
     *
     * @return Загруженные данные
     */
    OkvedData download();
}
