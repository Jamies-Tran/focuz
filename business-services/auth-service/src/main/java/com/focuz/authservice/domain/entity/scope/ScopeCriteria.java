package com.focuz.authservice.domain.entity.scope;

import com.focuz.corestarter.infrastructure.bootstrap.utils.SorterUtils;
import com.focuz.corestarter.infrastructure.bootstrap.utils.StringConvertUtils;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Builder
public record ScopeCriteria(
        String search,
        List<String> scopeCodes,
        List<String> statusCodes,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public ScopeCriteria {
        search = StringConvertUtils.normalizeWhiteSpace(search);
        scopeCodes = CollectionUtils.isEmpty(scopeCodes) ? List.of() : scopeCodes;
        statusCodes = CollectionUtils.isEmpty(statusCodes) ? List.of() : statusCodes;
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }
}
