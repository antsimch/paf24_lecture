package sg.edu.nus.iss.paf24_lecture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf24_lecture.exception.BankAccountNotFoundException;
import sg.edu.nus.iss.paf24_lecture.model.BankAccount;
import sg.edu.nus.iss.paf24_lecture.service.BankAccountService;

@RestController
@RequestMapping(path = "/api/bankaccounts")
public class BankAccountController {
    
    BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public ResponseEntity<Boolean> createBankAccount(
            @RequestBody BankAccount bankAccount) {
    
        Boolean bAccountCreated = 
                bankAccountService.createBankAccount(bankAccount);

        if (bAccountCreated) {
            return ResponseEntity.ok().body(bAccountCreated);
        } else {
            return ResponseEntity.internalServerError()
                    .body(bAccountCreated);
        }
    }

    @GetMapping(path = "/{accountId}")
    public ResponseEntity<BankAccount> getAccountById(
            @PathVariable("accountId") Integer id) {

        BankAccount bankAccount = 
                bankAccountService.getBankAccountById(id);

        if (bankAccount == null) {
            throw new BankAccountNotFoundException(
                    "Bank account not found");
        }

        return new ResponseEntity<BankAccount>(bankAccount, 
                HttpStatus.OK);
    }

    @PostMapping(path = "/transfer/{withdrawnAccountId}/{depositedAccountId}/{amount}")
    public ResponseEntity<Boolean> transfer(
            @PathVariable("withdrawnAccountId") int withdrawnAccountId, 
            @PathVariable("depositedAccountId") int depositedAccountId, 
            @PathVariable("amount") float amount) {

        Boolean transferSuccessful = bankAccountService
                .transferAmount(withdrawnAccountId, 
                depositedAccountId, 
                amount);

        return ResponseEntity.ok().body(transferSuccessful);
    }
}
