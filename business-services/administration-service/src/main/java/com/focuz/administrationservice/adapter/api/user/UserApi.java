package com.focuz.administrationservice.adapter.api.user;

import com.focuz.administrationservice.application.dto.request.user.UserRequest;
import com.focuz.administrationservice.application.dto.response.user.UserResponse;
import com.focuz.corestarter.domain.entity.template.response.PageResponse;
import com.focuz.corestarter.domain.entity.template.response.ValueResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/v1/users")
public interface UserApi {
    @PostMapping
    ValueResponse<?> create(@RequestBody @Validated UserRequest request);

    @GetMapping("/{userId}")
    ValueResponse<UserResponse> getDetail(@PathVariable Long userId);

    @GetMapping
    PageResponse<UserResponse> getPage(
            @RequestParam(required = false, value = "search", defaultValue = "")
            String search,
            @RequestParam(required = false, value = "dobRange", defaultValue = "")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            List<LocalDate> dobRange,
            @RequestParam(required = false, value = "createdAtRange", defaultValue = "")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
           List<LocalDateTime> createdAtRange,
           @RequestParam(required = false, value = "sorter", defaultValue = "")
           String sorter,
            @RequestParam(required = false, value = "current", defaultValue = "0")
           Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "25")
           Integer pageSize
    );

    @PutMapping("/{userId}")
    ValueResponse<?> update(@PathVariable Long userId, @RequestBody @Validated UserRequest request);

    @DeleteMapping("/{userId}")
    ValueResponse<?> remove(@PathVariable Long userId);

    @PatchMapping("/{userId}/active")
    ValueResponse<?> active(@PathVariable Long userId);

    @PatchMapping("/{userId}/inactive")
    ValueResponse<?> inactive(@PathVariable Long userId);
}
