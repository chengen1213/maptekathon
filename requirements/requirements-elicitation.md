# Maptek-a-thon (Hackathon Framework)        

This app is inspired by ​TopCoder in the form of a virtual Hackathon. Maptek would like to be able to post input and output data and allow for programmers to submit solutions that are the most performant. The submissions would then be automatically ranked and displayed on a leaderboard. Maptek developers primarily program in Java, C++ and C#, however the platform is expected to be extensible to accept submissions of other languages. 

## Contact person of the team

*TODO:* Please name one contact person in the team that I can reach out to regarding questions about requirements.

Contact person for PG7:

Qiaoqiao Liu ([qiaoqiao.liu@student.adelaide.edu.au](mailto:qiaoqiao.liu@student.adelaide.edu.au))

## Additional Details

### User Roles
* Role 1: Maptek admin
* Role 2: Contestant/submitter/customer/coder

### Description of System

* Competition-style online [hackathon](https://en.wikipedia.org/wiki/Hackathon), focused on large data sets, with deadline for contest completion
* Similar to [Web Submission System](https://cs.adelaide.edu.au/services/websubmission/) of the School of Computer Science or [Zealy](https://zealy.io/) (formerly known as Automark), but with leaderboard
* See also [CodingBat](https://codingbat.com/java), [TopCoder](https://www.topcoder.com/), [Kaggle](https://www.kaggle.com/competitions), [CodeChef](https://www.codechef.com/ide), and [this challenge](https://dysdoc.github.io/docgen2/index.html) for inspiration
* Code is uploaded for evaluation/measurement through a simple web interface, no SVN or Git(Hub) connection required
* Admin: Interface to define a problem and upload a public and a corresponding private dataset (contestant can code and test against public dataset, performance measurement will be against private - and perhaps public - dataset)
* The uploaded code is executed with the private dataset and analysed for execution time and memory footprint
* Submissions must be possible in Java, C++ and C#

### Technical Details
* Web application
* Web interface: emphasise function over form
* Established implementation stack, not too offbeat (could be Ruby on Rails, ASP.NET, Jakarta EE, Python Flask, etc.)
* Authentication with email and password (stored securely) but no OAuth required
* Compile and execute the submitted code as a process spawned from the web application.service plus a process monitoring the performance of the executed code
* Wrap third-party tools (gcc, javac, etc.) to compile/run the submitted code
* Security of code execution!

## Update (26/08/2019)

* The Maptek-a-thon is purely a public hackathon system (private challenges are not part of the requirements for this project)
* Submissions that do not pass all tests should still be admissible to the leaderboard (with a somehow reduced score)
* The leaderboard of a given challenge could have (potentially) multiple submissions from a single user, but the system should only retain their best score
* Options to achieve "Security of code execution" include:
  * Externalising code execution to a separate server, which then sandboxes the execution
  * Executing the code in a sandboxed thread on the same server
  * The client is also open for other approaches you propose
  * Security of code execution should be at least considered in the design, even if the actual implementation may not be possible in the short time frame of this project

## Update (30/08/2019)

* The admin *may* choose to specify a specific language for a certain challenge, but in most cases all languages will be supported
* Regarding code submission, please clarify whether you will support an online form similar to [CodeChef](https://www.codechef.com/ide) or rather a file upload. If you've decided to do the latter, will it be possible to upload multiple files or just one? What about file names?
* How do you handle library dependencies? Will you limit those to the standard libraries or also allow to add functionality from additional libraries?
