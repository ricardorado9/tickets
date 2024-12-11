package com.doublev.test.controller;

import com.doublev.test.dto.FilterDto;
import com.doublev.test.dto.TicketDto;
import com.doublev.test.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/tickets")
@RestController
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<List<TicketDto>> find(@RequestBody FilterDto filterDto) {
        return ResponseEntity.ok(ticketService.findTicketsByFilter(filterDto));
    }

    @PostMapping("/create")
    public ResponseEntity<TicketDto> save(@RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.save(ticketDto));
    }

    @PutMapping("/update")
    public ResponseEntity<TicketDto> update(@RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.update(ticketDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        ticketService.delete(id);
        return ResponseEntity.ok("Eliminacion completada");
    }

}
