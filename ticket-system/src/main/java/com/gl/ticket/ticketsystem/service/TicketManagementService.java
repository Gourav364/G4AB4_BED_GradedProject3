package com.gl.ticket.ticketsystem.service;

import com.gl.ticket.ticketsystem.entity.Ticket;
import com.gl.ticket.ticketsystem.exceptionhandler.TicketTrackerException;

import java.util.List;

public interface TicketManagementService {

    List<Ticket> ticketList();

    void addTicket(Ticket ticket) throws TicketTrackerException;

    void updateTicket(Ticket ticket) throws TicketTrackerException;

    void deleteTicket(Long id) throws TicketTrackerException;

    Ticket findTicket(Long id) throws TicketTrackerException;

    List<Ticket> searchTicket(String keyword);
}


