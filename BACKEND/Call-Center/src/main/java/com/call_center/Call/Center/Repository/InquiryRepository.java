package com.call_center.Call.Center.Repository;

import com.call_center.Call.Center.Entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
