# FRC 8775 Robotics - 2022 Offseason Code
This repository maintains our robot's 2022 offseason code which we are using to participate in offseason rapid react games.

## Goals and Improvements over regular season
This code has had, and will have lots of improvements over our 2022 regular season code.
 - Move to Command-Based
 - 20-second automated poistioning and 4-bar climb
 - Computer vision assisted automated cargo tracking and pickup
 - Computer vision assisted automated target alignment and shooting
 - 5-ball auto-period

 ## About the Rapid-React Game
 A few resources explaining more about the FRC rapid react game.
 - [Explanation video](https://www.youtube.com/watch?v=LgniEjI9cCM) - A quick explanaion of the rapid react game.
 - [Game manual](https://firstfrc.blob.core.windows.net/frc2022/Manual/2022FRCGameManual.pdf) - This all of FRC's information on the game.

## Necessary Software
There is some software that you will need to work on the project. It is recommended that you install software as instructed in on WPILIB's [website](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/wpilib-setup.html). This will setup the necessary tools for you to develop in vscode.

If you want however, you can develop in any environment that suites you, as long as you download the necessary components (JDK, etc.).

## Documentation for WPILIB and other libraries
Documentation for of the important libraries used in this project.
 - [WPILIB Documentation](https://docs.wpilib.org/en/stable/index.html) - This is the FRC provided control system for our robot. It is very important to understand how it works both software and hardware. Be sure to check out the [Command-Based](https://docs.wpilib.org/en/stable/docs/software/commandbased/index.html) section as that is the system that is used in this project.
 - [PhotonVision](https://docs.photonvision.org/en/latest/) - We use PhotonVision in the project to handle vision processing on our robot.

## Running Unit Tests
Unit tests can be run from the command line by using the gradle test task.\
If you are on Windows: `.\gradlew.bat clean test`\
If you are on Linux: `.\gradlew clean test`

They can also be run from the GUI of vscode using the "Test Runner for Java" extension.

## Resolving Merge Conflicts

Stuff goes here

See something like this?

![image](https://user-images.githubusercontent.com/58612/178773622-c5c66379-4020-47f0-aa52-68d22b86744e.png)

DO NOT click that "Resolve Conflicts" button. Unfortunately GitHub makes resolving merge conflicts harder than it needs to
be, but don't worry! You can follow these steps and get your branch up to date
quickly.

To do this you will need to run some commands in a terminal. VS Code has one you can access or you can use your system's terminal emulator (Windows Terminal on Windows 10+ or Terminal on OS X).

1. Ensure your local main is up to date.

```
git checkout main
git pull
```

2. Switch to your branch. Make sure to use your actual branch name in the command.

```
git checkout your-branch-name
```

3. Replay your commits on top of the current main. This is different from what GitHub instructs you to do, but this is the "correct" way to bring a branch up to date cleanly.

```
git rebase main
```

When you run this command it may ask you to manually resolve merge conflicts. It will show you what the change on main was alongside your change that conflicts with it. You'll need to manually edited those then `git add` each file once it looks good.

4. Update your remote branch with the changes.

```
git push --force-with-lease origin your-branch-name
```

That should do it! If you run into any issues with this please ask in our Discord server and someone will help you out.
