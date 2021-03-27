### Demo Application to explore Spring Native

###### Sample application to expose Spring Native Capabilities. Abbreviated version of something like bitly. Not for production use, yet! All persistance to inmemory H2 db.

#### Features [Spring Modules included]
- Spring Boot 2.4.x.
- Spring Data JPA [Starter].
- Spring Security [Starter] with JWT.
- Spring Cloud Function [Starter].
- Tomcat with SSL.

#### Instructions
```sh
# native image plugin - ref pom.xml line 22-49, line 209-210
$ mvn -Pnative-image package
# execute generated native image
$ ./com.dccorp.urlshortner.urlshortnerapplication

# using buildpacks with docker - ref pom.xml line 193-212
$ mvn spring-boot:build-image
# execute docker image 
$ docker run -p 8080:8080 urlshortner:0.0.1-SNAPSHOT

# after application starts using either option above [ buildpacks or native image plugin ]
# register a new user
curl -k --location --request POST 'https://localhost:8080/signup' --header 'Content-Type: application/json' --data-raw '{
    "firstName":"FName",
    "lastName":"LName",
    "userName":"userName",
    "password":"userPassword"
}'

# execute login endpoint to get JWT
curl -i -k --location --request POST 'https://localhost:8080/login' --header 'Content-Type: application/json' --data-raw '{
    "userName":"userName",
    "password":"userPassword"
}' | grep Auth
# will print Auhtorisation Header
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.e.......

# use authorization header to access secured endpoint
curl -k --location --request POST 'https://localhost:8080/create' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaGF1ZGhhcnlkZWVwYWswOEBnbWFpbC5jb20iLCJleHAiOjE2MTc2NDQzNzl9.eSfylYVUIgnOFbUULXG9yjUJuApPvgSKJCti_Jdv-XK-umVPPv7eYRgSm62K60vY89Sp_nRIWx6UOjEuNe5v6Q' \
--header 'Content-Type: application/json' \
--data-raw '{
    "requestURL":"https://stackoverflow.com/questions/17955777/redirect-to-an-external-url-from-controller-action-in-spring-mvc",
    "requestedShortCode":"stack"
}'
#should see response like
{"statusCodeValue":201,"statusCode":"CREATED","body":{"shortURL":"stack",......
```

###### More details to follow.