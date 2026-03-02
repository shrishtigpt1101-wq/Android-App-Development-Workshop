# Android App Development Workshop - ToDo App

A simple ToDo Android app built with **Kotlin**, **Jetpack Compose**, and **Room**.

This project demonstrates a clean beginner-friendly architecture using:
- UI layer with Compose
- State management with ViewModel + StateFlow
- Local persistence with Room database
- Repository pattern between ViewModel and data layer

## What Is Implemented

- Add a new task
- View tasks in a list (latest first)
- Mark task as done/undone
- Delete task
- Persist data locally using Room (`todo_db`)

## Tech Stack

- Kotlin
- Jetpack Compose (Material 3)
- AndroidX Lifecycle + ViewModel
- Kotlin Coroutines + Flow
- Room (DAO, Entity, Database)
- Gradle Kotlin DSL

## Project Architecture

`MainActivity -> TodoViewModel -> TodoRepository -> TodoDao -> Room Database`

Data flow:
- `TodoDao.getAllTodos()` returns `Flow<List<TodoEntity>>`
- Repository maps entities to domain model (`Todo`)
- ViewModel exposes `TodoUiState`
- Compose UI collects state and renders list/reacts to actions

## Project Structure

```text
app/src/main/java/com/pulak/todoapplication/
|- MainActivity.kt
|- data/
|  |- TodoEntity.kt
|  |- TodoDao.kt
|  |- TodoDatabase.kt
|  |- TodoRepository.kt
|- domain/
|  |- Todo.kt
|- ui/
   |- TodoScreen.kt
   |- TodoViewModel.kt
```

## Requirements (What You Need)

- Android Studio (latest stable recommended)
- JDK 17 installed and configured for Gradle
- Android SDK with:
  - `compileSdk = 36`
  - `targetSdk = 36`
  - `minSdk = 24`
- Gradle will be used via wrapper (`gradlew` / `gradlew.bat`)

## How To Run (Android Studio)

1. Clone the repo:
   ```bash
   git clone https://github.com/shrishtigpt1101-wq/Android-App-Development-Workshop.git
   ```
2. Open the project in Android Studio.
3. Let Gradle sync complete.
4. Run on emulator or physical device.

## Build and Test (CLI)

From project root:

### Windows

```powershell
.\gradlew.bat assembleDebug
.\gradlew.bat test
```

### macOS / Linux

```bash
./gradlew assembleDebug
./gradlew test
```

## Package and App Info

- Application ID: `com.pulak.todoapplication`
- App name: `todoapp`
- Database name: `todo_db`

## Future Improvements

- Edit existing task title
- Task filtering (All / Active / Completed)
- Due dates and reminders
- Better UI states (loading/error/empty enhancements)
- UI tests for core flows

---

Workshop project for Android app development practice.
