# MedCentral - Hospital Management System (HMS)

**MedCentral** is a comprehensive, modular, and scalable Hospital Management System developed by Group 1. It enables efficient digital management of hospitals, patients, staff, appointments, prescriptions, medicines, and more.

This system was designed with multi-hospital access, patient portability, and robust data relationships in mind. It offers API-driven interaction with structured database schemas and clean backend logic.

## 👥 Contributors

- [@jibolashittubolu](https://github.com/jibolashittubolu)
- [@IamVistall](https://github.com/IamVistall)
- [@whiteNight39](https://github.com/whiteNight39) (Titania)

---

## 📌 Features

- National-level patient registry with local access controls
- Hospital and staff registration and management
- Medicine inventory tracking per hospital
- Equipment catalog and maintenance tracking
- Patient appointment booking and calendar
- In-person check-ins (emergency, appointment-based, or walk-in)
- CheckIn-staff assignment tracking
- Prescription creation, fulfillment, and billing
- Automated sales and financial tracking per CheckIn
- Advanced queries for search, history, and report generation

---

## 🏗️ System Architecture

MedCentral is built around 10 core entities, each mapped to a dedicated SQL table and corresponding API layer:

1. **PATIENT** – Global patient registry
2. **HOSPITAL** – Individual hospital information
3. **STAFF** – Medical and non-medical personnel
4. **MEDICINE** – Drug stock with reorder and expiry management
5. **EQUIPMENT** – Hospital devices and machinery with condition tracking
6. **APPOINTMENT** – Scheduled visits between patient and staff
7. **CHECKIN** – Onsite hospital visit representation
8. **CHECKIN-STAFF** – Staff assignment per visit
9. **PRESCRIPTION** – Medicines prescribed during visits
10. **SALES** – Billing and prescription fulfillment

Each table includes fields for `createdAt`, `updatedAt`, and `status` (where applicable) to support auditability and soft-deletion.

---

## 🧠 Technologies

- **Java**
- **Spring Boot (JDBC Template)**
- **PostgreSQL**
- **UUID-based entity IDs**
- **RESTful APIs**
- **IntelliJ IDEA** for development
- **Git & GitHub** for version control

---

---

## 🔌 API Overview

### Patient APIs
- `POST /patients`
- `GET /patients/{id}`
- `PATCH /patients/{id}`
- `GET /patients?search=...`
- `DELETE /patients/{id}` *(soft delete)*

### Appointment APIs
- `POST /appointments`
- `PATCH /appointments/{id}/reschedule`
- `DELETE /appointments/{id}`
- `GET /appointments?patientId=...`

### CheckIn APIs
- `POST /checkins`
- `GET /checkins/{id}`
- `PATCH /checkins/{id}`
- `GET /checkins?patientId=...`

### Medicine APIs
- `POST /medicines`
- `GET /medicines/{id}`
- `PATCH /medicines/{id}/add-stock`
- `GET /medicines?hospitalId=...`
- `GET /medicines/low-stock`

### Staff APIs
- `POST /staff`
- `GET /staff/{id}`
- `PUT /staff/{id}`
- `GET /staff?hospitalId=...`

...and many more for:
- Equipment
- Prescription
- Sales
- Bulk operations (e.g., assign multiple staff, bulk prescribe)

---

## ⚙️ Setup Instructions

### Clone the repo:
   ```bash
   git clone https://github.com/jibolashittubolu/MedCentral.git
```
---

## 🔐 Notes

- All entities use UUIDs as primary keys.
- Soft deletes are supported via status fields.
- Bulk operations and advanced search endpoints are available for staff, prescriptions, and appointments.
