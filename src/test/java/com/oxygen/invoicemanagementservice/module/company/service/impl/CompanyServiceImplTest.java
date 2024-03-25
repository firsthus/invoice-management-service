package com.oxygen.invoicemanagementservice.module.company.service.impl;

import com.oxygen.invoicemanagementservice.module.authentication.password.entity.UserPassword;
import com.oxygen.invoicemanagementservice.module.authentication.password.enums.PasswordStatus;
import com.oxygen.invoicemanagementservice.module.authentication.user.entity.User;
import com.oxygen.invoicemanagementservice.module.authentication.user.pojo.request.CreateUserRequest;
import com.oxygen.invoicemanagementservice.module.authentication.user.service.UserService;
import com.oxygen.invoicemanagementservice.module.company.entity.Company;
import com.oxygen.invoicemanagementservice.module.company.pojo.request.CreateCompanyRequest;
import com.oxygen.invoicemanagementservice.module.company.pojo.response.CompanyResponse;
import com.oxygen.invoicemanagementservice.module.company.reposervice.CompanyRepositoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CompanyServiceImpl.class})
class CompanyServiceImplTest {


    @MockBean
    private CompanyRepositoryService companyRepositoryService;

    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    @MockBean
    private UserService userService;




    @Test
    void testCreateCompany() {
        // Arrange
        doNothing().when(companyRepositoryService).saveCompany(Mockito.<Company>any());

        UserPassword userPassword = new UserPassword();
        userPassword.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userPassword.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        userPassword.setDateAdded(LocalDate.of(1970, 1, 1).atStartOfDay());
        userPassword.setId(1L);
        userPassword.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userPassword.setLastModifiedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        userPassword.setPassword("iloveyou");
        userPassword.setPasswordStatus(PasswordStatus.ACTIVE);
        userPassword.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        userPassword.setVersion(1);

        User user = new User();
        user.setAuthorities(new HashSet<>());
        user.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        user.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmailAddress("42 Main St");
        user.setId(1L);
        user.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        user.setLastModifiedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setRoles(new HashSet<>());
        user.setUserPassword(userPassword);
        user.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        user.setVersion(1);
        when(userService.createCompanyUser(Mockito.<CreateUserRequest>any())).thenReturn(user);

        CreateCompanyRequest request = new CreateCompanyRequest();
        request.setAddress("42 Main St");
        request.setDescription("The characteristics of someone or something");
        request.setEmailAddress("42 Main St");
        request.setLogoUrl("https://example.org/example");
        request.setName("Name");
        request.setPassword("AZAZ".toCharArray());
        request.setPhone("6625550144");

        // Act
        companyServiceImpl.createCompany(request);

        // Assert
        verify(userService).createCompanyUser(Mockito.any());
        verify(companyRepositoryService).saveCompany(Mockito.any());
    }



    @Test
    void testGetCompanyByUuid() {
        // Arrange
        UserPassword userPassword = new UserPassword();
        userPassword.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        userPassword.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        userPassword.setDateAdded(LocalDate.of(1970, 1, 1).atStartOfDay());
        userPassword.setId(1L);
        userPassword.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        userPassword.setLastModifiedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        userPassword.setPassword("iloveyou");
        userPassword.setPasswordStatus(PasswordStatus.ACTIVE);
        userPassword.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        userPassword.setVersion(1);

        User auth = new User();
        auth.setAuthorities(new HashSet<>());
        auth.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        auth.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        auth.setEmailAddress("42 Main St");
        auth.setId(1L);
        auth.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        auth.setLastModifiedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        auth.setRoles(new HashSet<>());
        auth.setUserPassword(userPassword);
        auth.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        auth.setVersion(1);

        Company company = new Company();
        company.setAddress("42 Main St");
        company.setAuth(auth);
        company.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        company.setCreatedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        company.setDescription("The characteristics of someone or something");
        company.setEmail("jane.doe@example.org");
        company.setId(1L);
        company.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        company.setLastModifiedOn(LocalDate.of(1970, 1, 1).atStartOfDay());
        company.setLogo("Logo");
        company.setName("Name");
        company.setPhone("6625550144");
        company.setUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        company.setVersion(1);
        when(companyRepositoryService.getCompanyByUuid(Mockito.<String>any())).thenReturn(company);

        // Act
        CompanyResponse actualUserByUuid = companyServiceImpl.getCompanyByUuid("01234567-89AB-CDEF-FEDC-BA9876543210");

        // Assert
        verify(companyRepositoryService).getCompanyByUuid("01234567-89AB-CDEF-FEDC-BA9876543210");
        assertEquals("01234567-89AB-CDEF-FEDC-BA9876543210", actualUserByUuid.getUuid());
        assertEquals("42 Main St", actualUserByUuid.getAddress());
        assertEquals("6625550144", actualUserByUuid.getPhone());
        assertEquals("Logo", actualUserByUuid.getLogo());
        assertEquals("Name", actualUserByUuid.getName());
        assertEquals("The characteristics of someone or something", actualUserByUuid.getDescription());
        assertEquals("jane.doe@example.org", actualUserByUuid.getEmail());
    }


    @Test
    void testCreateCompanyValidRequestPersistCompanyObjectToDatabase() {
        // Arrange
        CreateCompanyRequest request = new CreateCompanyRequest();
        request.setName("Test Company");
        request.setAddress("123 Test Street");
        request.setPhone("1234567890");
        request.setEmailAddress("test@test.com");
        request.setPassword("password".toCharArray());
        request.setLogoUrl("https://example.com/logo");

        User user = new User();
        Mockito.when(userService.createCompanyUser(Mockito.any(CreateUserRequest.class))).thenReturn(user);

        // Act
        companyServiceImpl.createCompany(request);

        // Assert
        Mockito.verify(companyRepositoryService, Mockito.atLeastOnce()).saveCompany(Mockito.any(Company.class));
    }

}
