package pe.edu.cibertec.PizzeriaBuenaVentura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.PizzeriaBuenaVentura.dto.ClientDetailDto;
import pe.edu.cibertec.PizzeriaBuenaVentura.dto.ClientDto;
import pe.edu.cibertec.PizzeriaBuenaVentura.service.MaintenanceService;

import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

    @GetMapping("/start")
    public String start(Model model) {
        List<ClientDto> clients = maintenanceService.findAllClients();
        model.addAttribute("Clients", clients);
        return "maintenance";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        ClientDetailDto clientDetailDto = maintenanceService.findClientById(id);
        model.addAttribute("client", clientDetailDto);
        return "maintenance_detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        ClientDetailDto clientDetailDto = maintenanceService.findClientById(id);
        model.addAttribute("client", clientDetailDto);
        return "maintenance_edit";
    }

    @PostMapping("/edit-confirm")
    public String editConfirm(@ModelAttribute ClientDetailDto clientDetailDto, Model model) {
        maintenanceService.updateClient(clientDetailDto);
        return "redirect:/maintenance/start";
    }

    @GetMapping("/register")
    public String register(Model model) {
        return "maintenance_register";
    }

    @PostMapping("/register")
    public String registerClient(@ModelAttribute ClientDetailDto clientDetailDto, Model model) {
        Boolean isSaved = maintenanceService.saveClient(clientDetailDto);

        if (isSaved) {
            return "redirect:/maintenance/start";
        } else {
            model.addAttribute("error", "Hubo un error al registrar al cliente.");
            return "maintenance_register";
        }
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable Integer id, Model model) {
        boolean success = maintenanceService.deleteClient(id);
        if (success) {
            return "redirect:/maintenance/start";
        }
        else {
            model.addAttribute("error", "No se pudo eliminar al cliente.");
            return "maintenance";
        }
    }

}
