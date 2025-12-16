package com.example.okvedphonematcher.service.impl;

import com.example.okvedphonematcher.domain.OkvedData;
import com.example.okvedphonematcher.domain.OkvedNode;
import com.example.okvedphonematcher.domain.PhoneNumber;
import com.example.okvedphonematcher.exception.OkvedNotFoundException;
import com.example.okvedphonematcher.service.FallbackStrategy;
import com.example.okvedphonematcher.utils.StringFilterUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class NearestPrefixFallbackStrategy implements FallbackStrategy {

    @Override
    public OkvedNode match(PhoneNumber phone, OkvedData data) {
        OkvedNode best = null;
        int bestLen = 0;
        for (OkvedNode n : flatten(data.items())) {
            String code = StringFilterUtils.filterDigits(n.code());
            if (code.isBlank()) continue;
            int len = commonPrefixLength(phone.value(), code);
            if (len > bestLen) {
                bestLen = len;
                best = n;
            }
        }
        if (Objects.nonNull(best)){
            return best;
        }
        throw new OkvedNotFoundException("Okved for phone " + phone.value() + " not found");
    }

    private int commonPrefixLength(String phone, String code) {
        int i = 0;
        int maxLength = Math.min(phone.length()-2, code.length());
        while (i < maxLength && phone.charAt(i+2) == code.charAt(i)) {
            i++;
        }
        return i;
    }

    private List<OkvedNode> flatten(List<OkvedNode> roots) {
        List<OkvedNode> out = new ArrayList<>();
        Deque<OkvedNode> dq = new ArrayDeque<>(roots);
        while (!dq.isEmpty()) {
            OkvedNode n = dq.removeFirst();
            out.add(n);
            if (n.items() != null) dq.addAll(n.items());
        }
        return out;
    }
}
