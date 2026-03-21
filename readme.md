# BUPT International School TA Recruitment System - Group 99

## Group Members
- Hengyi Zhang
- Kehan Meng
- Zhizhen Cai
- Chengyi Jia
- Yutong Shi
- Ke Ning

## Current Scope
This repository contains the Sprint 1 prototype of the TA Recruitment System for EBU6304 Software Engineering Group Project.

Implemented core flows:
- Create applicant profile
- Record English qualification
- Save CV file reference
- Save applicant availability
- Post opportunity with requirements
- Browse and search opportunities
- View opportunity details
- Submit application
- Upload screening task
- Review applications by opportunity

## Storage Design
According to coursework requirements, all input/output data are stored in plain text files:
- data/applicants.txt
- data/opportunities.txt
- data/applications.txt

No database is used.

## Project Structure
- `docs/` coursework documents
- `src/` Java source code
- `data/` plain text storage files

## How to Run
Compile:
```bash
javac -d out src/group99/ta/model/*.java src/group99/ta/storage/*.java src/group99/ta/service/*.java src/group99/ta/*.java