# Just Workout
Workout database app to assist in coming up with fresh and effective strength training workouts.
[Trello Board (private)](https://trello.com/b/qDzkvYYl/just-workout-android)
## Project structure
The app is not technically modularized but the package structure mimics how I would modularize it. This keeps responsibilities clear and if I were to actually modularize in the future it would be pretty simple.
- `app/.../justworkout/`
    - `models/` - Domain models common to the entire app
    - `database/` - Room databse Entities and DAOs
    - `repository/` - Abstraction layer to decouple data fetching from the core app 
    - `di/` - Dependency injection utilities and shared components
    - `ui/` - Composables and ViewModels
    - `utility/` - Shared utilities

## Dev docs
Taking some architectural inspiration from [skydove's pokedex project](https://github.com/skydoves/Pokedex).

[View Models and State in Compose](https://developer.android.com/codelabs/basic-android-kotlin-compose-viewmodel-and-state#10)

[Guide to App Arch](https://developer.android.com/topic/architecture)

[Compose Previews with ViewModels](https://developer.android.com/jetpack/compose/tooling/previews#preview-viewmodel)

[Compose Destinations Library Docs](https://composedestinations.rafaelcosta.xyz/)

[Compose Destinations article explaining the navArgsDelegate feature](https://proandroiddev.com/compose-destinations-simpler-and-safer-navigation-in-compose-with-no-compromises-74a59c6b727d)

[Room db article on defining relationships](https://developer.android.com/training/data-storage/room/relationships)
