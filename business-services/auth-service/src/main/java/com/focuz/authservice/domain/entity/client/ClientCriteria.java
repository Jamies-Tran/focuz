package com.focuz.authservice.domain.entity.client;

import com.focuz.corestarter.infrastructure.bootstrap.utils.SorterUtils;
import com.focuz.corestarter.infrastructure.bootstrap.utils.StringConvertUtils;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ClientCriteria(
        String search,
        List<String> clientCodes,
        List<String> statusCodes,
        String sorter,
        Integer current,
        Integer pageSize
) {
    public ClientCriteria {
        search = StringConvertUtils.normalizeWhiteSpace(search);
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }
}
