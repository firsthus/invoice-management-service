package com.oxygen.invoicemanagementservice.module.authentication.user.reposervice;

import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;
import com.oxygen.invoicemanagementservice.common.exception.BadRequestException;
import com.oxygen.invoicemanagementservice.common.exception.OxygenRuntimeException;
import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import com.oxygen.invoicemanagementservice.module.authentication.user.repository.UserRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRepositoryService {

    private final UserRepository userRepository;

    @Transactional
    public User getUserByUuid(String uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(() -> new BadRequestException("User not found."));
    }

    public User getUserByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new BadRequestException("User not found."));
    }

    public Page<User> searchUsers(Predicate predicate, Pageable pageable) {
        return userRepository.findAll(predicate, pageable);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }



    public void validateUserDoesNotExist(String emailAddress) {
        if (existsByEmailAddress(emailAddress.toLowerCase())) {
            throw new OxygenRuntimeException(ResponseCode.DUPLICATE_RESOURCE, "User already exists.");
        }
    }



    public boolean existsByEmailAddress(String emailAddress) {
        return userRepository.existsByEmailAddress(emailAddress);
    }
}
