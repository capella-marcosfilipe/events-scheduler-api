# Events Scheduler API

REST API for creating events, subscribing attendees and tracking referrals: each subscriber gets a referral link, and the API ranks who brought the most people to an event.

**Stack:** Java 21 · Spring Boot 3.4 · Spring Data JPA / Hibernate · MySQL

## Endpoints

| Method | Path | Description |
|---|---|---|
| `POST` | `/events` | Create an event |
| `GET` | `/events` | List events |
| `GET` | `/events/{prettyName}` | Get an event by its slug |
| `POST` | `/subscription/{prettyName}` | Subscribe to an event |
| `POST` | `/subscription/{prettyName}/{userId}` | Subscribe through a referral from `userId` |
| `GET` | `/subscription/{prettyName}/ranking` | Top referrers for the event |
| `GET` | `/subscription/{prettyName}/ranking/{userId}` | A user's referral position |

Domain errors (event not found, duplicate subscription, unknown referrer) return structured `ErrorMessage` responses.

## Structure

`controller` → `service` → `repository` (Spring Data), with `model` entities (`Event`, `User`, `Subscription`), `dto` and custom `exception` types.

## Running

```bash
# MySQL running locally with a db_events database
# configure credentials in src/main/resources/application.properties
./mvnw spring-boot:run
```
