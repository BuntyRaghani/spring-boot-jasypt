[![CircleCI](https://circleci.com/gh/BuntyRaghani/spring-boot-jasypt.svg?style=svg)](https://circleci.com/gh/BuntyRaghani/spring-boot-jasypt)
# Spring Boot Jasypt

**A simple Spring Boot app to encrypt and decrypt secrets using Jasypt.**


## Jasypt Introduction

**Jasypt (Java Simplified Encryption)** is a library using which we can encrypt and decrypt secrets. Instead of hardcoding the secrets in our application configuration(application.properties), we will encrypt the secrets using the Jasypt library and store the encrypted value inside the application.properties instead of the plain text. Our application will auto decrypt the value and provide the original value wherever required.

### How to Use:
1. Add Jasypt spring boot starter dependency in pom.xml of your application.
2. Encrypt the value using Jasypt CLI commands or by using the Jasypt maven plugin.
3. Store the encrypted value instead of original plain text inside the application.properties file.
4. Read the property using @Value Spring annotation which will inject the decrypted value into the field.
5. Use the field wherever required in your class.

## Encrypting & Decrypting Secrets

### Using Jasypt CLI commands

* Download the Jasypt jar file from the maven repository and place it inside any folder on your system.
* Start the terminal/command prompt and change the path to the folder containing the Jasypt jar file.

#### Steps to Encrypt Secret:<br/>
This is required to generate the encrypted value so that you can store the generated value inside the application.properties file of your application.

1. Encrypt the secret using the below command.
```
java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="Password@123" password=SomeStrongEncryptionKey algorithm=PBEWITHHMACSHA512ANDAES_256 ivGeneratorClassName=org.jasypt.iv.RandomIvGenerator
```
> where:
> * jasypt-1.9.3.jar is the name of the jar file downloaded from the maven repository.
> * JasyptPBEStringEncryptionCLI is the name of the class responsible for performing encryption.
> * Password@123 is the secret that you want to encrypt.
> * SomeStrongEncryptionKey is the key using which encryption will be performed.
> * PBEWITHHMACSHA512ANDAES_256 is the name of algorithm.
> * RandomIvGenerator is the name of the IV generator that will produce different output with each run of the encryption even if we use the same encryption key.

2. Assign the encrypted value to required property inside the application.properties file. <br/>
   **Syntax:** propertyName=ENC(valueObtainedFromStep1)
```
some.encrypted.property=ENC(ZbDLXoFgnhNTmluLzjZBu/Bq17+pSnuvSyLr23b7RBfBukBXBfWRFQx7626OkQL3)
```
<br/>

#### Steps to Decrypt Secret:<br/>
This is only required if you want to see the decrypted value manually.

1. Decrypt the secret using the below command.
```
java -cp jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringDecryptionCLI input="ZbDLXoFgnhNTmluLzjZBu/Bq17+pSnuvSyLr23b7RBfBukBXBfWRFQx7626OkQL3" password=SomeStrongEncryptionKey algorithm=PBEWITHHMACSHA512ANDAES_256 ivGeneratorClassName=org.jasypt.iv.RandomIvGenerator
```
> where:
> * jasypt-1.9.3.jar is the name of the jar file downloaded from the maven repository.
> * JasyptPBEStringDecryptionCLI is the name of the class responsible for performing decryption.
> * ZbDLXoFgnhNTmluLzjZBu/Bq17+pSnuvSyLr23b7RBfBukBXBfWRFQx7626OkQL3 is the secret that you want to decrypt. It is the same value that was obtained after performing the encryption.
> * SomeStrongEncryptionKey is the key using which decryption will be performed. It should be the same key that was used during the encryption.
> * PBEWITHHMACSHA512ANDAES_256 is the name of an algorithm that was used during the encryption.
> * RandomIvGenerator is the name of the IV generator that was used during the encryption.

<br/>

### Using Jasypt Maven Plugin

* Add jasypt-maven-plugin inside the build > plugins section of pom.xml.

#### Steps to Encrypt Secret:<br/>
This is required to generate the encrypted value so that you can store the generated value inside the application.properties file of your application.

1. Encrypt the secret using the below command.
```
mvn jasypt:encrypt-value -Djasypt.encryptor.password=Password@123 -Djasypt.plugin.value=SomeStrongEncryptionKey
```
> where:
> * Password@123 is the secret that you want to encrypt.
> * SomeStrongEncryptionKey is the key using which encryption will be performed.
> * PBEWITHHMACSHA512ANDAES_256 is the default algorithm that will be used for encryption.
> * RandomIvGenerator is the default IV generator that will be used and it will produce different output with each run of the encryption even if we use the same encryption key.

2. Assign the encrypted value to required property inside the application.properties file. <br/>
   **Syntax:** propertyName=ENC(valueObtainedFromStep1)
```
some.encrypted.property=ENC(ZbDLXoFgnhNTmluLzjZBu/Bq17+pSnuvSyLr23b7RBfBukBXBfWRFQx7626OkQL3)
```
<br/>

#### Steps to Decrypt Secret:<br/>
This is only required if you want to see the decrypted value manually.

1. Decrypt the secret using the below command.
```
mvn jasypt:decrypt-value -Djasypt.encryptor.password=SomeStrongEncryptionKey -Djasypt.plugin.value=ZbDLXoFgnhNTmluLzjZBu/Bq17+pSnuvSyLr23b7RBfBukBXBfWRFQx7626OkQL3
```
> where:
> * ZbDLXoFgnhNTmluLzjZBu/Bq17+pSnuvSyLr23b7RBfBukBXBfWRFQx7626OkQL3 is the secret that you want to decrypt. It is the same value that was obtained after performing the encryption.
> * SomeStrongEncryptionKey is the key using which decryption will be performed. It should be the same key that was used during the encryption.
> * PBEWITHHMACSHA512ANDAES_256 is the default algorithm that will be used for decryption.
> * RandomIvGenerator is the name of the IV generator that was used during the decryption.


## How to Run Application

**Start the application using any of the commands mentioned below:**

> **Note:** These commands need to run inside the root folder of this project i.e inside the **spring-boot-jasypt** folder.


- **Using maven** <br/>```mvn spring-boot:run -Dspring-boot.run.arguments=--jasypt.encryptor.password=SomeStrongEncryptionKey```


- **From jar file**<br/>
  Create a jar file using '**mvn clean install**' command and then execute
  <br/>```java -jar target/spring-boot-jasypt-1.0.1-SNAPSHOT.jar --jasypt.encryptor.password=SomeStrongEncryptionKey```

> The value of jasypt.encryptor.password should be the same key using which you have generated the encrypted value.

> **Note:** By default spring boot application starts on port number 8080. If port 8080 is occupied in your system then you can change the port number by uncommenting and updating the **server.port** property inside the **application.properties** file that is available inside the **src > main > resources** folder.

<br/>

**Send an HTTP GET request to '/getDecryptedValue' endpoint using any of the two methods:**

- **Browser or REST client**
  <br/>```http://localhost:8080/getDecryptedValue```


- **cURL**
  <br/>```curl --request GET 'http://localhost:8080/getDecryptedValue```


## How to Run Unit Test Cases

**Run the test cases using any of the commands mentioned below:**

> **Note:** These commands need to run inside the root folder of this project i.e inside the **spring-boot-jasypt** folder.

- **To run all the test cases**
  <br/>```mvn test -Djasypt.encryptor.password=SomeStrongEncryptionKey```


- **To run a particular test class**
  <br/>```mvn -Dtest=SpringBootJasyptControllerTest test -Djasypt.encryptor.password=SomeStrongEncryptionKey```
  <br/>or
  <br/>```mvn -Dtest=SpringBootJasyptApplicationTests test -Djasypt.encryptor.password=SomeStrongEncryptionKey```

> The value of jasypt.encryptor.password should be the same key using which you have generated the encrypted value.

<br/>

> **Note:** While starting your application or while running the maven install command you need to provide the argument -Djasypt.encryptor.password={encryption-key-without-curly-braces} or else your application will fail to start / maven install command will fail due to test case failures.
