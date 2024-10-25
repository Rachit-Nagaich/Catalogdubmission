# Shamir's Secret Sharing

This project implements Shamir's Secret Sharing scheme using Java. The program reads secret sharing parameters and roots from a JSON file and calculates the constant term using Lagrange interpolation.

## Features

- Load secret sharing parameters from a JSON file.
- Calculate constant terms (secrets) based on the input values.
- Support for multiple test cases.

## Requirements

- Java Development Kit (JDK) 20 or higher
- Maven for dependency management

## Dependencies

This project uses the following dependency for JSON handling:

- `org.json:json:20210307`

## Installation

1. **Clone the Repository**

   Open your terminal and run the following command:

   ```bash
   git clone https://github.com/yourusername/ShamirSecretSharing.git
   cd ShamirSecretSharing

