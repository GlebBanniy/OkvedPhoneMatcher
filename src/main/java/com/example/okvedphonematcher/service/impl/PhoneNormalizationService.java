package com.example.okvedphonematcher.service.impl;

import com.example.okvedphonematcher.domain.PhoneNumber;
import com.example.okvedphonematcher.exception.InvalidMobilePhoneNumberException;
import com.example.okvedphonematcher.service.PhoneNormalizer;
import com.example.okvedphonematcher.utils.StringFilterUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PhoneNormalizationService implements PhoneNormalizer {

    private static final int TEN_DIGITS = 10;
    private static final int ELEVEN_DIGITS = 11;
    private static final String COUNTRY_CODE_7 = "7";
    private static final String COUNTRY_CODE_8 = "8";
    private static final String BASE_COUNTRY_CODE = "+7";
    private static final String MOBILE_PHONE_FIRST_DIGIT = "9";

    @Override
    public PhoneNumber normalize(String rawNumber) {
        String s = rawNumber.trim();
        boolean hasPlus = s.startsWith("+");
        String digits = StringFilterUtils.filterDigits(s);
        if (digits.length() == ELEVEN_DIGITS) {
            // Может быть 8XXXXXXXXXX или 7XXXXXXXXXX
            return buildFor11Digits(digits, hasPlus);
        } else if (digits.length() == TEN_DIGITS) {
            // Ожидаем мобильный без кода: 9XXXXXXXX
            return buildIfMobile(digits);
        } else {
            throw new InvalidMobilePhoneNumberException("Unexpected digit count: " + digits.length());
        }
    }

    private PhoneNumber buildFor11Digits(String elevenDigits, boolean hasPlus) {
        if ((elevenDigits.startsWith(COUNTRY_CODE_8) && !hasPlus) || elevenDigits.startsWith(COUNTRY_CODE_7)) {
            return buildIfMobile(elevenDigits.substring(1));
        } else {
            throw new InvalidMobilePhoneNumberException("11 digits but doesn't start with 7 or 8");
        }
    }

    private PhoneNumber buildIfMobile(String tenDigits) {
        // В РФ мобильные номера начинаются с 9
        if (tenDigits.startsWith(MOBILE_PHONE_FIRST_DIGIT)) {
            return new PhoneNumber(BASE_COUNTRY_CODE + tenDigits);
        }
        throw new InvalidMobilePhoneNumberException(
                "Not a Russian mobile number: expected first digit '9', got '" + tenDigits.charAt(0) + "'"
        );
    }
}
