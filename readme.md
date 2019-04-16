docker-machine ip default# URL Shortener Demo

This project is a simple URL shortener. The function is a simple web app that takes an input of a URL and outputs a custom link to use to redirect to the original URL. Redis was chosen to read from as it is an in-memory cache that allows for fast reads. MySQL was chosen as persistent storage to lookup previously submitted URL's and to backup redis to restore if needed.

  - Spring Boot
  - Java 8
  - Docker
  - redis
  - MySQL


### Installation

Thsi demo requires the following to be installed:
[docker](https://docs.docker.com/install/)
[docker-compose](https://docs.docker.com/compose/install/)
[docker-machine](https://docs.docker.com/machine/install-machine/)
Thats it =)

After installing the docker dependencies.

```sh
$ cd {PROJECT_ROOT}
$ docker-machine start default
$ eval $(docker-machine env)
$ docker-compose up
```
`docker-machine start default` Creates a vm to run the conainers in

`eval $(docker-machine env)` Sets the proper environment variables to run docker

`docker-compose up` Builds the containers and starts the services required for the web app

### Usage
To use the app after it is fully instantiated, simply navigate to the IP assigned to the "default" docker machine.
 
 **Get IP**
 
 ```sh
$ docker-machine ip default
192.168.64.2
 ```
 Note the IP provided
 
 **Browse**
 In a browser navigate to the IP address. i.e "http://192.168.64.2"

## Troubleshooting
After starting for the first time, sometimes the database doesn't initialize fast enough and you get JDBC connection issues.
To fix:

```sh
$ docker-compose down
$ docker-compose up
```

### Todos

 - Write MORE Tests
 - Add logging

