package com.focuz.iscstarter.infrastructure.client.administration;

import com.focuz.corestarter.domain.entity.template.response.ValueResponse;
import com.focuz.iscstarter.infrastructure.client.administration.dto.request.user.UserValidateRequest;
import com.focuz.iscstarter.infrastructure.client.administration.dto.response.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "AdministrationClient",
        url = "${internal.administration.domain}"
)
public interface AdministrationClient {
    @PostMapping("${internal.administration.prefix-service:}${internal.administration.user-validate}")
    ValueResponse<UserResponse> validate(@RequestBody @Validated UserValidateRequest request);
}
