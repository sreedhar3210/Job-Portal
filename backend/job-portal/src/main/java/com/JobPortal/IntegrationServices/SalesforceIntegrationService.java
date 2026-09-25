package com.JobPortal.IntegrationServices;

import com.JobPortal.models.Company;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.HashMap;
import java.util.Map;

@Service
public class SalesforceIntegrationService {

    private final WebClient webClient;

    @Value("${salesforce.client.id}")
    private String clientId;

    @Value("${salesforce.client.secret}")
    private String clientSecret;

    @Value("${salesforce.domain}")
    private String domain;

    public SalesforceIntegrationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    // Helper record to parse the Salesforce Token response
    private record TokenResponse(String access_token, String instance_url) {}

    public String getSalesforceAccessToken() {

        // STEP 1: Get the Access Token (x-www-form-urlencoded)
        String tokenUrl = domain + "/services/oauth2/token";
        
        TokenResponse tokenResponse = webClient.post()
                .uri(tokenUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters.fromFormData("grant_type", "client_credentials")
                        .with("client_id", clientId)
                        .with("client_secret", clientSecret))
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .block(); // Block only this background thread until we get the token

        if (tokenResponse == null || tokenResponse.access_token() == null) {
            throw new RuntimeException("Failed to retrieve Salesforce access token");
        }

        return tokenResponse.access_token();
    }

    // @Async
    // public void createSalesforceAccount(String companyName, String companyWebsite) {
    public void createSalesforceAccount(Company company) {

        System.out.println(">>>>>> 3. inside SalesforceIntegrationService class createSalesforceAccount method");
        String accessToken = getSalesforceAccessToken();

        System.out.println(">>>>>>> access token is: " + accessToken);

        // STEP 2: Create the Account in Salesforce (JSON)
        String companyApiUrl = domain + "/services/apexrest/" + "create-company";

        // Map the data to Salesforce Account standard fields
        Map<String, String> companyData = new HashMap<>();
        companyData.put("LocalId", company.getId().toString());
        companyData.put("Name", company.getCompanyName());
        companyData.put("IndustryType", company.getIndustryType());
        companyData.put("URL", company.getCompanyURL());
        companyData.put("Size", company.getCompanySize());
        companyData.put("Description", company.getCompanyDescription());
        companyData.put("Email", company.getCompanyEmail());
        companyData.put("Password", company.getCompanyPassword());

        webClient.post()
                .uri(companyApiUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(companyData)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(
                        response -> System.out.println("Success! Account Created ID: " + response),
                        error -> System.err.println("Salesforce API Error: " + error.getMessage())
                );
    }
}