package com.doublev.test.controller;

import com.doublev.test.dto.FilterDto;
import com.doublev.test.dto.TicketDto;
import com.doublev.test.dto.UserDto;
import com.doublev.test.service.TicketService;
import com.doublev.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class TicketControllerV2 {

    private final TicketService ticketService;
    private final UserService userService;

    @QueryMapping
    public List<TicketDto> getTickets(@Argument(value = "filter") FilterDto filter) {
        return ticketService.findTicketsByFilter(filter);
    }

    @MutationMapping
    public TicketDto createTicket(@Argument(value = "ticket") TicketDto ticketDto) {
        return ticketService.save(ticketDto);
    }

    @MutationMapping
    public TicketDto updateTicket(@Argument(value = "ticket") TicketDto ticketDto) {
        return ticketService.update(ticketDto);
    }

    @MutationMapping
    public String deleteTicket(@Argument(value = "id") Long id) {
        ticketService.delete(id);
        return "Eliminacion completada";
    }

    @QueryMapping
    public UserDto getUsers(@Argument(value = "q") String username) {
        return userService.findUsers(username);
    }

}
