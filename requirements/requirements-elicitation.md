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
* Compile and execute the submitted code as a process spawned from the web service plus a process monitoring the performance of the executed code
* Wrap third-party tools (gcc, javac, etc.) to compile/run the submitted code
* Security of code execution!
