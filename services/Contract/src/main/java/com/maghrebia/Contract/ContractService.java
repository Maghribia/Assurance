package com.maghrebia.Contract;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@RequiredArgsConstructor

@Service
@Slf4j
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private EmailService emailService;
    public Contract addContract(Contract contract) {
        return contractRepository.save(contract);
    }
    public Contract toggleStatus(Long id) {
        Contract contract = contractRepository.findById(id).orElseThrow(() -> new RuntimeException("Contract not found"));
        contract.setStatut(contract.getStatut().equals("valide") ? "non valide" : "valide");
        return contractRepository.save(contract); // Sauvegarder les modifications dans la base
    }
    // READ (ALL)
    public List<Contract> getAllContracts() {
        log.info("Fetching all contracts");
        return contractRepository.findAll();
    }

    // READ (BY ID)
    public Optional<Contract> getContractById(Long id) {
        log.info("Fetching contract by ID: {}", id);
        return contractRepository.findById(id);
    }


    // UPDATE
    public Contract updateContract(Long id, Contract contractUpdates) {
        log.info("Updating contract with ID: {}", id);
        return contractRepository.findById(id)
                .map(existingContract -> {
                    if (contractUpdates.getClientId() != null) {
                        existingContract.setClientId(contractUpdates.getClientId());
                    }
                    if (contractUpdates.getProduitAssuranceId() > 0) {
                        existingContract.setProduitAssuranceId(contractUpdates.getProduitAssuranceId());
                    }
                    if (contractUpdates.getStatut() != null) {
                        existingContract.setStatut(contractUpdates.getStatut());
                    }
                    if (contractUpdates.getMontantMensuel() > 0) {
                        existingContract.setMontantMensuel(contractUpdates.getMontantMensuel());
                    }
                    if (contractUpdates.getTypeAssurance() != null) {
                        existingContract.setTypeAssurance(contractUpdates.getTypeAssurance());
                    }
                    return contractRepository.save(existingContract);
                })
                .orElseThrow(() -> new RuntimeException("Contract not found with ID: " + id));
    }

    // DELETE
    public void deleteContract(Long id) {
        log.info("Deleting contract with ID: {}", id);
        contractRepository.deleteById(id);
    }
    @Scheduled(fixedRate = 10000) // Toutes les 10 secondes
    public void checkContractExpirations() {
        System.out.println("🔔 Vérification des contrats expirants...");

        LocalDate today = LocalDate.now();
        LocalDate expirationDate = today.plusDays(7);

        List<Contract> expiringContracts = contractRepository.findByDateExpirationBetween(today, expirationDate);

        if (expiringContracts.isEmpty()) {
            System.out.println("✅ Aucun contrat trouvé entre " + today + " et " + expirationDate);
        } else {
            for (Contract contract : expiringContracts) {
                System.out.println("🚨 Contrat expirant trouvé : " + contract);

                // Remplace avec la vraie adresse e-mail du contrat si tu l’as
                String clientEmail = "kassem.benhenda@esprit.tn";

                if (clientEmail != null && !clientEmail.isEmpty()) {
                    emailService.sendExpirationReminder(clientEmail, contract);
                } else {
                    System.err.println("❌ Aucun email trouvé pour le contrat ID : " + contract.getId());
                }
            }
        }
    }
    public Map<String, List<Contract>> getContractsGroupedByMonth() {
        List<Contract> allContracts = contractRepository.findAll();

        return allContracts.stream()
                .filter(c -> c.getDateExpiration() != null)
                .collect(Collectors.groupingBy(contract -> {
                    LocalDate date = contract.getDateExpiration();
                    return date.getYear() + "-" + String.format("%02d", date.getMonthValue()); // Ex: "2025-04"
                }));
    }



}
