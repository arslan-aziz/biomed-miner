# Biomed-Miner

## Project Overview
---
Biomedical research strives to deepen our understanding of human biology as well as translate that understanding to novel therapies and technologies for use in the medical industry. However, the interdisciplinary nature of the field poses extra challenge to researchers, academics, and students in being able to stay abreast of the latest bodies of work across the various domains related to their research. Extensive effort is required to "keep an eye out" for tangential but potentially relevant work that could lead to the next big project or idea.
<br><br>
The **aim** of _Biomed-Miner_ is to provide users an interactive and intuitive experience in exploring the plethora of information related to and branching from a particular biomedical topic leveraging the online repository of biomedical literature and natural language processing (NLP).
<br><br>
End state user story (abridged): Users query the application related to particular biomedical topics and receive interpretable data visualizations from which they can interactively branch and expand their exploration.

## Implementation
---
### Current State
Check out a demo of the application with mock data here!
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
