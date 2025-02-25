package com.min.koreaareaboard.reports.repository;

import com.min.koreaareaboard.reports.entity.Reports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Reports, Long> {

}
