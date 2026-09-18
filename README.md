# Payflow Digital Wallet

A payment-inspired digital wallet prototype built with Java and Spring Boot. It demonstrates layered architecture, wallet balances, payment-style transfers, mobile onboarding, transaction references, passbook history, and a responsive frontend.

> This project is for learning and portfolio use. It does not process real Paytm, UPI, card, or bank payments.

## Features

- Mobile number and email onboarding
- Wallet balance and add-money flow
- Transfer flow with validation
- Transaction IDs and timestamps
- Digital passbook with filters
- QR and gallery payment entry points
- Bank account and contact screens
- Security and privacy dashboard
- Responsive browser interface

## Technology

- Java 21+
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- H2 for development
- Maven
- HTML, CSS, and JavaScript

## Project Structure

```text
src/
  main/
    java/com/tyagi/fintech1/
      controller/       REST endpoints
      digital_wallet/   Spring Boot entry point
      entity/           JPA entities
      repository/       database repositories
      service/          business logic and transactions
    resources/
      static/           frontend application
      application.properties
  test/                 application tests
docs/                   architecture and developer guide
```

## Run Locally

Requirements: Java 21 or newer.

```powershell
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

Open `http://localhost:8080` in a browser.

## Architecture

```text
Browser UI
   -> REST Controller
   -> Service Layer
   -> Repository
   -> JPA Entity / Database
   -> JSON response
   -> UI refresh
```

Controllers handle HTTP requests. Services own wallet rules and transaction boundaries. Repositories access the database. The frontend calls the REST API and renders the returned wallet and ledger state.

## Documentation

- [Architecture and developer guide](docs/PAYFLOW_DEVELOPER_GUIDE.html)
- [Developer guide PDF](docs/PAYFLOW_DEVELOPER_GUIDE.pdf)
- [Architecture notes](docs/architecture.md)

## Production Roadmap

Before production use, add Spring Security with JWT, password hashing, PostgreSQL with Flyway migrations, server-side fee deduction, provider webhook verification, idempotency keys, HTTPS, rate limiting, Docker, and integration tests.

## Portfolio Positioning

Describe this honestly as a payment-inspired wallet prototype with production-oriented architecture. A real payment product requires licensed provider integrations, compliance controls, reconciliation, fraud monitoring, and secure authentication.
