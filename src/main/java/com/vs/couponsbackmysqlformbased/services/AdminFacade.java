package com.vs.couponsbackmysqlformbased.services;

import com.vs.couponsbackmysqlformbased.AOP.SpecifyException;
import com.vs.couponsbackmysqlformbased.AOP.ValidEntry;
import com.vs.couponsbackmysqlformbased.Exceptions.CouponRESTException;
import com.vs.couponsbackmysqlformbased.Exceptions.CouponRESTExceptionHandler;
import com.vs.couponsbackmysqlformbased.Exceptions.ExpReason;
import com.vs.couponsbackmysqlformbased.beans.Category;
import com.vs.couponsbackmysqlformbased.beans.Company;
import com.vs.couponsbackmysqlformbased.beans.Customer;
import com.vs.couponsbackmysqlformbased.repositories.CategoryRepository;
import com.vs.couponsbackmysqlformbased.repositories.CompanyRepository;
import com.vs.couponsbackmysqlformbased.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Scope("prototype")
@Lazy
public class AdminFacade extends Facade{

    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final CategoryRepository categoryRepository;

    @SpecifyException(exception = CouponRESTException.COMPANY_ADD)
    public void addCompany(@ValidEntry Company company) throws Exception {
        companyRepository.save(company);
    }

    @SpecifyException(exception = CouponRESTException.COMPANY_UPDATE)
    public void updateCompany(@ValidEntry Company company) throws Exception {
        companyRepository.save(company);
    }

    @SpecifyException(exception = CouponRESTException.COMPANY_DELETE)
    public void deleteCompany(int companyID) throws Exception {
        companyRepository.deleteById(companyID);
    }

    @SpecifyException(exception = CouponRESTException.COMPANY_GET)
    public List<Company> getAllCompanies() throws Exception {
        List<Company> list = companyRepository.findAll();
        if (list.isEmpty()) throw new CouponRESTExceptionHandler(CouponRESTException.COMPANY_GET.getFailure(), ExpReason.COMPANIES_WERENT_FOUND);
        return list;
    }

    @SpecifyException(exception = CouponRESTException.COMPANY_GET)
    public Company getOneCompany(int companyID) throws Exception {
        return companyRepository.findById(companyID).get();
    }

    @SpecifyException(exception = CouponRESTException.CUSTOMER_ADD)
    public void addCustomer(@ValidEntry Customer customer) throws Exception {
        customerRepository.save(customer);
    }

    @SpecifyException(exception = CouponRESTException.CUSTOMER_UPDATE)
    public void updateCustomer(@ValidEntry Customer customer) throws Exception {
        customerRepository.save(customer);
    }

    @SpecifyException(exception = CouponRESTException.CUSTOMER_DELETE)
    public void deleteCustomer(int customerID) throws Exception {
        customerRepository.deleteById(customerID);
    }

    @SpecifyException(exception = CouponRESTException.CUSTOMER_GET)
    public List<Customer> getAllCustomers() throws Exception {
        List<Customer> list = customerRepository.findAll();
        if (list.isEmpty()) throw new CouponRESTExceptionHandler(CouponRESTException.CUSTOMER_GET.getFailure(), ExpReason.CUSTOMERS_WERENT_FOUND);
        return list;
    }

    @SpecifyException(exception = CouponRESTException.CUSTOMER_GET)
    public Customer getOneCustomer(int customerID) throws Exception {
        return customerRepository.findById(customerID).get();
    }

    @SpecifyException(exception = CouponRESTException.CATEGORY_ADD)
    public void addCategory(@ValidEntry Category category) throws Exception {
        categoryRepository.save(category);
    }

    @SpecifyException(exception = CouponRESTException.CATEGORY_GET)
    public List<Category> getAllCategories() throws Exception{
        return categoryRepository.findAll();
    }

    @SpecifyException(exception = CouponRESTException.CATEGORY_GET)
    public Category getOneCategory(int id) throws Exception{
        return categoryRepository.findById(id).get();
    }
}
