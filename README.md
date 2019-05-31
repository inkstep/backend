# Inkstep Server Backend
Backend server for inkstep


[![CircleCI](https://circleci.com/gh/inkstep/backend.svg?style=svg)](https://circleci.com/gh/inkstep/backend)
![](https://img.shields.io/badge/database-postgres-purple.svg)
![](https://img.shields.io/badge/dependancies-gradle-green.svg)
![](https://img.shields.io/badge/container-docker-blue.svg)
![](https://img.shields.io/badge/server-aws-yellow.svg)

url for HTTP requests 

http://inkstep-backend.eu-west-2.elasticbeanstalk.com/

# Scripts
Run ./install to install necessary programs

Run ./clean to clean the directory

Run ./run to run the server

# API (Currently supports v1)

# API Endpoints V1 (all preceded by `/v1`)

## User handling

#### `PUT /user`

Creates a new user in the database. Returns a json {"user_name":"XXX", "passphrase":"XXX"}

## JSON params

| Param | Description |
| ---- | ------ |
| `name` | The username |

## Journey

#### `GET /journey`

Returns details of a current journey 


## Query params

| Param | Description |
| ---- | ------ |
| null | null |

_Note_: v1 just returns prefilled data

#### `PUT /journey`

Creates a new journey object. Returns an empty json {}


## JSON params

| Param | Description |
| ---- | ------ |
| `user_name` | The username |
| `artist_name` | The name of the artist |
| `artst_email` | The email of the artist |
| `tattoo` | What the tattoo will be of |
| `size` | Size of the tattoo |
| `pos` | Position on the body of the tattoo |
| `desc` | Longer description of the desired tattoo |

