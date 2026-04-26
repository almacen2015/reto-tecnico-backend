package backend.msaccount.infraestructure.persistence;

import backend.msaccount.domain.model.Account;
import backend.msaccount.domain.repository.AccountRepository;
import backend.msaccount.infraestructure.entity.AccountEntity;
import backend.msaccount.infraestructure.mapper.AccountMapper;
import backend.msaccount.infraestructure.repository.AccountJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaRepository accountJpaRepository;

    public AccountRepositoryImpl(AccountJpaRepository accountJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
    }

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = AccountMapper.toEntity(account);
        return AccountMapper.toDomain(accountJpaRepository.save(accountEntity));
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountJpaRepository.findById(id)
                .map(AccountMapper::toDomain);
    }

    @Override
    public List<Account> findAll() {
        return accountJpaRepository.findAll()
                .stream()
                .map(AccountMapper::toDomain)
                .toList();
    }

    @Override
    public void updateStatus(Long id, Boolean status) {
        accountJpaRepository.findById(id)
                .ifPresent(entity -> {
                    entity.setStatus(status);
                    accountJpaRepository.save(entity);
                });
    }
}
