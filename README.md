# OtpSessionTracking (Email + OTP Authentication)


This project implements a passwordless authentication flow using **Email + OTP**, followed by a **Session Tracking Screen** that shows login duration in real time.


The assignment is implemented fully offline (no backend), with all OTP logic stored locally.


---


## Features


### 1. Email + OTP Login Flow

- User enters an email address

- User taps **Send OTP**

- A 6-digit OTP is generated locally

- User enters OTP to verify and log in

---

## OTP Rules Implemented


### OTP Length

- OTP is always **6 digits**


```kotlin
val otp = (100000..999999).random().toString()
```
## OTP Expiry (60 Seconds)

Each OTP is valid for only **60 seconds**.

Expiry is checked using timestamp difference:

```kotlin
val secondsSinceCreation = (System.currentTimeMillis() - otpData.generatedAt) / 1000

if (secondsSinceCreation > 60) {
    return OtpResult.Expired
}
```
---
## Data Structures Used

### 📌 Map Storage (OTP Per Email)

OTP information is stored using:

```java
Map<String, OtpData>

```
Reason
This approach is used because: Multiple users/emails can request OTP simultaneously
Each email maintains its own:
- OTP value
- Expiry time
- Attempts count

Provides efficient and fast lookup using the email as the key

---
## External SDK Integration (Timber)

Timber was integrated as the mandatory external SDK for logging and debugging.

### Why Timber?

- Lightweight and easy to integrate
- No backend required
- Provides cleaner logging than `Log.d()`
- Helps track events like OTP creation and logout
- Perfect for this assignment because only event logging is required

---
## GPT Usage

GPT was used during development for:

- Guidance on Jetpack Compose screen structure
- Timber usages (never used before)
- README formatting support

### Implemented Myself
- OTP expiry logic and session timer handling
- Compose recomposition
- ViewModel state management
- OTP validation rules  


