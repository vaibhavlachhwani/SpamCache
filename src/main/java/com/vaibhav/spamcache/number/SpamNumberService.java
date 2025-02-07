package com.vaibhav.spamcache.number;

import com.vaibhav.spamcache.model.SpamStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SpamNumberService {
    private final SpamNumberRepository spamNumberRepository;

    @Value("${app.spam.base_threshold}")
    private double baseThreshold; // Default: 10

    @Value("${app.spam.time_decay_factor}")
    private double timeDecayFactor; // Default: 0.05

    @Value("${app.spam.report_weight_scaling}")
    private double reportWeightScaling; // Default: 1.5

    public SpamNumberService(SpamNumberRepository spamNumberRepository) {
        this.spamNumberRepository = spamNumberRepository;
    }

    public void reportSpam(String phoneNumber) {
        Optional<SpamNumber> spamEntry = spamNumberRepository.findById(phoneNumber);

        if (spamEntry.isPresent()) {
            SpamNumber spamNumber = spamEntry.get();
            spamNumber.setSpamReportCount((int) (spamNumber.getSpamReportCount() + calculateReportWeight(spamNumber.getSpamReportCount())));
            spamNumber.setLastReportedAt(LocalDateTime.now());
            spamNumberRepository.save(spamNumber);
        } else {
            spamNumberRepository.save(new SpamNumber(phoneNumber));
        }
    }

    public SpamStatus lookup(String phoneNumber) {
        Optional<SpamNumber> spamEntry = spamNumberRepository.findById(phoneNumber);

        if (spamEntry.isEmpty()) {
            return SpamStatus.NOT_SPAM;
        }

        SpamNumber spamNumber = spamEntry.get();
        double spamScore = calculateSpamScore(spamNumber);

        if (spamScore >= 0.7) {
            return SpamStatus.SPAM;
        } else if (spamScore >= 0.3) {
            return SpamStatus.LIKELY_SPAM;
        } else {
            return SpamStatus.NOT_SPAM;
        }
    }

    private double calculateSpamScore(SpamNumber spamNumber) {
        double spamCount = spamNumber.getSpamReportCount();
        long daysSinceLastReport = Duration.between(spamNumber.getLastReportedAt(), LocalDateTime.now()).toDays();

        // Time Decay Factor
        double timeDecay = Math.exp(-timeDecayFactor * daysSinceLastReport);

        // Effective Spam Count
        double effectiveSpamCount = calculateReportWeight(spamCount) * timeDecay;

        // Normalize Spam Score
        return Math.min(1.0, effectiveSpamCount / baseThreshold);
    }

    private double calculateReportWeight(double spamCount) {
        return reportWeightScaling * (1 + Math.log(spamCount + 1) / Math.log(2));
    }
}
