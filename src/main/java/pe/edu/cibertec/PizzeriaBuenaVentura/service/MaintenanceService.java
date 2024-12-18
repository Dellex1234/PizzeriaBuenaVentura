package pe.edu.cibertec.PizzeriaBuenaVentura.service;

import pe.edu.cibertec.PizzeriaBuenaVentura.dto.ClientDetailDto;
import pe.edu.cibertec.PizzeriaBuenaVentura.dto.ClientDto;

import java.util.List;

public interface MaintenanceService {

    List<ClientDto> findAllClients();

    ClientDetailDto findClientById(int id);

    Boolean updateClient(ClientDetailDto filmDetailDto);

    Boolean deleteClient(int id);

    Boolean saveClient(ClientDetailDto filmDetailDto);
}
