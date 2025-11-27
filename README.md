# ADF TabularTranslator Generator

> A Spring Boot REST API that generates Azure Data Factory `TabularTranslator` JSON configurations from field specifications.

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
  - [JSON Input](#json-input)
  - [CSV Input](#csv-input)
  - [Swagger UI](#swagger-ui)
- [API Endpoints](#api-endpoints)
- [CSV Format](#csv-format)
- [Examples](#examples)
- [Dependencies](#dependencies)
- [Building](#building)
- [Contributing](#contributing)

## 🎯 Overview

This application simplifies the creation of Azure Data Factory (ADF) `TabularTranslator` configurations by accepting field specifications in either JSON or CSV format and generating the corresponding ADF-compatible JSON output.

Perfect for data engineers and analysts who need to quickly generate ADF Copy Activity translator configurations without manually writing JSON.

## ✨ Features

- **Multiple Input Formats**: Accept both JSON and CSV file inputs
- **Type Mapping**: Automatic conversion of common data types to ADF-compatible types
- **CSV Support**: Upload Excel-exported CSV files with field specifications
- **Interactive Documentation**: Built-in Swagger UI for easy testing
- **Lightweight**: Minimal dependencies, fast startup
- **Future-Ready**: Includes tokenization metadata field for upcoming features

## 📦 Prerequisites

- **Java 17** or higher
- **Maven 3.6+**
- **Port 8080** available (or configure a different port)

## 🚀 Installation

1. **Clone or download the project**:
   ```bash
   cd adf-tabular-translator
   ```

2. **Build the project**:
   ```bash
   mvn clean package
   ```

3. **Run the application**:
   ```bash
   java -jar target/translator-generator-0.0.1-SNAPSHOT.jar
   ```

   Or using Maven:
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`.

## 💡 Usage

### JSON Input

Send a POST request with JSON payload to `/api/generate-translator`:

```bash
curl -X POST http://localhost:8080/api/generate-translator \
  -H "Content-Type: application/json" \
  -d '{
    "fields": [
      {
        "name": "userId",
        "type": "Integer",
        "optional": false
      },
      {
        "name": "userName",
        "type": "String",
        "optional": true,
        "tokenizationNeeded": true
      },
      {
        "name": "salary",
        "type": "Decimal",
        "precision": 10,
        "scale": 2
      }
    ]
  }'
```

### CSV Input

Upload a CSV file to `/api/generate-translator/csv`:

```bash
curl -X POST http://localhost:8080/api/generate-translator/csv \
  -F "file=@fields.csv"
```

### Swagger UI

Access the interactive API documentation at:
```
http://localhost:8080/swagger-ui/index.html
```

Use Swagger UI to test endpoints directly from your browser without writing curl commands.

## 🔌 API Endpoints

| Method | Endpoint | Description | Input |
|--------|----------|-------------|-------|
| POST | `/api/generate-translator` | Generate translator from JSON | JSON body |
| POST | `/api/generate-translator/csv` | Generate translator from CSV | Multipart file |
| GET | `/swagger-ui/index.html` | Interactive API documentation | - |

## 📄 CSV Format

Your CSV file should have the following columns (case-insensitive):

| Column | Type | Required | Description |
|--------|------|----------|-------------|
| Name | String | Yes | Field name |
| Type | String | Yes | Data type (Integer, String, Decimal, etc.) |
| Precision | Integer | No | Numeric precision |
| Scale | Integer | No | Decimal scale |
| Optional | Boolean | No | Whether field is optional |
| Tokenization | Boolean | No | Tokenization requirement (future feature) |

**Example CSV**:
```csv
Name,Type,Precision,Scale,Optional,Tokenization
userId,Integer,,,false,false
userName,String,,,true,true
salary,Decimal,10,2,false,false
```

## 📝 Examples

### Input (JSON)
```json
{
  "fields": [
    {
      "name": "customerId",
      "type": "Integer"
    },
    {
      "name": "email",
      "type": "String"
    }
  ]
}
```

### Output (TabularTranslator)
```json
{
  "type": "TabularTranslator",
  "mappings": [
    {
      "source": {
        "name": "customerId",
        "type": "Int32"
      },
      "sink": {
        "name": "customerId",
        "type": "Int32"
      }
    },
    {
      "source": {
        "name": "email",
        "type": "String"
      },
      "sink": {
        "name": "email",
        "type": "String"
      }
    }
  ]
}
```

## 📚 Dependencies

- **Spring Boot 3.4.0** - Application framework
- **OpenCSV 5.12.0** - CSV parsing
- **springdoc-openapi 2.8.14** - Swagger/OpenAPI documentation

## 🔨 Building

### Build JAR
```bash
mvn clean package
```

### Run Tests
```bash
mvn test
```

### Skip Tests
```bash
mvn clean package -DskipTests
```

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📧 Support

For issues or questions, please open an issue in the project repository.

---

**Note**: The `tokenizationNeeded` field is included in the input specification for future feature development and does not currently affect the mapping logic.
