package com.gl.ticket.ticketsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketExceptionController {

    @GetMapping("/redirectionUrl")
    public String errorView(@ModelAttribute("flashAttribute") Object flashAttribute, Model model) {
        model.addAttribute("redirectionAttribute", flashAttribute);
        return "ticket/error";
    }
}
