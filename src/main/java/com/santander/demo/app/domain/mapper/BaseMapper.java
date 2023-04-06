package com.santander.demo.app.domain.mapper;


public interface BaseMapper<E, R> {

    R toResponse(E entity);
}
