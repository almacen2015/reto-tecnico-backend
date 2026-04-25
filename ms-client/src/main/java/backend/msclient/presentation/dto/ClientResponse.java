package backend.msclient.presentation.dto;

public record ClientResponse(
        String name,
        String gender,
        String identification,
        String address,
        String phone,
        String password,
        Boolean status
) {
}
