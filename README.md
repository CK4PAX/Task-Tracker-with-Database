# Task-Tracker-with-Database

Hi, this is a project that follows the requirements published at [RoadMap projects](https://roadmap.sh/projects/task-tracker). 

In this case, I have tried to make it with a database. Therefore, I've used **SQLite3**. Additionally, I've developed it using **TDD (Test-Driven Development)**, so there is a pair of files with tests (I've used **JUnit5**). I appreciate any feedback, so thanks in advance!

---

## How to Execute

Use the following command:
```bash
java -jar TaskTracker.jar <options>
````
Use this options 
	-add <"task">
	-delete <task-id>
	-update <task-id> <"updated task">
	-list
	-list todo
	-list done
	-list in-progress
	-mark-done <task-id>
	-mark-in-progress <task-id>
If you enter a bad command, CLI will help you to correct it.
