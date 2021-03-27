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
# requires GraalVM locally installed.
$ mvn -Pnative-image package
# execute generated native image
$ ./com.dccorp.urlshortner.urlshortnerapplication

# using buildpacks with docker - ref pom.xml line 193-212
$ mvn spring-boot:build-image
# execute docker image 
$ docker run -p 8080:8080 urlshortner:0.0.1-SNAPSHOT

# after application starts [using either buildpacks or native image plugin]
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

#
###### Key Learnings
- Startup time ~ 0.55seconds vs ~4.5seconds subjective to machine.
- The Generated image is around ~182M [using buildpacks] vs ~148M [native image plugin] vs ~40M executable jar [ofcourse JVM is exposed outside of the jar on running machine].
- Startup time is almost instantaneous - but build time is significantly higher - which possibly will improve over time as the project matures.
- Not all Spring modules /projects are supported yet - for ex. developer tools / AOP support missing [03/25] - things in motion already.
- Need significant RAM on the machine to play with Native support in the first place.
- No Out-of-the-box support to generate native images for pre-existing Spring Boot application - tweeks will be required - though in long run that is the goal.
- Size of native image could be futher reduced by using tools like upx.
  ```sh
  $ ls -lh | grep url
  -rwxr-xr-x   1 deepak.chaudhary  staff   148M Mar 27 00:00 com.dccorp.urlshortner.urlshortnerapplication
  -rw-r--r--   1 deepak.chaudhary  staff    38M Mar 26 23:47 urlshortner-0.0.1-SNAPSHOT-exec.jar
  -rw-r--r--   1 deepak.chaudhary  staff    76K Mar 26 23:47 urlshortner-0.0.1-SNAPSHOT.jar
  
  $ upx -7 -k com.dccorp.urlshortner.urlshortnerapplication
  
  $ ls -lh | grep url # size reduced to 43M from 148M which is amazing.
  -rwxr-xr-x   1 deepak.chaudhary  staff    43M Mar 27 00:00 com.dccorp.urlshortner.urlshortnerapplication
  -rwxr-xr-x   1 deepak.chaudhary  staff   148M Mar 27 00:00 com.dccorp.urlshortner.urlshortnerapplicatio~
  -rw-r--r--   1 deepak.chaudhary  staff    38M Mar 26 23:47 urlshortner-0.0.1-SNAPSHOT-exec.jar
  -rw-r--r--   1 deepak.chaudhary  staff    76K Mar 26 23:47 urlshortner-0.0.1-SNAPSHOT.jar
  ```
- Further image size could be reduced by using embedded tomcat specific to native image generations - ref pom.xml line 118.
- Oracle provides GraalVM Dashboard online tool to peek inside native image.
  ```sh
  -H:DashboardDump=dumpfileoversizedbuildArgs
  -H:+DashboardAll
  ```
  Only available when using native image plugin and not buildpacks to generate image.
  [GraalVM Dashboard](https://www.graalvm.org/docs/tools/dashboard/?ojr=dashboard)
- Very good support from Community / good documentation Spring Native and GraalVM.

#
###### Links
- [GraalVM](https://www.graalvm.org/)
- [Spring Native](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/)
- [Spring Native Samples](https://github.com/spring-projects-experimental/spring-native/tree/master/samples)
###### More details to follow.