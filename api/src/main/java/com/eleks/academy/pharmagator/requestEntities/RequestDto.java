package com.eleks.academy.pharmagator.requestEntities;

public interface RequestDto<T, TResp> {
    T getMappedEntity();

    TResp getMappedDtoEntity();
}
