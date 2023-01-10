# **First Year Finder**

## Project Description

I propose a desktop application that will help connect students with others who share similar interests. This application will do a *better job* at connecting first-year students than the typical floor meetings because it allows students to quickly identify people who they would likely enjoy spending time with. This will be accomplished by creating a social database of students in first-year residences. Each student will be able to add their profile to the database. The application will be able to match similar students together who are ideally in the same residence buildings. Helping to add to the overall first-year experience, the application will allow students to find friends who are interested in similar activities, in the same major, or just in the same residence building.

This application is of interest to me because, during my first year, I got very lucky and was paired with a roommate who shared a love for the outdoors. Unfortunately, this is not the case for many students who struggle to initially find friends on campus. I seek to enhance students’ initial experience on campus and connect them with people they would not have otherwise met. Any given student would be able to create a social profile which shares their major, extracurricular interests, residence and/or social media contact with other first-year students in the residences. Each student will be able to create and publish a profile to the application. Users should be able to sort through the profiles to find people with similar interests. Once the user has found a person who they believe they would get along with, they would be able to reach out using the social media attached to the given person's profile. After finding a likely good friend, this application will have successfully done its part in connecting the first-year students.

## User Stories

- As a user, I want to be able to create my own profile and add it to the database/list of other students.
- As a user, I want to be able to filter through the list of students to find people with similar interests.
- As a user, I want to be able to filter through the list of students to find people living in a specific residence.
- As a user, I want to be able to filter through the list of students to find people in the same faculty as I am.
- As a user, I want to be able to delete my own profile.
- As a user, I want to be able to make edits to my own profile.
- As a user, I want to be able to save and add student profiles to a list of my own for future reference.
- As a user, I want to see the list of all the other students or all of the filtered students.
- As a user, I want to be given the option to save my data for a later date when I am quitting the application.
- As a user, I want to be able to upload data from a file or start fresh when I open the application.

# Instructions for Grader

- You can generate the first required event related to adding Students to a ListOfStudent by filling in the 5 fields and clicking add student. Note that Res # must be an integer from 1-3
- You can generate the second required event related to adding Xs to a Y by selecting a student and pressing remove student.
- You can filter through students by residence or by interest using the appropriate fields and button.
- You can locate my visual component by removing a student which reveals a popup with an image.
- You can save the state of my application by clicking the save list button.
- You can reload the state of my application by clicking the load list button.

## Phase 4: Task 2

Fri Nov 25 09:48:24 PST 2022  
Grady was added to the list.  
Fri Nov 25 09:48:24 PST 2022  
Jacob was added to the list.  
Fri Nov 25 09:48:24 PST 2022  
Oli was added to the list.  
Fri Nov 25 09:48:24 PST 2022  
Harper was added to the list.  
Fri Nov 25 09:48:24 PST 2022  
Ben was added to the list.  
Fri Nov 25 09:48:24 PST 2022  
Griffin was added to the list.  
Fri Nov 25 09:48:27 PST 2022  
Grady was removed from the list.  
Fri Nov 25 09:48:29 PST 2022  
Jacob was removed from the list.  
Fri Nov 25 09:48:34 PST 2022  
Saved list of students.  
Fri Nov 25 09:48:45 PST 2022  
List of students was filtered by residence: Totem Park.  
Fri Nov 25 09:48:45 PST 2022  
All students were removed from the list.  
Fri Nov 25 09:48:45 PST 2022  
Harper was added to the list.  
Fri Nov 25 09:48:45 PST 2022  
Griffin was added to the list.  
Fri Nov 25 09:48:48 PST 2022  
List of students filtered by interest: bucs.  
Fri Nov 25 09:48:48 PST 2022  
All students were removed from the list.  
Fri Nov 25 09:48:48 PST 2022  
Griffin was added to the list.  
Fri Nov 25 09:48:50 PST 2022  
Load saved list of students.  
Fri Nov 25 09:48:50 PST 2022  
All students were removed from the list.  
Fri Nov 25 09:48:50 PST 2022  
Oli was added to the list.  
Fri Nov 25 09:48:50 PST 2022  
Harper was added to the list.  
Fri Nov 25 09:48:50 PST 2022  
Ben was added to the list.  
Fri Nov 25 09:48:50 PST 2022  
Griffin was added to the list.

## Phase 4: Task 3: 
I believe the design of my UML diagram is fairly simple with very little coupling which shows good design. However, upon diving into my ListOfStudent class, I believe it has relatively low cohesion which might be improved in the future by separating the methods that filter the list into their own classes, since this functionality creates a whole new ListOfStudent and does nothing to the actual list itself.
### Potential Improvements: 
- Separate Filter by Residence and Filter by Interest methods into their own classes
- Add functionality to the GUI so that the ListOfStudent with all the students is saved before filtering so that it can be referenced again