package com.vaibhav.spamcache.number;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class SpamNumber {
    @Id
    private String phoneNumber;

    private int spamReportCount;

    private LocalDateTime lastReportedAt;

    public SpamNumber() {
    }

    public SpamNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.spamReportCount = 1;
        this.lastReportedAt = LocalDateTime.now();
    }

    public SpamNumber(String phoneNumber, int spamReportCount, LocalDateTime lastReportedAt) {
        this.phoneNumber = phoneNumber;
        this.spamReportCount = spamReportCount;
        this.lastReportedAt = lastReportedAt;
    }

    public SpamNumber(String phoneNumber, int spamReportCount) {
        this.phoneNumber = phoneNumber;
        this.spamReportCount = spamReportCount;
    }

    public void incrementSpamReportCount() {
        this.spamReportCount++;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getSpamReportCount() {
        return spamReportCount;
    }

    public void setSpamReportCount(int spamReportCount) {
        this.spamReportCount = spamReportCount;
    }

    public LocalDateTime getLastReportedAt() {
        return lastReportedAt;
    }

    public void setLastReportedAt(LocalDateTime lastReportedAt) {
        this.lastReportedAt = lastReportedAt;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SpamNumber{");
        sb.append("phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", spamReportCount=").append(spamReportCount);
        sb.append('}');
        return sb.toString();
    }
}
