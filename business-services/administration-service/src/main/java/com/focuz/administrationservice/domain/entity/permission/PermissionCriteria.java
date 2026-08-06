package com.focuz.administrationservice.domain.entity.permission;

import com.focuz.administrationservice.infrastructure.bootstrap.utils.StringConvertUtils;
import com.focuz.corestarter.infrastructure.bootstrap.utils.SorterUtils;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Builder
public record PermissionCriteria(
        String search,
        List<String> permissionCodes,
        List<String> statusCodes,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public PermissionCriteria {
        search = StringConvertUtils.normalizeWhiteSpace(search);
        permissionCodes = CollectionUtils.isEmpty(permissionCodes) ? List.of() : permissionCodes;
        statusCodes = CollectionUtils.isEmpty(statusCodes) ? List.of() : statusCodes;
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }
}
