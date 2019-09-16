# hospAdmissions


This is a springboot based application that uses CRUD repository and hibernate, and in the backend MySQL Server. Please ensure that you have setup Java8 in the system, and has installed maven, and the required paths and JVM_HOME and MAVEN_HOME are set.

A) Initialize MySQL DB
 1. Import the scripts in the root of the folder in the order they are given, the first one to be executed being 0_prom_hosp_admission_DB_Init.sql

A) Setup and Run

1. Git clone the repository into your loal file system.
2. From command prompt, run the below command
     $ mvn clean verify
3. To start the application:
     $ mvn spring-boot:run




Hospital Admissions REST services

Following are the rest services for performing admission related operations:

1) Fetch all admissions:

GET  http://localhost:8080/admissions

sample output:

[ 
   { 
      "id":1,
      "patient":{ 
         "name":"Arun Job",
         "dob":"XX-XX-XXXX",
         "sex":{ 
            "code":"M",
            "name":"Male"
         },
         "address":null,
         "phone":null
      },
      "admissionDate":"13-09-2019",
      "category":{ 
         "categoryCode":4,
         "categoryName":"Outpatient"
      },
      "source":"referral",
      "modifiedDate":"16-09-2019",
      "modifiedBy":"Joby",
      "dischargeDate":"16-09-2019"
   },
   { 
      "id":2,
      "patient":{ 
         "name":"Joby Job",
         "dob":"XX-XX-XXXX",
         "sex":{ 
            "code":"M",
            "name":"Male"
         },
         "address":null,
         "phone":null
      },
      "admissionDate":"16-09-2019",
      "category":{ 
         "categoryCode":1,
         "categoryName":"Normal"
      },
      "source":"referral",
      "modifiedDate":"16-09-2019",
      "modifiedBy":"Joby",
      "dischargeDate":null
   }
]


2) Create admission:
POST  http://localhost:8080/admissions

PayLoad:

{ 
   "patient":{ 
      "name":"Joby Job",
      "dob":"XX-XX-XXXX",
      "sex":{ 
         "code":"M",
         "name":"Male"
      },
      "address":null,
      "phone":null
   },
   "doa":"13-09-2019",
   "category":{ 
      "categoryCode":1
   },
   "source":"referral",
   "modifiedDate":"11-09-2019",
   "modifiedBy":"Joby"
}


3) Update admission:

The path element represents ID of th admission in the table

PUT  http://localhost:8080/admissions/{1}

PayLoad:

{ 
   "patient":{ 
      "name":"Joby Job",
      "dob":"XX-XX-XXXX",
      "sex":{ 
         "code":"M",
         "name":"Male"
      },
      "address":null,
      "phone":null
   },
   "doa":"13-09-2019",
   "category":{ 
      "categoryCode":1
   },
   "source":"referral",
   "modifiedDate":"11-09-2019",
   "modifiedBy":"Joby"
}


4) Delete admission:
    -- Note that a 'soft' delete is only implemented. This will just close the current active admission for the Patient (set dod - date of discharge as current date)
    
 DELETE  http://localhost:8080/admissions/{1}

 
