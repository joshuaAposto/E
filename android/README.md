# ShareBooster Android App

A modern Android application for boosting Facebook shares with a beautiful Material Design 3 interface.

## Features

- **User Authentication**: Login, registration, and Google Sign-In
- **Share Boosting**: Boost Facebook post shares with customizable intervals
- **Premium Features**: Upgrade to premium for unlimited shares
- **Profile Management**: Edit profile, change password, manage account
- **Real-time Updates**: Live share progress and status updates
- **Dark Theme**: Beautiful dark theme with gradient backgrounds
- **Modern UI**: Material Design 3 with custom components

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with Repository pattern
- **Database**: Room (SQLite)
- **Networking**: Retrofit + OkHttp
- **Authentication**: Google Sign-In
- **Dependency Injection**: Manual DI (can be upgraded to Hilt)
- **Image Loading**: Coil
- **Navigation**: Navigation Compose

## Project Structure

```
app/src/main/java/com/sharebooster/app/
├── data/
│   ├── local/
│   │   ├── entity/          # Room entities
│   │   ├── dao/             # Data Access Objects
│   │   └── ShareBoosterDatabase.kt
│   ├── model/               # Data models
│   └── repository/          # Repository implementations
├── network/
│   ├── ApiService.kt        # Retrofit API interface
│   └── AuthInterceptor.kt   # Authentication interceptor
├── ui/
│   ├── navigation/          # Navigation setup
│   ├── screen/              # Compose screens
│   ├── theme/               # Material Design theme
│   └── viewmodel/           # ViewModels
├── service/                 # Background services
├── utils/                   # Utility classes
└── MainActivity.kt
```

## Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd android
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the android folder and open it

3. **Configure Google Sign-In**
   - Replace the `google-services.json` with your own Firebase project configuration
   - Update the Google Client ID in `Constants.kt` if needed

4. **Update API Configuration**
   - Update the `BASE_URL` in `Constants.kt` to point to your backend API
   - Ensure your backend API is running and accessible

5. **Build and Run**
   - Sync the project with Gradle files
   - Build the project
   - Run on an emulator or physical device

## API Integration

The app integrates with the ShareBooster backend API hosted at `https://www.sharebooster.sbs/`. 

### Key API Endpoints:
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `POST /api/auth/google-login` - Google Sign-In
- `POST /api/submit` - Start share boost
- `POST /api/stop-share` - Stop share boost
- `GET /api/share-status` - Get boost status
- `GET /api/profile` - Get user profile
- `PUT /api/profile/update` - Update profile

## Features Overview

### Authentication
- Email/Password login and registration
- Google Sign-In integration
- Secure token-based authentication
- Auto-login with stored credentials

### Share Boosting
- Facebook post URL input validation
- Customizable share amount and intervals
- Real-time progress tracking
- Background service for continuous boosting
- Share limit enforcement (500 for free, unlimited for premium)

### Premium Features
- Premium account status display
- Premium request functionality
- Different share limits based on plan
- Premium UI indicators and badges

### Profile Management
- Profile picture upload and management
- Edit personal information
- Change password functionality
- Account status and premium information

### Settings
- App preferences and configuration
- Notification settings
- Theme preferences
- Account management options

## Customization

### Theme
The app uses a custom Material Design 3 theme with:
- Dark background with gradient effects
- Primary color: #6366F1 (Indigo)
- Secondary color: #06B6D4 (Cyan)
- Accent color: #F59E0B (Amber)
- Premium gold: #FFD700

### UI Components
- Custom gradient buttons
- Animated cards with elevation
- Status badges for different states
- Progress indicators for share boosting
- Material Design 3 components throughout

## Dependencies

- **Compose BOM**: 2023.10.01
- **Compose Navigation**: 2.7.6
- **Retrofit**: 2.9.0
- **Room**: 2.6.1
- **Coil**: 2.5.0
- **Google Play Services Auth**: 20.7.0
- **Firebase Auth**: 22.3.1
- **Work Manager**: 2.9.0
- **DataStore**: 1.0.0

## Building for Production

1. **Generate signed APK**:
   ```bash
   ./gradlew assembleRelease
   ```

2. **Generate AAB for Play Store**:
   ```bash
   ./gradlew bundleRelease
   ```

3. **Configure ProGuard**:
   - The project includes ProGuard rules in `proguard-rules.pro`
   - Enable R8 for release builds

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support and questions:
- Create an issue in the repository
- Contact the development team
- Check the documentation

## Screenshots

The app features a modern dark theme with:
- Beautiful gradient backgrounds
- Material Design 3 components
- Smooth animations and transitions
- Intuitive user interface
- Responsive design for different screen sizes

## Future Enhancements

- Push notifications for boost completion
- Advanced analytics and reporting
- Social sharing features
- Multiple platform support (Instagram, Twitter)
- Advanced scheduling options
- Team collaboration features