package com.eleks.academy.pharmagator.services;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ControllerService<I, Req, Resp> {
    List<Resp> getAll();

    ResponseEntity<Resp> getById(I id);

    void create(Req req);

    void deleteById(I id);

    ResponseEntity<Resp> update(I id, Req entity);
}
