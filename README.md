# Spring Boot SolAPI Integration

This project integrates SolAPI, a messaging platform supporting SMS, LMS, MMS, KakaoTalk notifications, into a Spring Boot application.

- Send SMS, LMS, MMS, and KakaoTalk notifications using SolAPI's HTTP REST API via FeignClient.
- Implement API Key authentication with HMAC-SHA256 for secure requests.
- Centralized test data management using FixtureUtil for consistent and maintainable testing.

## Tech Used
- Backend Framework: Spring Boot 3.4.1
- Programming Language: Java (JDK 21)
- Build Tool: Gradle
- Dependencies: Spring Web, Nurigo SDK, Spring Cloud OpenFeign
- Testing: JUnit 5, Mockito, WireMock, MockMvc

## Prerequisites
- JDK 21
- Gradle build tool
