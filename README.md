# Inkstep Server Backend
Backend server for inkstep


[![CircleCI](https://circleci.com/gh/inkstep/backend.svg?style=svg)](https://circleci.com/gh/inkstep/backend)
![](https://img.shields.io/badge/database-postgres-purple.svg)
![](https://img.shields.io/badge/dependancies-gradle-green.svg)
![](https://img.shields.io/badge/container-docker-blue.svg)
![](https://img.shields.io/badge/server-aws-yellow.svg)
![](https://img.shields.io/badge/project-inkstep-black.svg)

url for HTTP requests

<http://inkstep-backend.eu-west-2.elasticbeanstalk.com/>

# Scripts
Run ./install to install necessary programs

Run ./clean to clean the directory

Run ./run to run the server

# API Endpoints

-
## /journey

#### `GET /journey`

Returns details of a current journey. _Note_: v1 just returns prefilled data

#### `PUT /journey`

Creates a new journey object. Returns an empty json {}

#### JSON params

| Param | Description |
| ---- | ------ |
| `user_id` | The user id |
| `artist_id` | The id of the artist |
| `tattoo_description` | What the tattoo will be of |
| `size` | Size of the tattoo |
| `position` | Position on the body of the tattoo |
| `availability` | The availability of the user |
| `deposit` | Whether the user is willing to put down a deposit |
| `ref_images` | The number of reference images needed |


## /user
#### `PUT /user`

Creates a new user in the database. Returns a json {"user_id":"1", "passphrase":"XXX"}

#### JSON params

| Param | Description |
| ---- | ------ |
| `user_name` | The username |
| `user_email` | The email |
