package com.maghrebia.Contract;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/contracts")
@Slf4j
public class ContractController {

    @Autowired
    private ContractService contractService;

    @Autowired
    private EmailService emailService;

    // ✅ Basculer le statut
    @PutMapping("/{id}/toggle-status")
    public ResponseEntity<String> toggleContractStatus(@PathVariable Long id) {
        Optional<Contract> contractOpt = contractService.getContractById(id);
        if (contractOpt.isPresent()) {
            Contract contract = contractOpt.get();
            String newStatus = contract.getStatut().equalsIgnoreCase("valide") ? "non valide" : "valide";
            contract.setStatut(newStatus);
            contractService.updateContract(id, contract);
            emailService.sendContractStatusEmail(newStatus, "kassem.benhenda@esprit.tn");
            return ResponseEntity.ok("✅ Statut du contrat mis à jour et email envoyé.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Contrat non trouvé");
        }
    }

    // ✅ Ajouter un contrat
    @PostMapping
    public Contract addContract(@RequestBody Contract contract) {
        return contractService.addContract(contract);
    }

    // ✅ Lister tous les contrats
    @GetMapping
    public ResponseEntity<List<Contract>> getAllContracts() {
        List<Contract> contracts = contractService.getAllContracts();
        return ResponseEntity.ok(contracts);
    }

    // ✅ Chercher un contrat
   /* @GetMapping("/search")
    public ResponseEntity<List<Contract>> searchContracts(@RequestParam String keyword) {
        List<Contract> results = contractService.searchContracts(keyword);
        return ResponseEntity.ok(results);
    }*/

    // ✅ Lire par ID
    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable Long id) {
        Optional<Contract> contract = contractService.getContractById(id);
        return contract.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Modifier un contrat
    @PutMapping("/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable Long id, @RequestBody Contract contractUpdates) {
        Contract updatedContract = contractService.updateContract(id, contractUpdates);
        return ResponseEntity.ok(updatedContract);
    }

    // ✅ Supprimer un contrat
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
        return ResponseEntity.noContent().build();
    }
}
