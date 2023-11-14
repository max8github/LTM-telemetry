# Overview
This entire directory is taken from folder `LTM-installation-v1/exercises` downloaded from Lightbend's Telemetry course.

> Note on Telemetry's use:  
  The app here will work as is, but note that in order to use Telemetry on it, you first have to create file `lightbend.sbt`, as 
  explained [here in credentials](https://lightbend.com/account/lightbend-platform/credentials)

# Customers Service 

This is a small Akka based application for managing customers as part of the Reactive BBQ microservice system.  
It is built using Akka HTTP, Akka Cluster Sharding and Akka Persistence.

# Running the Application
```shell
 sbt run
```
The default HTTP port here is `9551` (and Akka Cluster port is `2551`).  
Node `2551` must be running to form a cluster as it is the first seed node.

To run another instance on a different HTTP port:
```shell
 sbt "run 2552"
```
For this node, Akka remoting will run on port `2552`, the Customers service HTTP port will run on port `9552` (= 2552 + 7000) and Akka Management will run on port `8552` (= 2552 + 6000).

To run yet another instance:
```shell
 sbt "run 2553"
```
For this third node, Akka remoting will run on port `2553`, the Customers service HTTP port will run on port `9553` (= 2553 + 7000) and Akka Management will run on port `8553`.

And so on for more nodes.

# Using the Application
You can use the **[Postman](postman/Akka%20Telemetry.postman_collection.json)** collection and environment provided to exercise the system.  
Alternatively, you can use `curl` commands as described in [this page](docs/curl.md).

## Running Tests
```shell
 sbt test
```

## Running Gatling Simulation
```shell
sbt gatling:test
```
