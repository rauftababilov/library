package com.andersen.library.services.client.impl;

import com.andersen.library.config.CacheConfig;
import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.client.ClientService;
import com.andersen.library.services.client.ClientValidatorService;
import com.andersen.library.services.client.model.ClientDto;
import com.andersen.library.services.client.model.ClientFilterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    private final ClientMapper mapper;

    private final ClientValidatorService validatorService;

    @Override
    public Page<ClientDto> getAll(ClientFilterDto filterDto, Pageable pageable) {
        return repository.findAllByFilter(filterDto.getFullName(), filterDto.getYearOfBirth(), pageable)
                .map(mapper::toDto);
    }

    @Cacheable(value = CacheConfig.CLIENT_BY_ID_CACHE, key = "#id", condition = "#allowDeleted == true")
    @Override
    public ClientDto get(Long id, boolean allowDeleted) {
        ClientDto clientDto = repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(ExceptionType.CLIENT_NOT_FOUND::exception);

        if (!allowDeleted && clientDto.isDeleted()) {
            throw ExceptionType.CLIENT_DELETED.exception();
        }

        return clientDto;
    }

    @Override
    public ClientDto create(ClientDto dto) {
        Client client = new Client();

        validatorService.throwIfClientAlreadyExists(dto.getFullName());

        mapper.populateClientEntityByDto(client, dto);

        client = repository.save(client);

        return mapper.toDto(client);
    }

    @CacheEvict(value = CacheConfig.CLIENT_BY_ID_CACHE, key = "#id")
    @Override
    public ClientDto update(Long id, ClientDto dto) {
        Client client = repository.findById(id).orElseThrow(ExceptionType.CLIENT_NOT_FOUND::exception);

        validateOnClientUpdate(dto, client);

        mapper.populateClientEntityByDto(client, dto);

        client = repository.save(client);

        return mapper.toDto(client);
    }

    @CacheEvict(value = CacheConfig.CLIENT_BY_ID_CACHE, key = "#id")
    @Override
    public void softDelete(Long id) {
        Client client = repository.findById(id).orElseThrow(ExceptionType.CLIENT_NOT_FOUND::exception);

        validateOnClientDelete(id, client);

        client.setDeleted(true);

        repository.save(client);
    }

    private void validateOnClientDelete(Long id, Client client) {
        validatorService.throwIfClientHasGivenBook(id);
        validatorService.throwIfClientDeleted(client.isDeleted());
    }

    private void validateOnClientUpdate(ClientDto dto, Client client) {
        validatorService.throwIfClientNameChangeNotAllowed(client.getFullName(), dto.getFullName());
        validatorService.throwIfClientDeleted(client.isDeleted());
    }

}
