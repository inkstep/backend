# Backend
Backend server for inkstep

url for HTTP requests 

http://inkstep-backend.eu-west-2.elasticbeanstalk.com/

# TOOLS

Using CircleCI for CI/CD

[![CircleCI](https://circleci.com/gh/inkstep/backend.svg?style=svg)](https://circleci.com/gh/inkstep/backend)

Using Gradle for Dependancies
Using Docker for containerisation
Using AWS for server hosting

url for HTTP requests inkstep-env.5zmmfs3yms.eu-west-2.elasticbeanstalk.com

PostGre for database

CATION: To access the database with this application create a password.txt file in the root directory with the valid PostGre credentials.

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
| `tattoo` | Brief description of the tattoo |
| `size` | Size of the tattoo |
| `pos` | Position on the body of the tattoo |
| `desc` | Description of your journey request |

