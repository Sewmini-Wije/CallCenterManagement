package com.call_center.Call.Center.Repository;

import com.call_center.Call.Center.Entity.Employee;
import com.call_center.Call.Center.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    long countByInqStatus(String inqStatus);
    List<Ticket> findByInqStatus(String inqStatus);
    List<Ticket> findByAssignedSkilledAgent (Employee assignedSkilledAgent);
//    List<Ticket> findAllByAdminRv(boolean adminRv);
}
