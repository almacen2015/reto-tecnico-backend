package backend.msclient.application.mapper;

import backend.msclient.domain.model.Client;
import backend.msclient.presentation.dto.ClientRequest;
import backend.msclient.presentation.dto.ClientResponse;

public class ClientMapper {
    public static Client toDomain(ClientRequest request) {
        return Client.builder()
                .name(request.name())
                .gender(request.gender())
                .identification(request.identification())
                .address(request.address())
                .phone(request.phone())
                .password(request.password())
                .status(request.status())
                .build();
    }

    public static ClientResponse toResponse(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .gender(client.getGender())
                .identification(client.getIdentification())
                .address(client.getAddress())
                .phone(client.getPhone())
                .status(client.getStatus())
                .password(client.getPassword())
                .build();
    }
}
