package com.focuz.administrationservice.domain.entity.grouppermission;

import com.focuz.administrationservice.infrastructure.bootstrap.utils.StringConvertUtils;
import com.focuz.corestarter.infrastructure.bootstrap.utils.SorterUtils;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Builder
public record GroupPermissionCriteria(
        Long authGroupId,
        String search,
        List<String> permissionCodes,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public GroupPermissionCriteria {
        search = StringConvertUtils.normalizeWhiteSpace(search);
        permissionCodes = CollectionUtils.isEmpty(permissionCodes) ? List.of() : permissionCodes;
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }
}
