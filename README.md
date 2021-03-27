### Demo Application to explore Spring Native.

###### Sample application to expose Spring Native Capabilities. Abbreviated version of something like bitly. Not for production use, yet! All persistance to inmemory H2 db.

#### Features / Spring Modules included
- Spring Boot 2.4.x.
- Spring Data JPA [<i>Starter</i>].
- Spring Security [<i>Starter</i>] with JWT.
- Spring Cloud Function [<i>Starter</i>].
- Tomcat with SSL.
- Exposing only REST endpoints.

###### [Summary 03/25](https://github.com/chaudharydeepak/urlshortner#key-learnings)

###### Quick Start:

1. Download [native package](https://github.com/chaudharydeepak/urlshortner/releases).
```sh
$ ./com.dccorp.urlshortner.urlshortnerapplication 
```   
2. Or execute following:
```sh
$ docker run -p 8080:8080 docker.pkg.github.com/chaudharydeepak/urlshortner/urlshortner:0.1
```

###### Detailed Instructions:
```sh
# checkout source code to local.

# native image plugin - ref pom.xml line 22-49, line 209-210
# requires GraalVM locally installed.
$ mvn -Pnative-image package
# execute generated native image
$ ./com.dccorp.urlshortner.urlshortnerapplication

# using buildpacks - ref pom.xml line 193-212
# requires docker local installation.
$ mvn spring-boot:build-image
# execute docker image 
$ docker run -p 8080:8080 urlshortner:0.0.1-SNAPSHOT

# after application starts [built either with buildpacks or native image plugin]
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

# use authorization header to access secured endpoint.
# basically we are creating a short url for a given url.
curl -k --location --request POST 'https://localhost:8080/create' \
--header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaGF1ZGhhcnlkZWVwYWswOEBnbWFpbC5jb20iLCJleHAiOjE2MTc2NDQzNzl9.eSfylYVUIgnOFbUULXG9yjUJuApPvgSKJCti_Jdv-XK-umVPPv7eYRgSm62K60vY89Sp_nRIWx6UOjEuNe5v6Q' \
--header 'Content-Type: application/json' \
--data-raw '{
    "requestURL":"https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/",
    "requestedShortCode":"nativedocs"
}'
#should see response like
{"statusCodeValue":201,"statusCode":"CREATED","body":{"shortURL":"stack",......

# try to see if short url works
$ curl -k -i https://localhost:8080/go/nativedocs
HTTP/1.1 302 
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers
Location: https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
Strict-Transport-Security: max-age=31536000 ; includeSubDomains
X-Frame-Options: SAMEORIGIN
Content-Length: 0
Date: Sat, 27 Mar 2021 05:54:59 GMT

```

#
###### Key Learnings
- Native image startup time is ~0.55s vs ~4.5s [<i>executable jar</i>] subjective to machine, which is pretty cool.
- The Generated native image is around ~182M [<i>using buildpacks</i>] vs ~148M [<i>native image plugin</i>] vs ~40M [<i>jar - ofcourse since JVM is provided outside of the jar on running machine</i>].
- Startup time is almost instantaneous - but build time is significantly higher - which possibly will improve over time as the project matures.
- Not all Spring modules /projects are supported yet - for ex. developer tools / AOP support missing [03/25] - things in motion already.
- Need significant RAM on the machine to play with Native support in the first place.
- No Out-of-the-box native image generation for pre-existing Spring Boot application - tweeks / testing will be required - though in long run that is the goal.
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
- Further image size could be reduced by using trimmed down tomcat [<i>experimental</i>] - ref pom.xml line 118.
- Oracle provides GraalVM Dashboard online tool to peek inside native image.
  ```sh
  -H:DashboardDump=dumpfileoversizedbuildArgs
  -H:+DashboardAll
  ```
  Only available when using native image plugin and not buildpacks to generate native image.
  [GraalVM Dashboard](https://www.graalvm.org/docs/tools/dashboard/?ojr=dashboard)
- Need to watchful around anything that's reflection. AOT[<i>Ahead-Of-Time</i>] compilation is actually analysing the entire application during build time[<i>hence increased build time</i>] - so it might miss some classes whose instantiation is decided at actual runtime instead[<i>lazy</i>], though there is <i>hint</i> support available to take care of these situations.
- Very good support from Community / good documentation Spring Native and GraalVM.

#
###### Links
- [GraalVM](https://www.graalvm.org/)
- [Spring Native](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/)
- [Spring Native Samples](https://github.com/spring-projects-experimental/spring-native/tree/master/samples)
###### More details to follow.