package com.andersen.library.services.client.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.client.ClientDto;
import com.andersen.library.services.client.ClientFilterDto;
import com.andersen.library.services.client.ClientService;
import lombok.RequiredArgsConstructor;
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
        return null;
    }

    @Override
    public ClientDto get(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(ExceptionType.CLIENT_NOT_FOUND::exception);
    }

    @Override
    public ClientDto create(ClientDto dto) {
        Client client = new Client();

        validatorService.throwIfClientAlreadyExists(dto.getFullName());

        client.setBirthday(dto.getBirthday());
        client.setFullName(dto.getFullName());

        client = repository.save(client);

        return mapper.toDto(client);
    }

    @Override
    public ClientDto fullUpdate(Long id, ClientDto dto) {
        Client client = repository.findById(id).orElseThrow(ExceptionType.CLIENT_NOT_FOUND::exception);

        validatorService.throwIfClientNameChangeNotAllowed(client.getFullName(), dto.getFullName());

        client.setBirthday(dto.getBirthday());
        client.setFullName(dto.getFullName());

        client = repository.save(client);

        return mapper.toDto(client);
    }

    @Override
    public void delete(Long id) {
        Client client = repository.findById(id).orElseThrow(ExceptionType.CLIENT_NOT_FOUND::exception);

        validatorService.throwIfClientHasGivenBook(id);

        repository.delete(client);
    }

}
