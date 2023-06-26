package sg.edu.nus.iss.paf24_lecture.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.paf24_lecture.exception.AccountBlockedAndDisabledException;
import sg.edu.nus.iss.paf24_lecture.exception.AmountNotSufficientException;
import sg.edu.nus.iss.paf24_lecture.model.BankAccount;
import sg.edu.nus.iss.paf24_lecture.repo.BankAccountRepository;

@Service
public class BankAccountService {
    
    BankAccountRepository bankAccountRepo;

    public BankAccountService(BankAccountRepository bankAccountRepo) {
        this.bankAccountRepo = bankAccountRepo;
    }

    public BankAccount getBankAccountById(int bankAccountId) {
        return bankAccountRepo.getBankAccountById(bankAccountId);
    }

    public Boolean withdrawAmount(float withdrawAmount, int bankAccountId) {
        return bankAccountRepo.withdrawAmount(withdrawAmount, bankAccountId);
    }

    public Boolean depositAmount(float depositAmount, int bankAccountId) {
        return bankAccountRepo.depositAmount(depositAmount, bankAccountId);
    }

    public Boolean createBankAccount(BankAccount bankAccount) {
        return bankAccountRepo.createBankAccount(bankAccount);
    }

    @Transactional
    public Boolean transferAmount(int withdrawnAccountId, 
            int depositedAccountId, 
            float transferAmount) {

        if (getBankAccountById(depositedAccountId) == null
                || getBankAccountById(withdrawnAccountId) == null) {

            return false;
        }

        if (!getBankAccountById(withdrawnAccountId).getIsActive() || 
                getBankAccountById(withdrawnAccountId).getIsBlocked()) {

            throw new AccountBlockedAndDisabledException(
                    "Receiver account is blocked or inactive");
        }

        if (!getBankAccountById(depositedAccountId).getIsActive() || 
                getBankAccountById(depositedAccountId).getIsBlocked()) {

            throw new AccountBlockedAndDisabledException(
                    "Receiver account is blocked or inactive");
        }

        if (transferAmount > getBankAccountById(withdrawnAccountId).getBalance()) {
            throw new AmountNotSufficientException("Amount is insufficient");
        }

        withdrawAmount(transferAmount, withdrawnAccountId);
        depositAmount(transferAmount, depositedAccountId);

        return true;
    }
}
