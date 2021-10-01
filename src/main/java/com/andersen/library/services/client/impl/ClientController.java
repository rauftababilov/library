package com.andersen.library.services.client.impl;

import com.andersen.library.services.client.ClientService;
import com.andersen.library.services.client.ClientUrl;
import com.andersen.library.services.client.model.ClientDto;
import com.andersen.library.services.client.model.ClientFilterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class ClientController {

    private final ClientService clientService;

    @GetMapping(ClientUrl.GET)
    public ClientDto get(@PathVariable Long clientId) {
        return clientService.get(clientId, true);
    }

    @GetMapping(ClientUrl.FIND)
    public Page<ClientDto> getAll(ClientFilterDto filterDto, Pageable pageable) {
        return clientService.getAll(filterDto, pageable);
    }

    @PostMapping(ClientUrl.CREATE)
    public ClientDto create(@Valid @RequestBody ClientDto dto) {
        return clientService.create(dto);
    }

    @PutMapping(ClientUrl.UPDATE)
    public ClientDto update(@PathVariable Long clientId, @Valid @RequestBody ClientDto dto) {
        return clientService.update(clientId, dto);
    }

    @DeleteMapping(ClientUrl.DELETE)
    public void delete(@PathVariable Long clientId) {
        clientService.softDelete(clientId);
    }

}
