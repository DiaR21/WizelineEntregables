package com.wizeline.maven.learningjavamaven.service;

import com.wizeline.maven.learningjavamaven.enumm.Country;
import com.wizeline.maven.learningjavamaven.repository.BankingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankAccountServiceImpl implements BankAccountService{
    @Autowired
    BankingAccountRepository bankAccountRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<BankAccountDTO> getAccounts() {
        List<BankAccountDTO> accountDTOList = new ArrayList<>();
        BankAccountDTO bankAccountOne = buildBankAccount("user3@wizeline.com", true, Country.MX, LocalDateTime.now().minusDays(7));
        accountDTOList.add(bankAccountOne);
        mongoTemplate.save(bankAccountOne);
        BankAccountDTO bankAccountTwo = buildBankAccount("user1@wizeline.com", false, Country.FR, LocalDateTime.now().minusMonths(2));
        accountDTOList.add(bankAccountTwo);
        mongoTemplate.save(bankAccountTwo);
        BankAccountDTO bankAccountThree = buildBankAccount("user2@wizeline.com" ,false, Country.US, LocalDateTime.now().minusYears(4));
        accountDTOList.add(bankAccountThree);
        mongoTemplate.save(bankAccountThree);
        mongoTemplate.findAll(BankAccountDTO.class).stream().map(bankAccountDTO -> bankAccountDTO.getUserName()).forEach(
                (user) -> {
                    LOGGER.info("User stored in bankAccountCollection " + user );
                });
        return accountDTOList;

    }

    @Override
    public void deleteAccounts() {
        bankAccountRepository.deleteAll();
    }

    @Override
    public List<BankAccountDTO> getAccountByUser(String user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(user));
        return mongoTemplate.find(query, BankAccountDTO.class);
    }


    @Override
    public com.wizeline.maven.learningjavamaven.model.BankAccountDTO getAccountDetails(String user, String lastUsage) {
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate usage = LocalDate.parse(lastUsage, dateformatter);
        return buildBankAccount(user, true, Country.MX, usage.atStartOfDay());
    }


    private BankAccountService BankAccountDTO BankAccountService buildBankAccount(String user, boolean isActive, Country country, LocalDateTime lastUsage) {
        BankAccountService  bankAccountDTO = new BankAccountService();
        bankAccountDTO.setAccountNumber(randomAcountNumber());
        bankAccountDTO.setAccountName("Dummy Account ".concat(randomInt()));
        bankAccountDTO.setUser(user);
        bankAccountDTO.setAccountBalance(randomBalance());
        bankAccountDTO.setAccountType(pickRandomAccountType());
        bankAccountDTO.setCountry(getCountry(country));
        bankAccountDTO.getLastUsage();
        bankAccountDTO.setCreationDate(lastUsage);
        bankAccountDTO.setAccountActive(isActive);
        return bankAccountDTO;
    }
}