# Java Servlet Login Page

This project is a Java servlet-based login page that demonstrates user authentication using various dependencies and a configuration file. Below are the steps to set up and configure the project.

## Dependencies

Before running this project, make sure to download the following dependencies and place them in the `WEB-INF/lib` directory of your servlet project:

- [com.google.gson-2.9.1.jar](https://search.maven.org/artifact/com.google.code.gson/gson/2.9.1/jar)
- [commons-codec-1.16.0.jar](https://repo1.maven.org/maven2/commons-codec/commons-codec/1.16.0)
- [javax.activation-1.2.0.jar](https://mvnrepository.com/artifact/javax.activation/javax.activation-api/1.2.0)
- [javax.mail-1.6.2.jar](https://mvnrepository.com/artifact/javax.mail/javax.mail-api/1.6.2)
- [json-20230618.jar](https://repo1.maven.org/maven2/org/json/json/20230618)
- [jsp-api.jar](https://repo1.maven.org/maven2/javax/servlet/jsp/javax.servlet.jsp-api/2.3.3)
- [mysql-connector-j-8.1.0.jar](https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.23)
- [servlet-api.jar](https://mvnrepository.com/artifact/jakarta.servlet/jakarta.servlet-api/6.0.0)
- [smtp-2.0.1.jar](https://mvnrepository.com/artifact/com.sun.mail/smtp/2.0.1)

## Configuration

After adding the dependencies, make sure to configure the `Globals.java` class to provide the necessary credentials and information for your project. Here is the structure of the `Globals.java` class:

```java
package com.login;

public final class Globals {
    final public static String Website = "";
    final public static String Google_Mail = "";
    final public static String Google_Pass = "";
    final public static String MySQL_Address = "";
    final public static String MySQL_User = "";
    final public static String MySQL_Pass = "";
    final public static String Client_ID = "";
    final public static String Client_Secret = "";
}
```

Fill in the values for the following constants within the `Globals.java` class:

- **Website**: Your website URL (e.g., "https://www.example.com").
- **Google_Mail** and **Google_Pass**: Google email and password for sending emails (if needed).
- **MySQL_Address**, **MySQL_User**, and **MySQL_Pass**: MySQL database connection details.
- **Client_ID** and **Client_Secret**: Credentials for any OAuth or API integrations if applicable.

## Usage

Once you have configured the dependencies and the `Globals.java` class, you can deploy the servlet-based login page to your preferred servlet container (e.g., Apache Tomcat) and start the application.

## Additional Notes

- Make sure to set up your servlet container properly and configure the web application's deployment descriptor (`web.xml`) with appropriate servlet mappings, security settings, and URL patterns as needed.

- The exact usage and functionality of this login page may vary based on your specific project requirements. Be sure to adapt the code and configuration accordingly.

- This project was given to me by Eduinq as a part of their development prgramme.

- You are good to use this but please make sure to mention me. UwU.

For more information and details about this project, feel free to contact me at shahbazalam92399@gmail.com.
