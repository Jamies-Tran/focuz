package com.focuz.administrationservice.domain.entity.client;

import com.focuz.administrationservice.infrastructure.bootstrap.utils.StringConvertUtils;
import com.focuz.corestarter.infrastructure.bootstrap.utils.SorterUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
        clientCodes = CollectionUtils.isEmpty(clientCodes) ? List.of() : clientCodes;
        statusCodes = CollectionUtils.isEmpty(statusCodes) ? List.of() : statusCodes;
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }
}
