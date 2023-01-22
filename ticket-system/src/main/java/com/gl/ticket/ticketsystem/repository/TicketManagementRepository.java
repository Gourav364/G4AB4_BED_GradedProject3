package com.gl.ticket.ticketsystem.repository;

import com.gl.ticket.ticketsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketManagementRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "select * from ticket t where t.title like %:keyword% or t.description like %:keyword%", nativeQuery = true)
    List<Ticket> findByKeyword(@Param("keyword") String keyword);
}
