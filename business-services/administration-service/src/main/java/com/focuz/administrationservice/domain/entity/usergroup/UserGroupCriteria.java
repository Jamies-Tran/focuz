package com.focuz.administrationservice.domain.entity.usergroup;

import com.focuz.administrationservice.infrastructure.bootstrap.utils.StringConvertUtils;
import com.focuz.corestarter.infrastructure.bootstrap.utils.SorterUtils;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;

@Builder
public record UserGroupCriteria(
        Long authGroupId,
        String search,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public UserGroupCriteria {
        search = StringConvertUtils.normalizeWhiteSpace(search);
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }
}
