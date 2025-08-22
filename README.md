# KinkDin - LeetCode Leaderboard App

A website+app that aggregates your peers LeetCode profiles, computes points, and creates a friendly leaderboard competition. Built with Kotlin, Jetpack Compose, and modern Android architecture.

## Features

- **Friend Profiles** - Track usernames, solved counts, contest ratings
- **Weekly Leaderboard** - Rank-based competition with tie handling
- **Offline-First** - Local caching with remote sync
- **Testable Architecture** - Clean MVVM with UseCase pattern
- **Role-Based Access** - User, Premium, Admin roles

## Tech Stack

- **Language:** Kotlin 
- **UI:** Jetpack Compose + Material3
- **Architecture:** MVVM + Clean Architecture
- **DI:** Hilt
- **Networking:** Retrofit + OkHttp
- **Database:** Room
- **Async:** Kotlin Coroutines + Flow

## Project Structure

```
app/src/main/java/com/mkj/kinkdin/
├── datamodel/          # Data models
├── navigation/         # Navigation components
├── screens/           # UI screens (Leaderboard, Splash)
├── ui/theme/          # UI theming
├── viewModel/         # ViewModels (Auth, User)
└── res/               # Android resources
```

## Quick Start

### Prerequisites
- Android Studio Flamingo+
- JDK 17+
- Android SDK 24+

### Setup
1. **Clone & Open**
   ```bash
   git clone <bhai link de doo pls>
   cd KinkDin/AppPortal/KinkDi
   ```
   Open in Android Studio

2. **Build & Run**
   - Sync Gradle
   - Run on emulator/device
   - App starts with Splash → Login/Leaderboard

## Points System

Points are calculated based on problem difficulty and acceptance rate:

```kotlin
// Easy: 10, Medium: 25, Hard: 50 base points
// Acceptance rate multiplier: <30% = 2x, <50% = 1.5x, <70% = 1.2x
```

## Configuration

### Environment Setup
```gradle
// app/build.gradle.kts
buildTypes {
    debug {
        buildConfigField "String", "BASE_URL", "\"https://api.dev.example.com/\""
    }
    release {
        buildConfigField "String", "BASE_URL", "\"https://api.example.com/\""
    }
}
```

### Authentication
- Store tokens in EncryptedSharedPreferences
- AuthInterceptor attaches JWT to requests
- Role-based access control

## Testing

- **Unit Tests:** PointsCalculator, UseCases, ViewModels
- **UI Tests:** Espresso for screen interactions
- **Database:** Room Inspector for debugging



## Contributing

1. Fork the repository
2. Create feature branch: `wait for me wait for me`
3. Add tests for business logic
4. Submit PR with clear description

## Roadmap

- Progress charts and streaks
- Achievement badges
- Push notifications
- Web dashboard
- Social features

## Contributors

![contribution](https://i.ibb.co/FN786Lw/1d6e4069-6ec1-4103-b15e-84228023edda.jpg)


- **Mohammad Yavar** - Frontend Molester 
- **Priyank Gupta** - Works on Minor
- **Samriddha Kumar Tripathi** - Backshot Handler
- **Mradul Gupta** - appy lover
- **Sumit Rajput** - tsxx Obsessed
- **Gagan Deep Yadav** - Main Developer
- **Rupesh Chaurasia** - hidden Member


**Note:** Still Cooking 