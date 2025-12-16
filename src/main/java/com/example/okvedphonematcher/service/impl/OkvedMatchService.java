package com.example.okvedphonematcher.service.impl;

import com.example.okvedphonematcher.domain.BestMatchOkved;
import com.example.okvedphonematcher.domain.OkvedNode;
import com.example.okvedphonematcher.domain.OkvedData;
import com.example.okvedphonematcher.domain.PhoneNumber;
import com.example.okvedphonematcher.service.FallbackStrategy;
import com.example.okvedphonematcher.service.OkvedMatcher;
import com.example.okvedphonematcher.utils.StringFilterUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class OkvedMatchService implements OkvedMatcher {

    private final FallbackStrategy fallback;

    @Override
    public BestMatchOkved matchByPhone(OkvedData data, PhoneNumber phone) {
        OkvedNode bestNode = null;
        int bestLen = -1;

        for (OkvedNode node : flatten(data.items())) {
            String code = StringFilterUtils.filterDigits(node.code());
            if (code.isBlank()) continue;
            if (endsWith(phone.value(), code)) {
                int len = code.length();
                if (len > bestLen) {
                    bestLen = len;
                    bestNode = node;
                }
            }
        }
        if (bestNode != null) {
            return new BestMatchOkved(bestNode, bestLen);
        }
        log.info("OKVED not found, trying use fallback strategy");
        return fallback.match(phone, data);
    }

    private boolean endsWith(String phone, String code) {
        if (code.length() > phone.length()) return false;
        return phone.endsWith(code);
    }

    private List<OkvedNode> flatten(List<OkvedNode> roots) {
        List<OkvedNode> out = new ArrayList<>();
        Deque<OkvedNode> dq = new ArrayDeque<>(roots);
        while (!dq.isEmpty()) {
            OkvedNode n = dq.removeFirst();
            out.add(n);
            if (n.items() != null) {
                dq.addAll(n.items());
            }
        }
        return out;
    }
}
