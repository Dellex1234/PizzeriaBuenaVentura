package pe.edu.cibertec.PizzeriaBuenaVentura.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.PizzeriaBuenaVentura.dto.ClientDetailDto;
import pe.edu.cibertec.PizzeriaBuenaVentura.dto.ClientDto;
import pe.edu.cibertec.PizzeriaBuenaVentura.entity.Client;
import pe.edu.cibertec.PizzeriaBuenaVentura.repository.ClientRepository;
import pe.edu.cibertec.PizzeriaBuenaVentura.repository.PizzaRepository;
import pe.edu.cibertec.PizzeriaBuenaVentura.service.MaintenanceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    ClientRepository clientRepository;
    PizzaRepository pizzaRepository;

    @Override
    public List<ClientDto> findAllClients() {

        List<ClientDto> clients = new ArrayList<ClientDto>();
        Iterable<Client> iterable = clientRepository.findAll();
        iterable.forEach(Client -> {
            ClientDto clientDto = new ClientDto(Client.getClientId(),
                    Client.getName(),
                    Client.getLastName(),
                    Client.getPhone());
            clients.add(clientDto);
        });
        return clients;
    }

    @Override
    public ClientDetailDto findClientById(int id) {

        Optional<Client> optional = clientRepository.findById(id);
        return optional.map(Client -> new ClientDetailDto(Client.getClientId(),
                Client.getName(),
                Client.getLastName(),
                Client.getDni(),
                Client.getAddress(),
                Client.getCity(),
                Client.getPhone(),
                Client.getRegistrationDate())
        ).orElse(null);

    }

    @Override
    public Boolean updateClient(ClientDetailDto clientDetailDto) {
        Optional<Client> optional = clientRepository.findById(clientDetailDto.clientId());
        return optional.map(
                client -> {
                    client.setName(clientDetailDto.name());
                    client.setLastName(clientDetailDto.lastName());
                    client.setDni(clientDetailDto.Dni());
                    client.setAddress(clientDetailDto.address());
                    client.setCity(clientDetailDto.city());
                    client.setPhone(clientDetailDto.phone());
                    client.setRegistrationDate(clientDetailDto.registrationDate());
                    clientRepository.save(client);
                    return true;
                }
        ).orElse(false);
    }

    @Override
    public Boolean deleteClient(int id) {
        Optional<Client> optional = clientRepository.findById(id);
        return optional.map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
    }

    @Override
    public Boolean saveClient(ClientDetailDto clientDetailDto) {

        Client client = new Client();
        client.setName(clientDetailDto.name());
        client.setLastName(clientDetailDto.lastName());
        client.setDni(clientDetailDto.Dni());
        client.setAddress(clientDetailDto.address());
        client.setCity(clientDetailDto.city());
        client.setPhone(clientDetailDto.phone());
        client.setRegistrationDate(clientDetailDto.registrationDate());

        clientRepository.save(client);

        return true;
    }
}

