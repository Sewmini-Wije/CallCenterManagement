package com.call_center.Call.Center.Service.Impl;

import com.call_center.Call.Center.DTO.EmployeeDTO;
import com.call_center.Call.Center.DTO.InquiryDTO;
import com.call_center.Call.Center.DTO.TicketDTO;
import com.call_center.Call.Center.Entity.Employee;
import com.call_center.Call.Center.Entity.Inquiry;
import com.call_center.Call.Center.Entity.Ticket;
import com.call_center.Call.Center.Repository.EmployeeRepository;
import com.call_center.Call.Center.Repository.TicketRepository;
import com.call_center.Call.Center.Service.TicketService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);
    @Autowired
    TicketRepository ticketRepo;
    @Autowired
    EmployeeRepository employeeRepo;


    @Transactional
    @Override
    public ResponseEntity<TicketDTO> addTicket(TicketDTO ticketDTO) {
        try {
            Ticket ticket = DTOtoEntity(ticketDTO);
            log.info("Adding Ticket: {}", ticket);

            Ticket saveTicket = ticketRepo.save(ticket);
            log.info("Ticket saved: {}", ticket);

            return ResponseEntity.ok(EntityToDTO(saveTicket));

        } catch (Exception e) {
            log.error("Adding Ticket Failed: ", e);
            return null;
        }
    }


    @Override
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<Ticket> getTickets = ticketRepo.findAll();
        List<TicketDTO> dto = new ArrayList<>();
        for (Ticket ticket : getTickets) {
            dto.add(EntityToDTO(ticket));
        }
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<TicketDTO> updateTicket(long id, TicketDTO ticketDTO) {
        Ticket ticket = DTOtoEntity(ticketDTO);

        if(ticketRepo.existsById(id)) {
            ticket.setId(id);
            Ticket saveTicket = ticketRepo.save(ticket);

            EntityToDTO(saveTicket);
            return ResponseEntity.ok(ticketDTO);
        } else
            return null;
    }

    @Override
    public void deleteTicket(long id) {
        ticketRepo.deleteById(id);
        log.info("Ticket Deleted successfully.");
    }

    @Override
    public ResponseEntity<TicketDTO> getTicketById(long id) {
        Optional<Ticket> ticket = ticketRepo.findById(id);
        if(ticket.isPresent()) {
            TicketDTO dto = EntityToDTO(ticket.get());
            return ResponseEntity.ok(dto);
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity<List<TicketDTO>> getTicketBySkAgent(Employee assignedSkilledAgent) {
        List<Ticket> tickets = ticketRepo.findByAssignedSkilledAgent(assignedSkilledAgent);
        List<TicketDTO> dto = new ArrayList<>();
        for (Ticket ticket : tickets) {
            dto.add(EntityToDTO(ticket));
        }
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<List<TicketDTO>> getNewTickets(String inqStatus) {
//        String inqStatus = "New";
        List<Ticket> tickets = ticketRepo.findByInqStatus(inqStatus);
        List<TicketDTO> dto = new ArrayList<>();
        for (Ticket ticket : tickets) {
            dto.add(EntityToDTO(ticket));
        }
        return ResponseEntity.ok(dto);
    }

//    @Override
//    public void assignTicket(long id, long employeeId) {
//        try {
//            Ticket ticket = ticketRepo.findById(id)
//                    .orElseThrow(() -> new NoSuchElementException("Ticket not found"));
//            Employee employee = employeeRepo.findById(employeeId)
//                    .orElseThrow(() -> new NoSuchElementException("Agent not found."));
//            ticket.setAssignedSkilledAgent(employee);
//            ticketRepo.save(ticket);
//        } catch(NoSuchElementException nex) {
//            System.out.println(nex.getMessage());
//        }
//    }

//    @Override
//    public List<Ticket> getAdminReviewTickets(boolean adminRv) {
//        return ticketRepo.findAllByAdminRv(adminRv);
//    }

    @Override
    public long getTotalTicketCount() {
        return ticketRepo.count();
    }

    @Override
    public long getNewTicketCount() {
        String inqStatus = "New";
        return ticketRepo.countByInqStatus(inqStatus);
    }

    @Override
    public long getPendingTicketCount() {
        String inqStatus = "Pending";
        return ticketRepo.countByInqStatus(inqStatus);
    }

    @Override
    public long getResolvedTicketCount() {
        String inqStatus = "Resolved";
        return ticketRepo.countByInqStatus(inqStatus);
    }

//    public List<Ticket> getAdminReviewTickets() {
//        return ticketRepo.findAllByAdminRvTrue();
//    }


    private Ticket DTOtoEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();

        ticket.setId(ticketDTO.getId());
        ticket.setCustomerName(ticketDTO.getCustomerName());
        ticket.setUserContactNo(ticketDTO.getUserContactNo());
        ticket.setInqDescription(ticketDTO.getInqDescription());
        ticket.setInqStatus(ticketDTO.getInqStatus());
        ticket.setAdminRv(ticketDTO.getAdminRv());

        if(ticketDTO.getAssignedSkilledAgent() != null) {
            Employee employee = new Employee();
            employee.setEmployeeId(ticketDTO.getAssignedSkilledAgent().getEmployeeId());
            employee.setEmployeeFirstName(ticketDTO.getAssignedSkilledAgent().getEmployeeFirstName());
            employee.setEmployeeLastName(ticketDTO.getAssignedSkilledAgent().getEmployeeLastName());
//            employee.setEmployeeGender(ticketDTO.getAssignedSkilledAgent().getEmployeeGender());
//            employee.setEmployeePosition(ticketDTO.getAssignedSkilledAgent().getEmployeePosition());
//            employee.setEmployeeContactNo(ticketDTO.getAssignedSkilledAgent().getEmployeeContactNo());
//            employee.setEmployeeEmail(ticketDTO.getAssignedSkilledAgent().getEmployeeEmail());
//            employee.setEmployeePassword(ticketDTO.getAssignedSkilledAgent().getEmployeePassword());
//            employee.setEmployeeCreatedAt(ticketDTO.getAssignedSkilledAgent().getEmployeeCreatedAt());
//            employee.setEmployeeUpdatedAt(ticketDTO.getAssignedSkilledAgent().getEmployeeUpdatedAt());
//            if(ticketDTO.getAssignedSkilledAgent().getInquiryList() != null) {
//                List<Inquiry> inquiryList = new ArrayList<>();
//                for (InquiryDTO inquiryDTO : ticketDTO.getAssignedSkilledAgent().getInquiryList()) {
//                    Inquiry inquiry = new Inquiry();
//                    inquiry.setInqId(inquiryDTO.getInqId());
//                    inquiry.setInqType(inquiryDTO.getInqType());
//                    inquiryList.add(inquiry);
//                }
//                employee.setInquiryList(inquiryList);
//            }
            ticket.setAssignedSkilledAgent(employee);
        }

        if (ticketDTO.getAssignedTicket() != null) {
            Inquiry inquiry = new Inquiry();
            inquiry.setInqId(ticketDTO.getAssignedTicket().getInqId());
            inquiry.setInqType(ticketDTO.getAssignedTicket().getInqType());
            ticket.setAssignedTicket(inquiry);
        }

        return ticket;
    }

    private TicketDTO EntityToDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();

        ticketDTO.setId(ticket.getId());
        ticketDTO.setCustomerName(ticket.getCustomerName());
        ticketDTO.setUserContactNo(ticket.getUserContactNo());
        ticketDTO.setInqDescription(ticket.getInqDescription());
        ticketDTO.setInqStatus(ticket.getInqStatus());
        ticketDTO.setAdminRv(ticket.getAdminRv());
        ticketDTO.setCreatedAt(ticket.getCreatedAt());
        ticketDTO.setUpdatedAt(ticket.getUpdatedAt());

        if(ticket.getAssignedSkilledAgent() != null) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setEmployeeId(ticket.getAssignedSkilledAgent().getEmployeeId());
            employeeDTO.setEmployeeFirstName(ticket.getAssignedSkilledAgent().getEmployeeFirstName());
            employeeDTO.setEmployeeLastName(ticket.getAssignedSkilledAgent().getEmployeeLastName());
//            employeeDTO.setEmployeeGender(ticket.getAssignedSkilledAgent().getEmployeeGender());
//            employeeDTO.setEmployeePosition(ticket.getAssignedSkilledAgent().getEmployeePosition());
//            employeeDTO.setEmployeeContactNo(ticket.getAssignedSkilledAgent().getEmployeeContactNo());
//            employeeDTO.setEmployeeEmail(ticket.getAssignedSkilledAgent().getEmployeeEmail());
//            employeeDTO.setEmployeePassword(ticket.getAssignedSkilledAgent().getEmployeePassword());
//            employeeDTO.setEmployeeCreatedAt(ticket.getAssignedSkilledAgent().getEmployeeCreatedAt());
//            employeeDTO.setEmployeeUpdatedAt(ticket.getAssignedSkilledAgent().getEmployeeUpdatedAt());
//            if (ticket.getAssignedSkilledAgent().getInquiryList() != null) {
//                List<InquiryDTO> inquiryDTOs = new ArrayList<>();
//                for (Inquiry inquiry : ticket.getAssignedSkilledAgent().getInquiryList()) {
//                    InquiryDTO inquiryDTO = new InquiryDTO();
//                    inquiryDTO.setInqId(inquiry.getInqId());
//                    inquiryDTO.setInqType(inquiry.getInqType());
//                    inquiryDTOs.add(inquiryDTO);
//                }
//                employeeDTO.setInquiryList(inquiryDTOs);
//            }
            ticketDTO.setAssignedSkilledAgent(employeeDTO);
        }

        if (ticket.getAssignedTicket() != null) {
            InquiryDTO inquiryDTO = new InquiryDTO();
            inquiryDTO.setInqId(ticket.getAssignedTicket().getInqId());
            inquiryDTO.setInqType(ticket.getAssignedTicket().getInqType());
            ticketDTO.setAssignedTicket(inquiryDTO);
        }
        return ticketDTO;
    }
}
