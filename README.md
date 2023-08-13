# Tindar: How the Cool Kids Date
This project was made for a software design course. The aim of the project was to learn about good software design and its importance via making a group project.

## Project description
In the finished project: users should be able to sign up, log in, edit their profiles, like profiles, and match when both users like each other's profiles, view their matches and converse with people they've matched with.

This project was mostly intended to be a learning experience in software design rather than an exercise in learning to use as many new tools as we could. We used Java rather than Kotlin because we wanted to focus on how to make good software given a language, rather than on learning a new programming language. We used SQLite since it was readily available, but the project is designed such that it should be reasonably easy to swap that out for a different database.

### Design
#### Overall design
This project was one for a course emphasizing the importance of Clean Architecture (CA) and SOLID. This is why our packages are organized into layers then features. While this was a hassle at times, writing in a very modular manner made testing and refactoring far easier.
#### Design patterns
We won't list every example of design pattern use, but we'll note a few of them.
- **Dependency injection**: in many cases (e.g. our database request and response models), we avoid hardcoding method parameters by passing around objects that follow a particular interface. One example: the class TindarMessage. They're used in every chat-related class to pull or pass message details, but only DatabaseHelper.java creates TindarMessage objects.
- **Strategy**: there was a variation on strategy used for displaying messages to vs. messages from the user. Instead of writing an explicit interface (we weren't familiar with front-end and couldn't figure out how to best express the desired layout through an attribute or class method), all the message xml layout files have the same names for analogous sections.
- **Observer**: there was an observer pattern used in Chat, but it was ultimately removed from the final product because the other observers would be on other devices and this app runs locally.

### Challenges and future directions
All the developers were new to Android front-end development, so learning front-end for this project was a challenge. Using tutorials, overcoming perfectionism and the need to perfectly understand everything we write, and asking each other for help were all essential to our progress.

If we were to continue this project, it would be interesting to explore:
- How to manage both a client and a server, and the possibility of many simultaneous users.
- How to reduce and handle privacy issues and security vulnerabilities.
- Automatic moderation for things like unsolicited pictures of sexual natures.
- How to design a recommender algorithm that keeps people on the dating app for as long as possible by giving them just enough matches that are just good enough to give them hope, but not so many high-quality matches that they find fulfilling relationships and stop using the app.  🙃

## Installation and running the project
Though this is supposedly an Android app, we haven't actually tried running this on a real Android device yet. We have been testing and developing this app using emulators in [Android Studio](https://developer.android.com/studio/install).

Plugin needed
- Lombok Plugin is needed for the Android Studio. Follow the instruction from https://projectlombok.org/setup/android

To run the app on the Android Studio
- Open an Android Studio
- Go to Tools > Device Manage and click on Create device. Make sure the Virtual tab is selected.
- Select Phone > Pixel 6 as Hardware and click Next
- Make sure Recommended tab is selected and select API 34 as System Image. Click Next.
- Click Finish
- Run the app on the Pixel 6 API 34 device

## Demo
- To test the app functionality, you can use the following credentials to login
  - email: jack@someemail.com
  - password: password_jack

## Credits
### Contributors
- https://github.com/jenniferjinyoungyang
- https://github.com/candidcalcifer
- https://github.com/kresimirgotovac
- https://github.com/A-Bunch-Of-Atoms
- https://github.com/navarhontes (the one writing this; blame them for anything wrong here)
- https://github.com/yiwxng

### References
Some reference materials that were used in the development of this project:
- [Beginner Android Programming (Java)](https://www.youtube.com/playlist?list=PL_c9BZzLwBRJLm0QETVj_XcN4jRsV4LkR)
- [RecyclerView | Everything You Need to Know](https://www.youtube.com/watch?v=Mc0XT58A1Z4)
- [Local Databases with SQLiteOpenHelper by CodePath](https://guides.codepath.com/android/local-databases-with-sqliteopenhelper)
- [Android Documentation](https://developer.android.com/)
