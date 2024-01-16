# NITCONF - SE LAB MODULE 1

## Software Requirements Specification

### Table of Contents
1. [Revision History](#1-revision-history)
2. [Introduction](#2-introduction)
   - 2.1 [Purpose](#21-purpose)
   - 2.2 [Intended Audience](#22-intended-audience)
   - 2.3 [Product Scope](#23-product-scope)
   - 2.4 [References](#24-references)
3. [Overall Description](#3-overall-description)
   - 3.1 [Product Perspective](#31-product-perspective)
   - 3.2 [Product Functions](#32-product-functions)
   - 3.3 [User Classes and Characteristics](#33-user-classes-and-characteristics)
   - 3.4 [Operating Environment](#34-operating-environment)
   - 3.5 [Design and Implementation Constraints](#35-design-and-implementation-constraints)
   - 3.6 [User Documentation](#36-user-documentation)
   - 3.7 [Assumptions and Dependencies](#37-assumptions-and-dependencies)
4. [External Interface Requirements](#4-external-interface-requirements)
   - 4.1 [User Interfaces](#41-user-interfaces)
   - 4.2 [Hardware Interfaces](#42-hardware-interfaces)
   - 4.3 [Software Interfaces](#43-software-interfaces)
   - 4.4 [Communications Interfaces](#44-communications-interfaces)
5. [System Features](#5-system-features)
   - 5.1 [Authentication](#51-authentication)
   - 5.2 [Dashboard](#52-dashboard)
      - 5.2.1 [User Profile](#521-user-profile)
      - 5.2.2 [Upload and View Submissions](#522-upload-and-view-submissions)
      - 5.2.3 [Interaction With Reviewers](#523-interaction-with-reviewers)
      - 5.2.4 [Notification System](#524-notification-system)
6. [Other Nonfunctional Requirements](#6-other-nonfunctional-requirements)
   - 6.1 [Performance Requirements](#61-performance-requirements)
   - 6.2 [Safety & Security Requirements](#62-safety-and-security-requirements)
   - 6.3 [Software Quality Attributes](#63-software-quality-attributes)
      - 6.3.1 [Reliability](#631-reliability)
      - 6.3.2 [Adaptability](#632-adaptability)
      - 6.3.3 [Maintainability](#633-maintainability)
      - 6.3.4 [Portability](#634-portability)
      - 6.3.5 [Cost Effectiveness](#635-cost-effectiveness)

## 1. Revision History
- Version 1.0 

## 2. Introduction
### 2.1 Purpose
This document outlines the requirements for the NITCONF Conference Website, focusing on speaker login and project submission functionalities along with being able to view their details and status of their papers.

### 2.2 Intended Audience
- Intended for speakers to submit their research papers as part of the CFP process.

### 2.3 Product Scope
- Specifies features related to the CFP process.
- Enables users to login and submit abstracts.

### 2.4 References
- [Spring Boot Documentation](https://spring.io/)

## 3. Overall Description
### 3.1 Product Perspective
- NITCONF website enhances the conference management process.

### 3.2 Product Functions
- User login using Google.
- Abstract submission (upto 3).
- View status of submitted papers.
- Edit and view speaker details.

  ![FLOWCHART](https://github.com/mikasajaeger19/SE_LAB_Team8_NITCONF/blob/main/docs/flow_chart.png?raw=true)

### 3.3 User Classes and Characteristics
1. Authors:
   - Must log in using Google account.
   - Primary functionality used is uploading research papers and viewing their status.

### 3.4 Operating Environment
- Web-based application.
- Compatible with major OS and browsers.
- Uses Spring Boot framework with Java.

### 3.5 Assumptions and Dependencies
- Users have internet access.
- Users follow provided guidelines.
- Dependencies on Spring Boot, external libraries, and notification system.

## 4. External Interface Requirements
### 4.1 User Interfaces
1. Logical Characteristics:
   - Web-based application.
   - User Login Screens.
   - Dashboard displaying submission status.
   - Facility to upload research papers.
   - Deadline Notification.

### 4.2 Hardware Interfaces
- Compatible with various devices.
- Requires internet access.

### 4.3 Software Interfaces
- Interfaces with relational database.
- Uses Spring Boot.
- REST APIs for CRUD functionality.

### 4.4 Communications Interfaces
- Email notification informing participants of registration and confirmation of paper submission and acceptance.
- Web Browser Communications to upload research papers and speaker data.

## 5. System Features
### 5.1 Authentication
- User login using Google.

### 5.2 Dashboard
#### 5.2.1 User Profile
- View/update user details.

#### 5.2.2 Upload And View Submissions
- Upload papers and add associated tags which enable viewers to filter papers by topic.
- View submission status.
- Re-submit with reviewer-suggested changes, within a reviewer specified deadline.
- View paper history and associated reviewer comments for the version.

#### 5.2.3 Interaction With Reviewers
- View reviewer comments and enable authors to reply to them if necessary.
- Reviewers maintain anonymity through interaction in the comments on a paper's page.
- View whether individual reviewer has given approval or not.

#### 5.2.4 Notification System
- Receive urgent notifications via email.

## 6. Other Nonfunctional Requirements
### 6.1 Performance Requirements
- Response time, concurrent user handling, scalability.
- Database and network performance.
- Reliability and uptime.

### 6.2 Safety and Security Requirements
- Data security through dedicated database
