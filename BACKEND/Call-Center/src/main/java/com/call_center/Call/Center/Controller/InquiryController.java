package com.call_center.Call.Center.Controller;

import com.call_center.Call.Center.DTO.InquiryDTO;
import com.call_center.Call.Center.Service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Ticket")
@CrossOrigin
public class InquiryController {
    @Autowired
    InquiryService inqService;

    @PostMapping("/addInquiry")
    public ResponseEntity<InquiryDTO> addInquiry(@RequestBody InquiryDTO inquiryDTO) {
        return inqService.addInquiry(inquiryDTO);
    }

    @GetMapping("/getAllInquiries")
    public ResponseEntity<List<InquiryDTO>> getAllInquiries() {
        return inqService.getAllInquiries();
    }

    @GetMapping("/Inquiries/{inqId}")
    public ResponseEntity<InquiryDTO> getInqById(@PathVariable long inqId) {
        return inqService.getInquiryByInqId(inqId);
    }
}
