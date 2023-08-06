# Tindar: How the Cool Kids Date
This project was made for a software design course. The aim of the project was to learn about good software design and its importance via making a group project.

## Project description
In the finished project: users should be able to sign up, log in, edit their profiles, like profiles and match when both users like each other's profiles, view their match list, and converse with people they've matched with.

This project was mostly intended to be a learning experience in software design rather than an exercise in learning to use as many new tools as we could. We used Java rather than Kotlin because we wanted to focus on how to make good software given a language, rather than on learning a new programming language. We used SQLite since it was readily available, but the project is designed such that it should be reasonably easy to swap that out for a different database.
### Challenges and future directions
This project doesn't fully work yet. I think fixing that is our main priority.

Once the basics are done, it would be be interesting to explore:
- How to manage both a client and a server, and the possibility of many simultaneous users.
- How to reduce and handle privacy issues and security vulnerabilities.
- Automatic moderation for things like unsolicited pictures of sexual natures.
- How to design a recommender algorithm that keeps people on the dating app for as long as possible by giving them just enough matches that are just good enough to give them hope, but not so many high-quality matches that they find fulfilling relationships and stop using the app.

## Installation and running the project
Though this is supposedly an Android app, we haven't actually tried running this on a real Android device yet. We have been testing and developing this app using emulators in [Android Studio](https://developer.android.com/studio/install).

I have no idea how to check which versions of android work with this app; someone please help. Here's the info that I've gathered which may or may not be correct:
- Gradle version 8.0
- Android Gradle Plugin Version 8.0.2
- Minimum SDK version 24

## Credits
### Contributors
https://github.com/jenniferjinyoungyang
https://github.com/candidcalcifer
https://github.com/kresimirgotovac
https://github.com/A-Bunch-Of-Atoms
https://github.com/navarhontes (the one writing this; blame them for anything wrong here)
https://github.com/yiwxng

### References
Some reference materials that were used in the development of this project:
- [Beginner Android Programming (Java)](https://www.youtube.com/playlist?list=PL_c9BZzLwBRJLm0QETVj_XcN4jRsV4LkR)
- [RecyclerView | Everything You Need to Know](https://www.youtube.com/watch?v=Mc0XT58A1Z4)