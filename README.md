# Simple Betslip App

Requirements

> A 3-way bet betslip for a sporting event between Team A and B. 

Data Model

* Betslip
     * id: Long 
     * userId: String (Unique) 
     * name: String Team-Name (Unique)
     * double: payoutFactor
     * double: amount
     * creationDate: Date
     * updateDate: Date
     * createdBy: String
     * updatedBy: String
     * version: Long
     

REST API:
* @GET      /betslip Return all Betslip entries (sorted by 'creationDate')
* @POST     /betslip Create new betslip entry
* @PUT      /betslip Update existing betslip entry
* @DELETE   /betslip/{id} Delete existing betslip

## Technology Stack

* Java 1.8
    * Streams 
    * Lambdas
* Third Party Libraries
    * Spring Boot (2.0.4.RELEASE)
    * Commons-Lang3 (Apache License)
    * Junit (EPL 1.0 License)
* Build Tool
    * Maven (Apache License)
* Code-Analyses
    * Sonar
    * Jacoco
* VCS 
    * GitHub (Public)
    
## License

MIT (unless noted otherwise)
