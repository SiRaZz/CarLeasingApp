# CarLeasingApp
Demo project for car leasing application task

# REST API

The REST API to the example app is described below.

## Run the app

Execute mvn goal:
    `mvn spring-boot:run`

## Submit new leasing application

### Request

`POST /submit/`

JSON example:

```json
{
  "leasingPeriod": 36,
  "interestRate": 2.6,
  "initialPayment": 500,
  "monthlyPaymentAmount": 420,
  "vehicleDetails": {
    "manufacturer": "BMW",
    "model": "530",
    "productionDate": "2012-06-25T00:00:00.000Z",
    "vinNumber": "WBAPW51CT95253",
    "carPrice": 8950,
    "newCar": false,
    "enginePower": 160
  },
  "personDetails": {
      "personCode": 39405141759,
      "firstName": "Petras",
      "lastName": "Petraitis",
      "workPlace" : "UAB Test",
       "monthlyIncome": 24
  },
  "coApplicantDetails": {
    "personCode": 49652141459,
    "firstName" : "Jonas",
    "lastName": "Jonaitis",
    "workPlace" : "UAB Maxima",
    "monthlyIncome": 14
  }
}
```

### Response
```json
{
    "vehicleDetails": {
        "id": 5,
        "vinNumber": "WBAPW51CT95253",
        "manufacturer": "BMW",
        "model": "530",
        "productionDate": "2012-06-25T00:00:00.000+00:00",
        "carPrice": 8950.0,
        "newCar": false,
        "enginePower": 160
    },
    "status": "REJECTED"
}
```

## Get leasing application status list  by person code

### Request

`GET /getStatus/{personCode}`

### Response
```json
[
    {
        "id": 1,
        "leasingPeriod": 36,
        "interestRate": 2.6,
        "initialPayment": 500.0,
        "status": "APPROVED",
        "vehicleDetails": {
            "id": 3,
            "vinNumber": "WBAPW51CT95253",
            "manufacturer": "BMW",
            "model": "530",
            "productionDate": "2012-06-25T00:00:00.000+00:00",
            "carPrice": 8950.0,
            "newCar": false,
            "enginePower": 160
        },
        "personDetails": {
            "id": 1,
            "personCode": "39405141759",
            "firstName": "Petras",
            "lastName": "Petraitis",
            "workPlace": "UAB Test",
            "monthlyIncome": 2400.0
        },
        "coApplicantDetails": {
            "id": 2,
            "personCode": "49652141459",
            "firstName": "Jonas",
            "lastName": "Jonaitis",
            "workPlace": "UAB Maxima",
            "monthlyIncome": 1400.0
        }
    },
    {
        "id": 2,
        "leasingPeriod": 36,
        "interestRate": 2.6,
        "initialPayment": 500.0,
        "status": "REJECTED",
        "vehicleDetails": {
            "id": 4,
            "vinNumber": "WBAPW51CT95253",
            "manufacturer": "BMW",
            "model": "530",
            "productionDate": "2012-06-25T00:00:00.000+00:00",
            "carPrice": 8950.0,
            "newCar": false,
            "enginePower": 160
        },
        "personDetails": {
            "id": 1,
            "personCode": "39405141759",
            "firstName": "Petras",
            "lastName": "Petraitis",
            "workPlace": "UAB Test",
            "monthlyIncome": 2400.0
        },
        "coApplicantDetails": {
            "id": 2,
            "personCode": "49652141459",
            "firstName": "Jonas",
            "lastName": "Jonaitis",
            "workPlace": "UAB Maxima",
            "monthlyIncome": 1400.0
        }
    },
    ]
```

## Update rule by name

`POST /updateRule`

JSON example:

```json
{
 "ruleName" : "minimumIncome"  ,
 "leasingApplicationRuleType" : "String",
 "value" : "12",
 "validTo" : null
}

```
