package com.vs.couponsbackmysqlformbased.security;

        import com.vs.couponsbackmysqlformbased.beans.Company;
        import com.vs.couponsbackmysqlformbased.beans.Customer;
        import com.vs.couponsbackmysqlformbased.repositories.CompanyRepository;
        import com.vs.couponsbackmysqlformbased.repositories.CustomerRepository;
        import lombok.Data;
        import lombok.RequiredArgsConstructor;
        import org.springframework.security.core.userdetails.User;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.stereotype.Service;

        import static com.vs.couponsbackmysqlformbased.enums.ClientType.*;

@Service
@RequiredArgsConstructor
@Data
public class UserAuthenticationService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = this.customerRepository.findCustomerByEmail(email);
        if (customer != null) {
            UserDetails newUser = User.withUsername(customer.getEmail())
                    .password(customer.getPassword())
                    .roles(CUSTOMER.toString())
                    .build();
            return newUser;
        }
        Company company = this.companyRepository.findCompanyByEmail(email);
        if (company != null) {
            UserDetails newUser = User.withUsername(company.getEmail())
                    .password(company.getPassword())
                    .roles(COMPANY.toString())
                    .build();
            return newUser;
        }
        throw new UsernameNotFoundException("user not found");
    }
}
