# Food for Thought

## Project Overview
---
We have all been in conversations before with family and friends on diet and nutrition. Discussion arises around what to eat when dealing with ailments such as diabetes or hypothyroidism, or nutrition to achieve a particular lifestyle. A quick search on Google returns thousands of blogs with too much information to parse through...
<br><br>
The **aim** of _Food for Thought_ is to build an accessible means to understand and digest the plethora of information on the internet related to diet and nutrition leveraging open source biomedical literature and natural language processing (NLP).
<br><br>
End state user story (abridged): Users can query the application related to particular ailments or disease and receive interpretable data visualizations to help inform their decision-making with respect to diet and nutrition.

## Implementation
---
### Summary
_Food for Thought_ is a web application where the query options and data visualization are presented on the client-side (using D3 and React). 


## Current State
Check out a demo of the application with mock processed results here!
The backend is implemented using Spring Boot which exposes an API for the client to request processing of queries and to retrieve results. The backend currently uses Spring resource management to store data on the server's file system, and maintains a map in memory of resource uid's to file paths. The application stack is currently hosted on AWS where the client-side is statically located on an S3 bucket and the web server runs on an EC2 instance.

### Current Tooling
* D3.js
* React.js
* NPM
* Java Spring Framework (Spring Web, Jackson)
* Maven

## Upcoming Features/Development
---
1. Implement ETL workflow to move beyond serving manually-created results with the following elements in consideration:
	- Scalability of storage for raw data.
	- Efficiency of query and/or search on text data.
	- Batch vs. streaming analytics for data transformation.
1. Within the data transformation workflow, explore entity extraction approaches for the relevant biomedical entities.
