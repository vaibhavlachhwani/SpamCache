package com.vaibhav.spamcache.number;

import com.vaibhav.spamcache.model.SpamStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpamNumberService {
    private final SpamNumberRepository spamNumberRepository;

    @Value("${spam.delta.min}")
    private int DELTA_MIN;

    @Value("${spam.delta.max}")
    private int DELTA_MAX;

    public SpamNumberService(SpamNumberRepository spamNumberRepository) {
        this.spamNumberRepository = spamNumberRepository;
    }

    public void reportSpam(String phoneNumber) {
        Optional<SpamNumber> existing = spamNumberRepository.findById(phoneNumber);

        if (existing.isPresent()) {
            SpamNumber spamNumber = existing.get();
            spamNumber.incrementSpamReportCount();
            spamNumberRepository.save(spamNumber);
        } else {
            spamNumberRepository.save(new SpamNumber(phoneNumber));
        }
    }

    public SpamStatus lookup(String phoneNumber) {
        Optional<SpamNumber> spamEntry = spamNumberRepository.findById(phoneNumber);

        if(spamEntry.isEmpty()) {
            return SpamStatus.NOT_SPAM;
        }

        int spamReportCount = spamEntry.get().getSpamReportCount();

        if (spamReportCount < DELTA_MIN) {
            return SpamStatus.NOT_SPAM;
        } else if (spamReportCount > DELTA_MAX) {
            return SpamStatus.SPAM;
        } else {
            return SpamStatus.LIKELY_SPAM;
        }
    }
}
