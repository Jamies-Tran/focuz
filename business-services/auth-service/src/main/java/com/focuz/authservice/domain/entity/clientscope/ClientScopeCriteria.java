package com.focuz.authservice.domain.entity.clientscope;

import com.focuz.corestarter.domain.entity.template.response.PageResponse;
import com.focuz.corestarter.infrastructure.bootstrap.utils.SorterUtils;
import com.focuz.corestarter.infrastructure.bootstrap.utils.StringConvertUtils;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Builder
public record ClientScopeCriteria(
        Long clientId,
        String search,
        List<String> scopeCodes,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public ClientScopeCriteria {
        search = StringConvertUtils.normalizeWhiteSpace(search);
        scopeCodes = CollectionUtils.isEmpty(scopeCodes) ? List.of() : scopeCodes;
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }
}
