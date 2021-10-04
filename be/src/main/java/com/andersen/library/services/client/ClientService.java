package com.andersen.library.services.client;

import com.andersen.library.services.client.model.ClientDto;
import com.andersen.library.services.client.model.ClientFilterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    /**
     * Find all clients
     *
     * @param filterDto clientFilterDto
     * @param pageable  pageable
     * @return list of clientDtos
     */
    Page<ClientDto> getAll(ClientFilterDto filterDto, Pageable pageable);

    /**
     * Find client by id
     *
     * @param id           id client
     * @param allowDeleted allow deleted flag
     * @return clientDto
     */
    ClientDto get(Long id, boolean allowDeleted);

    /**
     * Add client to DB
     *
     * @param dto clientDto
     * @return clientDto
     */
    ClientDto create(ClientDto dto);

    /**
     * Update client in DB
     *
     * @param id  id client
     * @param dto clientDto
     * @return updated clientDto
     */
    ClientDto update(Long id, ClientDto dto);

    /**
     * Delete client by id from DB
     *
     * @param id id client
     */
    void softDelete(Long id);

}
