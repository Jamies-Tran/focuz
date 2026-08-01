package com.focuz.administrationservice.domain.entity.user;

import com.focuz.administrationservice.infrastructure.bootstrap.utils.StringConvertUtils;
import com.focuz.corestarter.infrastructure.bootstrap.utils.SorterUtils;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UserCriteria(
        String search,
        List<LocalDate> dobRange,
        List<LocalDateTime> createdAtRange,
        Integer current,
        Integer pageSize,
        String sorter
) {
    public UserCriteria {
        search = StringConvertUtils.normalizeWhiteSpace(search);
        dobRange = CollectionUtils.isEmpty(dobRange) ? List.of() : dobRange;
        createdAtRange = CollectionUtils.isEmpty(createdAtRange) ? List.of() : createdAtRange;
    }

    public LocalDate getFirstIndexDobRange() {
        if(CollectionUtils.isEmpty(dobRange)) {
            return null;
        }
        return dobRange.getFirst();
    }

    public LocalDate getLastIndexDobRange() {
        if(CollectionUtils.isEmpty(dobRange)) {
            return null;
        }
        return dobRange.getLast();
    }

    public LocalDateTime getFirstIndexCreatedAtRange() {
        if(CollectionUtils.isEmpty(createdAtRange)) {
            return null;
        }
        return createdAtRange.getFirst();
    }

    public LocalDateTime getLastIndexCreatedAtRange() {
        if(CollectionUtils.isEmpty(createdAtRange)) {
            return null;
        }
        return createdAtRange.getLast();
    }

    public PageRequest pageRequest() {
        return PageRequest.of(current, pageSize, SorterUtils.handleSorter(sorter));
    }
}
