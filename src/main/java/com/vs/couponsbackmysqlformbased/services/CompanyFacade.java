package com.vs.couponsbackmysqlformbased.services;

import com.vs.couponsbackmysqlformbased.AOP.SpecifyException;
import com.vs.couponsbackmysqlformbased.AOP.ValidEntry;
import com.vs.couponsbackmysqlformbased.Exceptions.CouponRESTException;
import com.vs.couponsbackmysqlformbased.Exceptions.CouponRESTExceptionHandler;
import com.vs.couponsbackmysqlformbased.Exceptions.ExpReason;
import com.vs.couponsbackmysqlformbased.beans.Category;
import com.vs.couponsbackmysqlformbased.beans.Company;
import com.vs.couponsbackmysqlformbased.beans.Coupon;
import com.vs.couponsbackmysqlformbased.repositories.CategoryRepository;
import com.vs.couponsbackmysqlformbased.repositories.CompanyRepository;
import com.vs.couponsbackmysqlformbased.repositories.CouponRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Scope("prototype")
@Data
@Lazy
public class CompanyFacade extends Facade{

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;
    private final CategoryRepository categoryRepository;

    @SpecifyException(exception = CouponRESTException.COUPON_ADD)
    public void addCoupon(Company company, @ValidEntry Coupon coupon) throws Exception{
        this.couponRepository.save(coupon);
    }

    @SpecifyException(exception = CouponRESTException.COUPON_UPDATE)
    public void updateCoupon(Company company, @ValidEntry Coupon coupon) throws Exception{
        this.couponRepository.save(coupon);
    }

    @SpecifyException(exception = CouponRESTException.COUPON_DELETE)
    public void deleteCoupon(Company company, int couponID) throws Exception {
        if (!couponRepository.existsByIdAndCompany(couponID, company))
            throw new CouponRESTExceptionHandler(CouponRESTException.COUPON_UPDATE.getFailure(), ExpReason.COUPON_NOT_AVAILABLE);
        this.couponRepository.deleteById(couponID);
    }

    @SpecifyException(exception = CouponRESTException.COUPON_GET)
    public List<Coupon> getAllCoupons() throws Exception {
        return couponRepository.findAll();
    }

    @SpecifyException(exception = CouponRESTException.COUPON_GET)
    public List<Coupon> getCompanyCoupons(Company company) throws Exception {
        return couponRepository.findCouponsByCompany(company);
    }

    @SpecifyException(exception = CouponRESTException.COUPON_GET)
    public List<Coupon> getCompanyCoupons(Company company, Category category) throws Exception {
        return couponRepository.getCouponsByCategoryAndCompany(category, company);
    }

    @SpecifyException(exception = CouponRESTException.COUPON_GET)
    public List<Coupon> getCompanyCoupons(Company company, double maxPrice) throws Exception {
        return couponRepository.getCouponsByCompanyAndPriceGreaterThanEqual(company, maxPrice);
    }

    @SpecifyException(exception = CouponRESTException.COUPON_GET)
    public Coupon getOneCouponByCompany(Company company, int couponID) throws Exception {
        Coupon coupon = couponRepository.getCouponByIdAndCompany(couponID, company);
        if (coupon == null) throw new CouponRESTExceptionHandler(CouponRESTException.COUPON_GET.getFailure(), ExpReason.COUPON_DOESNT_EXIST);
        return coupon;
    }

    @SpecifyException(exception = CouponRESTException.COUPON_GET)
    public Coupon getOneCouponByTitleAndCompany(Company company, String title) throws Exception {
        Coupon coupon = couponRepository.getCouponByTitleAndCompany(title, company);
        if (coupon == null) throw new CouponRESTExceptionHandler(CouponRESTException.COUPON_GET.getFailure(), ExpReason.COUPON_DOESNT_EXIST);
        return coupon;
    }

    @SpecifyException(exception = CouponRESTException.COMPANY_GET)
    public Company getCompanyDetails(Company company) throws Exception {
        Company details = companyRepository.findById(company.getId()).get();
        details.setCoupons(couponRepository.getCouponsByCompany(company));
        return details;
    }

    @SpecifyException(exception = CouponRESTException.CATEGORY_GET)
    public List<Category> getAllCategories() throws Exception {
        return categoryRepository.findAll();
    }

    @SpecifyException(exception = CouponRESTException.CATEGORY_GET)
    public Category getOneCategory(int id) throws Exception{
        return categoryRepository.findById(id).get();
    }
}
