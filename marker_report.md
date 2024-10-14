# The Assignment Three marker's report:

4 member group

 - Task 0: Game Skeleton (10/100) -- 9
     - gradle-based project; builds uneventfully (why two jar's?)
     - (should include `build` dir into .gitignore)
     - plays alright ("fake" terminal -- dumps a new frame on every step)
     - README could be more contentful (a bit of everything)

 - Task 1.1: User Stories/Features (10/100) -- 7
     - 4 basic US's OK
     - additional F's -- is writing unit tests a feature?
     - no priorities, no assignees, no timing

 - Task 1.2: Work Planning (18/100) -- 15
     - trunk-based (inferred by the marker)
     - planning OK
     - minutes OK; tend to be more cursory at later stages; not a PBI style format
     - no claims re using agile approach; no mention of running sprints

 - Task 2: Project Work, Git/Repo History, Coding Style (40/100) -- 36
     - 24 issies (3 not closed), labelled (mainly by their state) used but not consistently
     - 5 milestones, only 1 closed? 
     - really active only in the last week; commits from all memeber, with Chriso and Qiutong making most
     - surpisied about the problems with JDK21->JDK17 transition (Idea allows to separate language and SDK version --
       did you try that?) -- one would like to know the details; gradlew bug in Dockerfile is likely due to
       not using the image layer but copying it from the host (mentioned above)
     - overall -- the postmortem is quite useful read

 - Task 3.1: Project Documentation (8/100) -- 5
     - somewhat short, no screenshots, no detailed gaming structure/rules/strategy explanation

 - Task 3.1.1: Project License (2/100) -- 2
     - Apachee, the choice is explained

 - Task 3.2: JavaDocs code documentation (6/100) -- 6
     - consistently included javadoc's with the standard tags (`@link` is rare, `@code` isn't used at all)

 - Task 3.3: Testing, Unit Tests, jUnit (4/100) -- 4
     - 23 unit tests (for 23 classes -- could be more?); basically OK
     - BUT -- provides a scenario (simulated user inputs) game tester (kudos!)

 - Task 4: Containerisation (`Dockerfile`), CI pipleline (`.gitlab-ci.yml`)  (2/100) -- 2
     - both basic (why not to include gradle layer into the image? same for gson)

 - Task 5: "Essay on Modernising an old application/Long Term Maintenance"
         (20% scaled-in, marked separately)
