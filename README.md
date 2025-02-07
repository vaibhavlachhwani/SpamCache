# SpamCache

SpamCache is a spam detection service that allows users to report spam phone numbers and check if a number is likely to be spam. The system utilizes a sophisticated scoring algorithm that considers the number of reports, time since the last report, and a logarithmic weight scaling to assess risk dynamically. It is built for high performance and scalability, ensuring real-time spam classification.

## Features

- **Spam Reporting:** Users can report a phone number as spam, incrementing its spam score.
- **Spam Lookup:** Retrieves the spam classification for a given number based on its spam score.
- **Dynamic Spam Detection:** Uses a weighted scoring system that incorporates:
  - **Report Frequency:** Higher reports increase risk non-linearly.
  - **Time Decay Factor:** Older reports lose influence exponentially.
  - **Threshold-Based Classification:** Categorizes numbers into **Not Spam, Likely Spam, or Confirmed Spam** based on predefined thresholds.

## API Endpoints

### Report Spam
- **Endpoint:** `POST /report-spam`
- **Description:** Reports a phone number as spam. If the number already exists, its spam count increases.
- **Response:** Status message confirming the report.

### Lookup Number
- **Endpoint:** `GET /lookup`
- **Description:** Checks if a phone number is classified as spam based on its spam score.
- **Response:** One of three values: `NOT_SPAM`, `LIKELY_SPAM`, or `SPAM`.

## Technology Stack

- **Backend:** Spring Boot, Spring Web
- **Database:** PostgreSQL/H2 (for persistent spam tracking)
- **ORM:** Spring Data JPA
- **Build Tool:** Maven

## Installation & Setup

### Prerequisites
- Java 17+
- PostgreSQL installed and running
- Maven installed

## Configuration

Threshold values and decay factors can be modified in `application.properties`:
```properties
app.spam.base_threshold=10
app.spam.time_decay_factor=0.05
app.spam.report_weight_scaling=1.5
```


