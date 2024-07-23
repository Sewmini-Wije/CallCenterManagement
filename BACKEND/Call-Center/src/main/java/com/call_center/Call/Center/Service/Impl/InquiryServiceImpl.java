package com.call_center.Call.Center.Service.Impl;

import com.call_center.Call.Center.DTO.InquiryDTO;
import com.call_center.Call.Center.Entity.Inquiry;
import com.call_center.Call.Center.Repository.InquiryRepository;
import com.call_center.Call.Center.Service.InquiryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InquiryServiceImpl implements InquiryService {
    private static final Log log = LogFactory.getLog(InquiryServiceImpl.class);

    @Autowired
    InquiryRepository inqRepo;

    @Override
    public ResponseEntity<InquiryDTO> addInquiry(InquiryDTO inquiryDTO) {
        try {
            Inquiry inquiry = DTOToEntity(inquiryDTO);

            Inquiry saveInquiry = inqRepo.save(inquiry);
            return ResponseEntity.ok(EntityToDTO(saveInquiry));
        } catch (Exception e) {
            log.error("Error : " +e);
            return null;
        }
    }

    @Override
    public ResponseEntity<List<InquiryDTO>> getAllInquiries() {
        List<Inquiry> getInq = inqRepo.findAll();
        List<InquiryDTO> dto = new ArrayList<>();
        for(Inquiry inquiry : getInq) {
            dto.add(EntityToDTO(inquiry));
        }
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<InquiryDTO> getInquiryByInqId(long inqId) {
        Optional<Inquiry> getInq = inqRepo.findById(inqId);
        if(getInq.isPresent()) {
            InquiryDTO dto = EntityToDTO(getInq.get());
            return ResponseEntity.ok(dto);
        } else
            return null;
    }

    private Inquiry DTOToEntity(InquiryDTO inquiryDTO) {
        Inquiry inquiry = new Inquiry();

        inquiry.setInqId(inquiryDTO.getInqId());
        inquiry.setInqType(inquiryDTO.getInqType());

        return inquiry;
    }


    private InquiryDTO EntityToDTO(Inquiry inquiry) {
        InquiryDTO inquiryDTO = new InquiryDTO();

        inquiryDTO.setInqId(inquiry.getInqId());
        inquiryDTO.setInqType(inquiry.getInqType());

        return inquiryDTO;
    }
}