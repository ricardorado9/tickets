package com.doublev.test.repository.specification;

import com.doublev.test.model.Ticket;
import org.springframework.data.jpa.domain.Specification;

public class TicketSpecification {

    public static Specification<Ticket> hasId(Long id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Ticket> hasUser(String user) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("usuario"), "%" + user + "%");
    }

    public static Specification<Ticket> hasStatus(String status) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }

}
