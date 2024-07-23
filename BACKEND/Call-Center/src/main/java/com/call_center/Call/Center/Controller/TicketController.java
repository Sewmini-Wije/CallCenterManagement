package com.call_center.Call.Center.Controller;

import com.call_center.Call.Center.DTO.TicketDTO;
import com.call_center.Call.Center.Entity.Employee;
import com.call_center.Call.Center.Entity.Ticket;
import com.call_center.Call.Center.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Ticket")
@CrossOrigin
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/addTicket")
    public ResponseEntity<TicketDTO> addTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.addTicket(ticketDTO);
    }

    @GetMapping("/getTicket")
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @PutMapping("/updateTicket/{id}")
    public ResponseEntity<TicketDTO> updateTicket (@PathVariable long id, @RequestBody TicketDTO ticketDTO) {
        return ticketService.updateTicket(id, ticketDTO);
    }

    @DeleteMapping("/deleteTicket/{id}")
    public void deleteTicket(@PathVariable long id) {
        ticketService.deleteTicket(id);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<TicketDTO> findById (@PathVariable long id) {
        return ticketService.getTicketById(id);
    }

    @GetMapping("/{inqStatus}")
    public ResponseEntity<List<TicketDTO>> findByInqStatus (@PathVariable String inqStatus) {
        return ticketService.getNewTickets(inqStatus);
    }

    @GetMapping("/totalTicketCount")
    public long getTotalTicket() {
        return ticketService.getTotalTicketCount();
    }

    @GetMapping("/newTicketCount")
    public long getNewTicketCount() {
        return ticketService.getNewTicketCount();
    }

    @GetMapping("/pendingTicketCount")
    public long getPendingTicketCount() {
        return ticketService.getPendingTicketCount();
    }

    @GetMapping("/resolvedTicketCount")
    public long getResolvedTicketCount() {
        return ticketService.getResolvedTicketCount();
    }

    @GetMapping("/skAgent")
    public ResponseEntity<List<TicketDTO>> getTicketBySkAgent(@RequestParam Long skAgentId) {
        Employee assignedSkilledAgent = new Employee();
        assignedSkilledAgent.setEmployeeId(skAgentId);
        return ticketService.getTicketBySkAgent(assignedSkilledAgent);
    }

//    @GetMapping("/adminReview")
//    public ResponseEntity<List<Ticket>> getAdminReviewTickets() {
//        List<Ticket> tickets = ticketService.getAdminReviewTickets(true);
//        return ResponseEntity.ok(tickets);
//    }

}
