package com.andersen.library.services.client;

import com.andersen.library.services.client.model.ClientDto;
import com.andersen.library.services.client.model.ClientFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Page<ClientDto> getAll(ClientFilterDto filterDto, Pageable pageable);

    ClientDto get(Long id);

    ClientDto create(ClientDto dto);

    ClientDto update(Long id, ClientDto dto);

    void softDelete(Long id);

}
