package com.call_center.Call.Center.Service;

import com.call_center.Call.Center.DTO.TicketDTO;
import com.call_center.Call.Center.Entity.Employee;
import com.call_center.Call.Center.Entity.Ticket;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TicketService {
    ResponseEntity<TicketDTO> addTicket(TicketDTO ticketDTO);
    ResponseEntity<List<TicketDTO>> getAllTickets();
    ResponseEntity<TicketDTO> updateTicket(long id, TicketDTO ticketdto);
    void deleteTicket(long id);
    ResponseEntity<TicketDTO> getTicketById(long id);
    ResponseEntity<List<TicketDTO>> getTicketBySkAgent(Employee assignedSkilledAgent);
    ResponseEntity<List<TicketDTO>> getNewTickets(String inqStatus);
//    List<Ticket> getAdminReviewTickets(boolean adminRv);
    long getTotalTicketCount();
    long getNewTicketCount();
    long getPendingTicketCount();
    long getResolvedTicketCount();
//    ResponseEntity<List<Ticket>> getFilteredTickets();
}
