package com.vaibhav.spamcache.number;

import com.vaibhav.spamcache.model.SpamStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class SpamNumberController {
    private final SpamNumberService spamNumberService;

    public SpamNumberController(SpamNumberService spamNumberService) {
        this.spamNumberService = spamNumberService;
    }

    @PostMapping("/report-spam/{phoneNumber}")
    public ResponseEntity<String> reportSpam(@PathVariable String phoneNumber) {
        spamNumberService.reportSpam(phoneNumber);
        return ResponseEntity.ok(phoneNumber + " : status updated.");
    }

    @GetMapping("/lookup/{phoneNumber}")
    public ResponseEntity<Map<String, Object>> lookup(@PathVariable String phoneNumber) {
        SpamStatus status = spamNumberService.lookup(phoneNumber);
        return ResponseEntity.ok(Map.of("status", status.name()));
    }
}
