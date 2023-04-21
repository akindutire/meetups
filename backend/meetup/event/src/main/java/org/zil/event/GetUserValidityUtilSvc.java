package org.zil.event;

import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.zil.event.dto.user.XValidUserRes;

@Component
@RequiredArgsConstructor
public class GetUserValidityUtilSvc {

    private final RestTemplate mrest;
    @Value("${gateway.base-url}")
    public String GatewayBaseUri;

    @Retry(name = "getUserValidConfirmation")
    public XValidUserRes getValidUserConfirmation (String currentUserEmail, String authToken) {
        System.out.println("Trying...");

        try {
            XValidUserRes res = null;
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + authToken);
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            res = mrest.exchange(
                    GatewayBaseUri + "/api/v1/user/isvalid/email/{userId}",
                    HttpMethod.GET,
                    requestEntity,
                    XValidUserRes.class,
                    currentUserEmail).getBody();

            return res;
        } catch (Exception e) {
            throw new IllegalStateException("Unable to continue with request, owning user can't be verified");
        }
    }
}
