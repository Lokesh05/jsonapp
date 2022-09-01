package com.jsonapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsonApplication.class, args);
    }

    /*@Bean
    CommandLineRunner runner(OrderService orderService){
        return args -> {
            // read JSON and load json
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<OrderRequest> typeReference = new TypeReference<OrderRequest>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/order.json");
            try {
                OrderRequest orderRequest = mapper.readValue(inputStream,typeReference);
                orderService.insertOrderDetails(orderRequest);
                System.out.println("Order Details Saved!");
            } catch (IOException e){
                System.out.println("Unable to save Order Details: " + e.getMessage());
            }
        };
    }*/
}
