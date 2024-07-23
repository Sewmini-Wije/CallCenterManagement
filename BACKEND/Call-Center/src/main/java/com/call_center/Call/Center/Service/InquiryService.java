package com.call_center.Call.Center.Service;

import com.call_center.Call.Center.DTO.InquiryDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InquiryService {
    ResponseEntity<InquiryDTO> addInquiry(InquiryDTO inquiryDTO);
    ResponseEntity<List<InquiryDTO>> getAllInquiries();
    ResponseEntity<InquiryDTO> getInquiryByInqId(long inqId);
}
