# lidl-search
Lidl Hackathon 2019

## Who are we?
We are team Rashko: Rally & Sashko

## What is this?
A Hackathon project for a competition hosted by Lidl Digital in 2019.

## What's the goal?
Create a product search (front-end and back-end) which is scalable and easy to use.

## Implementation
The implementation will consist of front-end and back-end.

### Front-end
The front-end will consist of a simple search box with autocompletion for commonly searched
terms and visualisation of the results (as product boxes). This part will be implemented with
HTML and JavaScript.

### Back-end
The back-end will consist of a server with RESTful API which serves AJAX requests from the front-end.
There will be several types of requests:
* Auto-complete: Given a string (what the user has entered in the search box) return a list of 5
most relevant products.
* Product page: Given a string (what the user has entered in the search box before clicking Search)
return a list of all products, sorted by relevance, that match this search string.
The back-end part of the project will be implemented with Java 8 and Spark as a web-server.
