package com.gl.ticket.ticketsystem.service;

import com.gl.ticket.ticketsystem.entity.Ticket;
import com.gl.ticket.ticketsystem.exceptionhandler.TicketTrackerException;
import com.gl.ticket.ticketsystem.repository.TicketManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketManagementServiceImpl implements TicketManagementService {

    private final TicketManagementRepository ticketManagementRepository;

    @Override
    public List<Ticket> ticketList() {
        return ticketManagementRepository.findAll();
    }

    @Override
    public void addTicket(Ticket ticket) throws TicketTrackerException {

        if (StringUtils.isEmpty(ticket.getTitle())) {
            throw new TicketTrackerException("Title should not be null", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isEmpty(ticket.getDescription())) {
            throw new TicketTrackerException("Description should not be null", HttpStatus.BAD_REQUEST);
        }

        ticket.setCreateAt(new Timestamp(new Date().getTime()));
        ticketManagementRepository.save(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) throws TicketTrackerException {
        if (Objects.isNull(ticket.getId())) {
            throw new TicketTrackerException("Id is mandatory parameter", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isEmpty(ticket.getTitle())) {
            throw new TicketTrackerException("Title is mandatory parameter", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isEmpty(ticket.getDescription())) {
            throw new TicketTrackerException("Description is mandatory parameter", HttpStatus.BAD_REQUEST);
        }

        Optional<Ticket> existingEmployeeOptional = ticketManagementRepository.findById(ticket.getId());
        if (!existingEmployeeOptional.isPresent()) {
            throw new TicketTrackerException("Ticket not present in system", HttpStatus.BAD_REQUEST);
        }

        ticketManagementRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) throws TicketTrackerException {
        if (Objects.isNull(id)) {
            throw new TicketTrackerException("Id is mandatory parameter", HttpStatus.BAD_REQUEST);
        }

        Optional<Ticket> existingEmployeeOptional = ticketManagementRepository.findById(id);
        if (!existingEmployeeOptional.isPresent()) {
            throw new TicketTrackerException("Ticket not present in system to delete", HttpStatus.BAD_REQUEST);
        }

        ticketManagementRepository.deleteById(id);
    }

    @Override
    public Ticket findTicket(Long id) throws TicketTrackerException {
        Optional<Ticket> existingEmployeeOptional = ticketManagementRepository.findById(id);
        if (!existingEmployeeOptional.isPresent()) {
            throw new TicketTrackerException("Ticket not present in system.", HttpStatus.BAD_REQUEST);
        }

        return existingEmployeeOptional.get();
    }

    @Override
    public List<Ticket> searchTicket(String keyword) {
        return ticketManagementRepository.findByKeyword(keyword);
    }
}
