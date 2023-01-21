package com.gl.ticket.ticketsystem.controller;

import com.gl.ticket.ticketsystem.exceptionhandler.TicketTrackerException;
import com.gl.ticket.ticketsystem.service.TicketManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketViewAndSearchController {

    private final TicketManagementService ticketManagementService;

    @PostMapping(value = "/viewTicket")
    public String viewTicket(@RequestParam("ticketId") Long ticketId, Model model) throws TicketTrackerException {
        model.addAttribute("ticket", ticketManagementService.findTicket(ticketId));
        return "/ticket/view_ticket";
    }

    @GetMapping(value = "/search")
    public String searchTicket(@RequestParam("keyword") String keyword, Model model) throws TicketTrackerException {
        model.addAttribute("tickets", ticketManagementService.searchTicket(keyword));
        return "ticket/ticketList";
    }
}
