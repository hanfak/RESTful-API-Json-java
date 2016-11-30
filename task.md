Description

This exercise is about building a simple JSON data stream store called GreedyDB. Basically, GreedyDB can ingests several stream of timed JSON events and makes them available for queries via some REST endpoints.

A timed JSON stream is made of a name, which uniquely identifies the stream within GreedyDB and a set of JSON documents compliant to the same arbitrary format. The only constraint is that a timestamp field must be present. For example, a stream called employers, would look like the following sequence of JSON objects:

{ “timestamp”: 1460463641, “id”: 88837264, “name”: “Davide”, “surname”: “Palmisano" }
{ “timestamp”: 1460465692, “id”: 667732, “name”: “Jonh”, “surname”: “Foobar" }
{ “timestamp”: 1460462644, “id”: 1180023, “name”: “Davide”, “surname”: “Palmisano" }

Another possible stream could be a stream called clicks like the following:

{ “timestamp”: 1460442540, “page”: “/login.html", “origin”:  { “brand”: “hcom”, “pos”: “UK" } }
{ “timestamp”: 1460422614, “page”: “/error.html", “origin”:  { “brand”: “hcom”, “pos”: “US" } }
{ “timestamp”: 1460492344, “page”: “/profile.html", “origin”:  { “brand”: “venere”, “pos”: “UK" } }

Please note that both JSON streams include the timestamp field.
Once these streams are ingested into GreedyDB, would be possible to answer queries like:

“what’s the latest value for the field page for the clicks stream?"
“what’s the value for the field origin.brand at timestamp 1460422614 for the stream clicks?"
“what the oldest value for the field name for the employers stream?"

Please note that GreedyDB will only keep JSON events no older than 30 minutes.

Keep in mind that we love testable code which can be run easily. Don’t worry if you can’t cover all the requirements but make sure that your code has unit tests and is well designed.

Requirements

Write a java standalone application with the following requirements and characteristics:

1) GreedyDB will expose a REST endpoint (/import/{streamName}) that will allow to import a single JSON event, where {feedName} is the name of the feed.
2) GreedyDB will expose a REST endpoint (/query/{streamName}/{timestamp}/{jsonPath}) that will return the value of the field specified with the JSON Path for the given stream on a given timestamp.
3) GreedyDB will expose a REST endpoint (/query/{streamName}/latest/{jsonPath}) that will return the latest value of the field specified with the JSON Path for the given stream.
4) GreedyDB will expose a REST endpoint (/query/{streamName}/oldest/{jsonPath}) that will return the oldest value of the field specified with the JSON Path for the given stream.

It’s up to you how to model errors with HTTP status codes. For example if a stream is not found, the query endpoints should return a 404.
