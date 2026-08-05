package com.focuz.administrationservice.domain.entity.authgroup;

import com.focuz.administrationservice.infrastructure.bootstrap.utils.StringConvertUtils;
import com.focuz.corestarter.infrastructure.bootstrap.utils.SorterUtils;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Builder
public record AuthGroupCriteria(
        String search,
        List<String> authGroupCodes,
        List<String> statusCodes,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public AuthGroupCriteria {
        search = StringConvertUtils.normalizeWhiteSpace(search);
        authGroupCodes = CollectionUtils.isEmpty(authGroupCodes) ? List.of() : authGroupCodes;
        statusCodes = CollectionUtils.isEmpty(statusCodes) ? List.of() : statusCodes;
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }
}
