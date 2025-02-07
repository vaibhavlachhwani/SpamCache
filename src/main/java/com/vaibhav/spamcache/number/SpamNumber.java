package com.vaibhav.spamcache.number;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SpamNumber {
    @Id
    private String phoneNumber;

    private int spamReportCount;

    public SpamNumber() {
    }

    public SpamNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.spamReportCount = 0;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SpamNumber{");
        sb.append("phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", spamReportCount=").append(spamReportCount);
        sb.append('}');
        return sb.toString();
    }
}
