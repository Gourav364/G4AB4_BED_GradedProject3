package com.gl.ticket.ticketsystem.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionHandler {

   @org.springframework.web.bind.annotation.ExceptionHandler(TicketTrackerException.class)
    public String handleException(HttpServletRequest request, TicketTrackerException ex, RedirectAttributes redirectAttrs) {
        log.error("[GlobalExceptionHandler.FastagInventoryException] url: {}, HttpStatus: {}, " +
                "message: {}, exception:  ", request.getRequestURL().toString(), ex.getStatus(), ex.getMessage(), ex);
       redirectAttrs.addFlashAttribute("errorObject", ex);
       return "redirect:/ticket/redirectionUrl";
    }
}
