package com.iamkhs.fooddelivery.service.impl;

import com.iamkhs.fooddelivery.entity.Address;
import com.iamkhs.fooddelivery.repository.AddressRepository;
import com.iamkhs.fooddelivery.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public void saveAddress(Address address) {
        this.addressRepository.save(address);
    }
}
