# Online Course Recommendation
A website which recommends online courses from multiple sources, like coursera, udemy, based on provided job descriptions.

## Motivation
This idea is inspired from my personal experience with job hunting. It is important for job seekers to know what skills a position needs or a company needs as a software enginner and this would help them know what they should prepare. During the time of job hunting, I used to collect all the job descriptions posted from interested companies, go through all the required skills listed out there and get started to pick up them by learning from online courses like Coursera, Udemy and so on. Learning from online courses might not make you proficient with a skill but that is a good start point.

I believe there are many people doing the same thing like I did but the process of collecting skills in job descriptions and searching online courses from websites is not quite easy. That is what our project would like to opitimize...

## Requirement
User can submit a job description \
User can view recommended online courses for each skill order by its frequency

## Design
* web architecture
  * Represent Layer: HTML5, CSS, React
  * Business Logic: Spring, SpringMVC, SpringBoot
  * Data Access Layer: Elasticsearch Java API
  * Data Store: ElasticSearch  
* data model \
Course (course name, description, url, skills, score, enrollment, level, price, source)
* data store
* data scraping
   * sources: Coursera (more in future)
   * technology: HtmlUnit, XPath
   * when to scrape and scrape how much data
   * what if source data updated
* cache
* business logic 
  * how to extract skills from job description 
  * rank criteria
## Demo

## Future work


