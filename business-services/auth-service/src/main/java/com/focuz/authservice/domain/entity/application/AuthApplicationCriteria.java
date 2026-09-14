package com.focuz.authservice.domain.entity.application;

import com.focuz.corestarter.infrastructure.bootstrap.utils.SorterUtils;
import com.focuz.corestarter.infrastructure.bootstrap.utils.StringConvertUtils;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Builder
public record AuthApplicationCriteria(
        String search,
        List<String> authApplicationCodes,
        List<String> statusCodes,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public AuthApplicationCriteria {
        search = StringConvertUtils.normalizeWhiteSpace(search);
        authApplicationCodes = CollectionUtils.isEmpty(authApplicationCodes) ? List.of() : authApplicationCodes;
        statusCodes = CollectionUtils.isEmpty(statusCodes) ? List.of() : statusCodes;
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }
}
