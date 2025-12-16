package com.example.okvedphonematcher.controller.impl;

import com.example.okvedphonematcher.controller.OkvedToPhoneMatchingController;
import com.example.okvedphonematcher.controller.dto.OkvedToPhoneMatchResult;
import com.example.okvedphonematcher.domain.PhoneNumber;
import com.example.okvedphonematcher.service.OkvedDownloader;
import com.example.okvedphonematcher.service.OkvedMatcher;
import com.example.okvedphonematcher.service.PhoneNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.okvedphonematcher.controller.Api.PHONE;

@RestController
@RequiredArgsConstructor
@RequestMapping
class OkvedToPhoneMatchingControllerImpl implements OkvedToPhoneMatchingController {

    private final PhoneNormalizer phoneNormalizer;
    private final OkvedDownloader okvedDownloader;
    private final OkvedMatcher okvedMatcher;

    @Override
    @PostMapping(PHONE)
    public OkvedToPhoneMatchResult match(@RequestBody String phone) {
        PhoneNumber phoneNumber = phoneNormalizer.normalize(phone);
        return OkvedToPhoneMatchResult.of(
                phoneNumber,
                okvedMatcher.matchByPhone(okvedDownloader.download(), phoneNumber)
        );
    }
}
