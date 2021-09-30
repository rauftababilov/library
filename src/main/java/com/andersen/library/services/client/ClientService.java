package com.andersen.library.services.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Page<ClientDto> getAll(ClientFilterDto filterDto, Pageable pageable);

    ClientDto get(Long id);

    ClientDto create(ClientDto dto);

    ClientDto fullUpdate(Long id, ClientDto dto);

    void delete(Long id);

}
