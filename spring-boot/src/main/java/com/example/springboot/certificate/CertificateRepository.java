package com.example.springboot.certificate;

import com.example.springboot.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    @Query("SELECT c FROM Certificate c WHERE c.fileName = ?1")
    Optional<Certificate> findCertificateByFileName(String fileName);
}
