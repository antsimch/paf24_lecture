package sg.edu.nus.iss.paf24_lecture.repo;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        return template.queryForObject(
                GET_ACCOUNT_SQL, 
                BeanPropertyRowMapper
                .newInstance(BankAccount.class), 
                bankAccountId);
    }

    public Integer withdrawAmount(int amount, int bankAccountId) {
        return template.update(WITHDRAW_SQL, amount, bankAccountId);
    }

    public Integer depositAmount(int amount, int bankAccountId) {
        return template.update(DEPOSIT_SQL, amount, bankAccountId);
    }
}
