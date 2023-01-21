package com.gl.ticket.ticketsystem.controller;

import com.gl.ticket.ticketsystem.entity.Ticket;
import com.gl.ticket.ticketsystem.exceptionhandler.TicketTrackerException;
import com.gl.ticket.ticketsystem.service.TicketManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketManagementController {

    private final TicketManagementService ticketManagementService;

    @GetMapping("/list")
    public String ticketListView(Model model) {
        model.addAttribute("tickets", ticketManagementService.ticketList());
        return "ticket/ticketList";
    }

    @GetMapping("/createTicketView")
    public String createTicketView(Model model) {
        Ticket ticket = new Ticket();
        model.addAttribute("ticket", ticket);
        return "ticket/create_ticket";
    }

    @PostMapping("/saveTicket")
    public String saveTicket(@ModelAttribute Ticket ticket) throws TicketTrackerException {

        ticketManagementService.addTicket(ticket);
        return "redirect:/ticket/list";
    }

    @GetMapping("/updateTicketView")
    public String updateTicketView(Model model, @RequestParam("ticketId") Long ticketId) throws TicketTrackerException {
        model.addAttribute("ticket", ticketManagementService.findTicket(ticketId));
        return "ticket/update_ticket";
    }

    @PostMapping("/updateTicket")
    public String update(@ModelAttribute("ticket") Ticket ticket) throws TicketTrackerException {
        ticketManagementService.updateTicket(ticket);
        return "redirect:/ticket/list";
    }

    @GetMapping("/redirectionUrl")
    public String errorView(@ModelAttribute("flashAttribute") Object flashAttribute, Model model) {
        model.addAttribute("redirectionAttribute", flashAttribute);
        return "ticket/error";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("ticketId") Long ticketId) throws TicketTrackerException {
        ticketManagementService.deleteTicket(ticketId);
        return "redirect:/ticket/list";
    }
}
