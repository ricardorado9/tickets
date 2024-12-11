package com.doublev.test.service;

import com.doublev.test.dto.FilterDto;
import com.doublev.test.dto.TicketDto;
import com.doublev.test.mapper.TicketMapper;
import com.doublev.test.model.Ticket;
import com.doublev.test.repository.TicketRepository;
import com.doublev.test.repository.specification.TicketSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    @Value("#{${app.page-size}}")
    private Long pageSize;

    @Transactional
    public TicketDto save(TicketDto ticketDto) {
        Ticket ticket = ticketRepository.save(ticketMapper.dtoToEntity(ticketDto));
        return ticketMapper.entityToDto(ticket);
    }

    @Transactional
    public TicketDto update(TicketDto ticketDto) {
        Optional<Ticket> entity = ticketRepository.findById(ticketDto.getId());
        if (entity.isPresent()) {
            entity.get().setStatus(ticketDto.getStatus());
            return ticketMapper.entityToDto(ticketRepository.save(entity.get()));
        }
        return null;
    }

    public void delete(Long id) {
        ticketRepository.deleteById(id);
    }

    public List<TicketDto> findTicketsByFilter(FilterDto filterDto) {
        Pageable page = PageRequest.of(Objects.nonNull(filterDto.getPageNumber()) ? filterDto.getPageNumber() : 0, pageSize.intValue());

        Specification<Ticket> spec = Specification.where(null);
        if (!ObjectUtils.isEmpty(filterDto.getId())) {
            spec = spec.and(TicketSpecification.hasId(filterDto.getId()));
        }
        if (!ObjectUtils.isEmpty(filterDto.getUsuario())) {
            spec = spec.and(TicketSpecification.hasUser(filterDto.getUsuario()));
        }

        if (!ObjectUtils.isEmpty(filterDto.getStatus())) {
            spec = spec.and(TicketSpecification.hasStatus(filterDto.getStatus()));
        }
        return ticketRepository.findAll(spec, page).map(ticketMapper::entityToDto).stream().collect(Collectors.toList());
    }

}
