package com.vaibhav.spamcache.number;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpamNumberRepository extends JpaRepository<SpamNumber, String> {
}
