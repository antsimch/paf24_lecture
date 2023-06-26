package sg.edu.nus.iss.paf24_lecture.repo;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sg.edu.nus.iss.paf24_lecture.exception.BankAccountNotFoundException;
import sg.edu.nus.iss.paf24_lecture.model.BankAccount;

@Repository
public class BankAccountRepository {
    
    JdbcTemplate template;

    private final String GET_ACCOUNT_SQL = 
            "select * from bank_account where id = ?";

    private final String WITHDRAW_SQL =
            "update bank_account set balance = balance - ? where id = ?";

    private final String DEPOSIT_SQL =
            "update bank_account set balance = balance + ? where id = ?";
    
    private final String CREATE_ACCOUNT_SQL =
            """
                insert into bank_account
                (full_name, is_blocked, is_active, account_type, balance)
                values (?, ?, ?, ?, ?)
                    """;

    public BankAccountRepository(JdbcTemplate template) {
        this.template = template;
    }

    public BankAccount getBankAccountById(int bankAccountId) {

        List<BankAccount> bankAccounts = template.query(
                GET_ACCOUNT_SQL, 
                BeanPropertyRowMapper
                .newInstance(BankAccount.class), 
                bankAccountId);
        
        if (bankAccounts.isEmpty()) {
            throw new BankAccountNotFoundException(
                    "Account does not exists");
        }

        return bankAccounts.get(0);        
    }

    public Boolean withdrawAmount(float withdrawAmount, 
            int bankAccountId) {

        Integer withdrawSuccessful = template.update(
                WITHDRAW_SQL, 
                withdrawAmount, 
                bankAccountId);

        return withdrawSuccessful > 0 ? true : false;
    }

    public Boolean depositAmount(float depositAmount, 
            int bankAccountId) {

        Integer depositSuccessful = template.update(
                DEPOSIT_SQL, 
                depositAmount, 
                bankAccountId);

        return depositSuccessful > 0 ? true : false;
    }

    public Boolean createBankAccount(BankAccount bankAccount) {

        Integer bAccountCreated = template.update(
                CREATE_ACCOUNT_SQL, 
                bankAccount.getFullName(), 
                bankAccount.getIsBlocked(), 
                bankAccount.getIsActive(), 
                bankAccount.getAccountType(), 
                bankAccount.getBalance());

        return bAccountCreated > 0 ? true : false;
    }
}
