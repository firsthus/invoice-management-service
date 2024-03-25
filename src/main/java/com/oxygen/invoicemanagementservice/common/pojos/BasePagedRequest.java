package com.oxygen.invoicemanagementservice.common.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oxygen.invoicemanagementservice.common.advice.enums.ResponseCode;
import com.oxygen.invoicemanagementservice.common.entities.BaseEntity;
import com.oxygen.invoicemanagementservice.common.exception.OxygenRuntimeException;
import com.oxygen.invoicemanagementservice.common.utils.PageUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Slf4j
public class BasePagedRequest {

    @PositiveOrZero
    private int pageNumber = PageUtils.DEFAULT_PAGE_NUMBER;

    @Positive
    private int pagSize = PageUtils.DEFAULT_SIZE;

    private Sort.Direction sortDirection = PageUtils.DEFAULT_DIRECTION;

    private List<@NotBlank String> sortBy = Collections.singletonList(PageUtils.DEFAULT_SORT_BY);



    @JsonIgnore
    public Predicate resolvePredicate(){
        return Expressions.TRUE;
    }



    @JsonIgnore
    public Pageable getPageable() {
        List<Sort.Order> sortOrders = generateSortOrder();
        return PageRequest.of(pageNumber, pagSize, Sort.by(sortOrders));
    }



    private List<Sort.Order> generateSortOrder() {

        Class<?> clazz = getEntityClass();

        Set<String> validSortFields = getAllFields(clazz);

        List<Sort.Order> sortOrders = new ArrayList<>();

        for (String sortField : sortBy) {
            if (validSortFields.contains(sortField)) {
                sortOrders.add(new Sort.Order(sortDirection, sortField));
            } else {

                log.error("Invalid sort field: {} for class: {}",
                        sortField, clazz.getSimpleName());

                throw new OxygenRuntimeException(ResponseCode.INVALID_ARGUMENT,
                        "Invalid sort field: " + sortField);
            }
        }

        return sortOrders;
    }



    @JsonIgnore
    public Class<? extends BaseEntity> getEntityClass(){
        return BaseEntity.class;
    }



    private Set<String> getAllFields(Class<?> clazz) {

        Set<String> allFields = new HashSet<>();

        while (clazz != null && clazz != Object.class) {
            Set<String> baseFields = getSimpleFields(clazz);
            allFields.addAll(baseFields);
            clazz = clazz.getSuperclass();
        }

        return allFields;
    }



    private static Set<String> getSimpleFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> BeanUtils.isSimpleValueType(field.getType()))
                .map(Field::getName)
                .collect(Collectors.toSet());
    }
}