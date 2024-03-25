package com.oxygen.invoicemanagementservice.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PageUtils {

    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_SIZE = 10;
    public static final String DEFAULT_SORT_BY = "id";
    public static final Sort.Direction DEFAULT_DIRECTION = Sort.Direction.DESC;

}
