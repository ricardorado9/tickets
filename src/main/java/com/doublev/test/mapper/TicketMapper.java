package com.doublev.test.mapper;

import com.doublev.test.dto.TicketDto;
import com.doublev.test.model.Ticket;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;
import java.util.Objects;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    TicketDto entityToDto(Ticket ticket);

    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Ticket dtoToEntity(TicketDto ticketDto);

    @AfterMapping
    default void setFechaCreacion(@MappingTarget Ticket ticket) {
        if (Objects.isNull(ticket.getId())) {
            ticket.setFechaCreacion(LocalDateTime.now());
        }
    }
}
