
Чтобы запустить приложение в docker выполните команду `docker-compose up --build` из корневой папки проекта.

Чтобы проверить номер телефона нужно выполнить POST запрос `http://localhost:8080/phone` с телом
`{
    "phone": "+79123011111"
}`

Код придерживается стандарта Javadoc для документации публичных классов и методов; для REST API используется OpenAPI.

Использованные фреймворки и библиотеки:

1. Библиотеки Spring Boot
(включая spring-boot-starter-validation, spring-boot-starter-web, spring-boot-configuration-processor, spring-boot-starter-test)
Лицензия: Apache License 2.0.
Источник: GitHub: [spring-projects/spring-boot](https://github.com/spring-projects/spring-boot). 
2. SpringDoc OpenAPI
(springdoc-openapi-starter-webmvc-ui)
Лицензия: Apache License 2.0.
Источник: GitHub: [springdoc/springdoc-openapi](https://github.com/springdoc/springdoc-openapi). 
3. Jackson Databind
(com.fasterxml.jackson.core:jackson-databind)
Лицензия: Apache License 2.0.
Источник: GitHub: [FasterXML/jackson-databind](https://github.com/FasterXML/jackson-databind). 
4. Project Lombok
(org.projectlombok:lombok)
Лицензия: MIT License.
Источник: [projectlombok/lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok). 
5. SLF4J API
(org.slf4j:slf4j-api)
Лицензия: MIT License.
Источник: [slf4j.org/license](https://mvnrepository.com/artifact/org.slf4j/slf4j-api).
6. Logback Classic
(ch.qos.logback:logback-classic)
Лицензия: Двойная лицензия EPL 1.0 и LGPL 2.1. Обе являются открытыми.
Источник: https://mvnrepository.com/artifact/ch.qos.logback/logback-classic.
7. JUnit 5 и Mockito
(junit-jupiter-api, junit-jupiter-engine, junit-platform-launcher, mockito-core)
Лицензия JUnit: Eclipse Public License v2.0 (открытая лицензия).
Источник JUnit: https://mvnrepository.com/artifact/junit/junit.
Лицензия Mockito: MIT License.
Источник Mockito: https://mvnrepository.com/artifact/org.mockito/mockito-core.
