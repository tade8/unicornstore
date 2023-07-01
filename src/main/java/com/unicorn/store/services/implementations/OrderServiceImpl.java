package com.unicorn.store.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.unicorn.store.exceptions.GenericException;
import com.unicorn.store.requests.OrderRequest;
import com.unicorn.store.response.OrderResponse;
import com.unicorn.store.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final String SECRET_KEY = System.getenv("PAYSTACK_SECRET_KEY");

    @Override
    public OrderResponse makePayments(OrderRequest orderRequest) throws JsonProcessingException {
        String url = "https://api.paystack.co/page";
        orderRequest.setRedirectUrl("https://www.google.com");
        orderRequest.setCollectPhone(true);
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();

        String body = mapper.writeValueAsString(orderRequest);

        RequestBody requestBody = RequestBody.create(
                body, MediaType.get("application/json")
        );
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + SECRET_KEY)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                log.info("Data " + responseBody);

                OrderResponse orderResponse = mapper.configure(
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        false).
                        readValue(responseBody, OrderResponse.class);
                orderResponse.setPaymentUrl("https://paystack.com/pay/" +
                        orderResponse.getData().getSlug());
                return orderResponse;
            }
            else {
                throw new GenericException(
                        "HTTP request was not successful. Response code: " +
                                response.code());

            }
        }
        catch (IOException exception) {
            throw new GenericException("Error processing response body: " +
                    exception.getMessage());
        }
    }
}
