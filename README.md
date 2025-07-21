📘 Data processing a Student Data set with Stream operations:

UML Class chart: https://github.com/Shamelhc1/StudentSystem_DataProcessing_with_Streams/blob/master/StudentSystemUL.png

This project genetates a data set of Random Student, with Course and an Engagement tracker class:
- The data set is mainly generated using Streams.generate() 
- Based on the generated data, I used intermediate and terminal operations to query and extract certain useful data.
- what percentage of male/ female students?
- how many students are in the age range of 30 to 60?
- what are the age statistics of the student data, i.e. average, min, max.
- Are there students that are still active and have been enrolled for more than 7 years




Classes:

Student

└── id: int

└── CountryCode: String

└── enagementMap: Map<String, courseEngagement>

└── yearEnrolled: int 

└── ageEnrolled: int 

└── gender: string
...


Course

└── name: String

└──title: String 

└──lectureCount: int

CourseEngagement

└──course: Course

└──lastActivityDate: LocalDate 

└──enrollmentDate: LocalDate

└──lastLecture: int

└──engagementType: Strong




