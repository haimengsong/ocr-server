# Online Course Recommendation
A website which recommends online courses from multiple sources, like coursera, udemy, based on provided job descriptions.

## Motivation
This idea is inspired from my personal experience with job hunting. It is important for job seekers to know what skills a position needs or a company needs as a software enginner and this would help them know what they should prepare. During the time of job hunting, I used to collect all the job descriptions posted from interested companies, go through all the required skills listed out there and get started to pick up them by learning from online courses like Coursera, Udemy and so on. Learning from online courses might not make you proficient with a skill but that is a good start point.

I believe there are many people doing the same thing like I did but the process of collecting skills in job descriptions and searching online courses from websites is not quite easy. That is what our project would like to opitimize...

## Requirement
User can submit a job description \
User can view the top 10 best online courses from multiple sources with highest score for each skill order by its frequency

## Design
* web architecture
  * Represent Layer: HTML5, CSS, React
  * Business Logic: Spring, SpringMVC, SpringBoot
  * Data Access Layer: Spring Data JPA
  * Data Store: MongoDB  
* data model \
![alt text](https://github.com/haimengsong/ocr-server/blob/master/course.png)
* choice of data store \
choose MongoDB as our data store for the following reasons:
1. the schema of data model is not fixed and may change (compared with relational database)
2. no need to support join and transaction (compared with relational database)
3. write fast (compared with relational database)
4. easy to manage data (compared with elasticsearch)
* data management
   * sources: Coursera (more in future work)
   * technology: Quartz, HtmlUnit, XPath
   * architecture
![alt text](https://github.com/haimengsong/ocr-server/blob/master/datamanager.png)
   * workflow 
   1. Quartz starts data manager
   2. data manager starts url extractors
   3. url extractors extract url from db or web
   4. url extractors push urls into url queue
   5. scheduler consumes urls from url queue
   6. scheduler create tasks and run
   7. tasks ask fetcher to fetch data from web 
   8. tasks ask validator to validate if need parsing
   9. tasks ask parser to parse data
   10 tasks ask handler to write data into db
* cache \
we use Redis as in-memory database to store the top 10 best courses for each skill and each request can directly read the result from Redis
* business logic 
  * how to extract skills from job description 
  1. replace all non-alphanumerical character with empty space
  2. split words as string array
  3. count skill frequency based on pre-configured skill set as map
  * rank criteria \
currently we are just simply ranking the courses by its score
  
## Demo
## Github
Client Repo: https://github.com/haimengsong/ocr-client \
Server Repo: https://github.com/haimengsong/ocr-server
## Future work


