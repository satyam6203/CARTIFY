package com.satyam.Ecommerce.Service.impl;

import com.satyam.Ecommerce.Config.JwtProvider;
import com.satyam.Ecommerce.Domain.Address;
import com.satyam.Ecommerce.Domain.Seller;
import com.satyam.Ecommerce.Exceptions.SellerException;
import com.satyam.Ecommerce.Repo.AddressRepo;
import com.satyam.Ecommerce.Repo.SellerRepo;
import com.satyam.Ecommerce.Service.SellerService;
import com.satyam.Ecommerce.constants.AccountStatus;
import com.satyam.Ecommerce.constants.USER_ROLE;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepo sellerRepo;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepo addressRepo;

    @Override
    public Seller getSellerProfile(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromToken(jwt);

        return this.getSellerByEmail(email);
    }

    @Override
    public Seller createSeller(Seller seller) throws Exception {

        Seller sellerExist = sellerRepo.findByEmail(seller.getEmail());
        if (sellerExist != null) {
            throw new Exception("Seller already exists with this email, use another account.");
        }

        Address savedAddress = addressRepo.save(seller.getPickupAddress());

        Seller newSeller = new Seller();

        newSeller.setEmail(seller.getEmail());
        newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
        newSeller.setSellerName(seller.getSellerName());
        newSeller.setPickupAddress(savedAddress);
        newSeller.setGSTIN(seller.getGSTIN());
        newSeller.setRole(USER_ROLE.ROLE_SELLER);
        newSeller.setMobile(seller.getMobile());
        newSeller.setBankDetails(seller.getBankDetails());
        newSeller.setBusinessDetails(seller.getBusinessDetails());
        newSeller.setAccountStatus(AccountStatus.PENDING_VERIFICATION);
        newSeller.setEmailVerified(false);

        return sellerRepo.save(newSeller);
    }

    @Override
    public Seller getSellerByID(Long id) throws SellerException {
        return sellerRepo.findById(id)
                .orElseThrow(() -> new SellerException("Seller not by this ID ->" + id));
    }

    @Override
    public Seller getSellerByEmail(String email) throws Exception {
        Seller seller = sellerRepo.findByEmail(email);
        if (seller == null) {
            throw new Exception("Seller not found by this email ->" + email);
        }
        return seller;
    }

    @Override
    public List<Seller> getAllSellers(AccountStatus status) {
        return sellerRepo.findByAccountStatus(status);
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws Exception {

        Seller existSeller = this.getSellerByID(id);

        if (seller.getSellerName() != null) {
            existSeller.setSellerName(seller.getSellerName());
        }

        if (seller.getMobile() != null) {
            existSeller.setMobile(seller.getMobile());
        }

        if (seller.getEmail() != null) {
            existSeller.setEmail(seller.getMobile());
        }

        if (seller.getBusinessDetails() != null &&
                seller.getBusinessDetails().getBusinessName() != null) {

            existSeller.getBusinessDetails().setBusinessName(
                    seller.getBusinessDetails().getBusinessName());
        }

        if (seller.getBankDetails() != null
                && seller.getBankDetails().getAccountHolder() != null
                && seller.getBankDetails().getAccountNumber() != null
                && seller.getBankDetails().getIfscCode() != null) {
            existSeller.getBankDetails().setAccountHolder(
                    seller.getBankDetails().getAccountHolder());

            existSeller.getBankDetails().setIfscCode(
                    seller.getBankDetails().getIfscCode());

            existSeller.getBankDetails().setAccountNumber(
                    seller.getBankDetails().getAccountNumber());
        }

        if (seller.getPickupAddress() != null
                && seller.getPickupAddress().getAddress() != null
                && seller.getPickupAddress().getCity() != null
                && seller.getPickupAddress().getState() != null
                && seller.getPickupAddress().getPhoneNumber() != null) {
            existSeller.getPickupAddress().setAddress(
                    seller.getPickupAddress().getAddress());
            existSeller.getPickupAddress().setPhoneNumber(
                    seller.getPickupAddress().getPhoneNumber());
            existSeller.getPickupAddress().setCity(
                    seller.getPickupAddress().getCity());
            existSeller.getPickupAddress().setState(
                    seller.getPickupAddress().getState());

        }

        if (seller.getGSTIN() != null) {
            existSeller.setGSTIN(seller.getGSTIN());
        }
        return sellerRepo.save(existSeller);
    }

    @Override
    public void deleteSeller(Long id) throws Exception {
        Seller existById = getSellerByID(id);

        if (existById == null) {
            throw new Exception("User not exist By this id ");
        }
        sellerRepo.deleteById(id);
    }

    @Override
    public Seller verifyEmail(String email, String otp) throws Exception {
        Seller seller = getSellerByEmail(email);
        seller.setEmailVerified(true);
        return sellerRepo.save(seller);
    }

    @Override
    public Seller updateSellerAccountStatus(Long sellerID, AccountStatus status) throws Exception {
        Seller seller = getSellerByID(sellerID);
        seller.setAccountStatus(status);
        return sellerRepo.save(seller);
    }
}
