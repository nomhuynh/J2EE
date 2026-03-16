package com.example.demo.config;

import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AccountRepository accountRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        Role roleAdmin = createRoleIfMissing("ROLE_ADMIN");
        Role roleUser = createRoleIfMissing("ROLE_USER");

        createOrUpdateAccount("admin", "admin123456", roleAdmin);
        createOrUpdateAccount("user1", "user123456", roleUser);
    }

    private Role createRoleIfMissing(String name) {
        return roleRepository.findByName(name).orElseGet(() -> {
            Role role = new Role();
            role.setName(name);
            return roleRepository.save(role);
        });
    }

    @Transactional
    private void createOrUpdateAccount(String loginName, String rawPassword, Role role) {
        Account account = accountRepository.findByLoginName(loginName).orElseGet(Account::new);
        account.setLoginName(loginName);
        account.setPassword(passwordEncoder.encode(rawPassword));
        if (account.getRoles().stream().noneMatch(r -> r.getName().equals(role.getName()))) {
            account.getRoles().add(role);
        }
        accountRepository.save(account);
    }
}
