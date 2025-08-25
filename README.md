# Github Users App

Github Users App is an Android application 📱 built using **Modern Android development** practices.
The app consumes the [GitHub REST API](https://docs.github.com/en/rest?apiVersion=2022-11-28) to list users, search users, display detailed profiles, and show repositories.

---

## 📸 Screenshots
| User List | Search | User Details |
|-----------|--------|--------------|
| <img width="250" alt="image_1" src="https://github.com/user-attachments/assets/28dc05cb-e6b8-4ecf-8e3e-9789683d8304" /> | <img width="250" alt="image_2" src="https://github.com/user-attachments/assets/1e126b18-3b43-45f0-897f-5f03f883288f" /> | <img width="250" alt="image_3" src="https://github.com/user-attachments/assets/2478180e-4940-4997-9e21-c562c4f74f2b" /> |

---
## 📖 About

- Loads **GitHub Users** from the GitHub REST API.  
- You can view user details by simply clicking on a user.  
- User details include profile info (avatar, name, bio, followers, following, etc.).  
- You can also see the list of **repositories** for each user.  
- Supports **searching users** by username.  
- Offline capable (using Room DB cache).  

---

## 📱 Tech Stack

- **Language:** [Kotlin](https://kotlinlang.org/)  
- **Architecture:** MVVM + Clean Architecture principles  
- **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose)  
- **Async:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) + [Flow](https://kotlinlang.org/docs/flow.html)  
- **Networking:** [Retrofit](https://square.github.io/retrofit/) + [OkHttp](https://square.github.io/okhttp/)  
- **Persistence:** [Room](https://developer.android.com/training/data-storage/room) (Single Source of Truth, offline support)  
- **Dependency Injection:** [Hilt](https://dagger.dev/hilt/)  
- **Image Loading:** [Coil](https://coil-kt.github.io/coil/) – Lightweight, modern image loading library for Android, built in Kotlin and optimized for Compose.  
- **Testing:**  
  - [JUnit5](https://junit.org/junit5/)  
  - [Mockito](https://site.mockito.org/)  
  - [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)  
  - [Espresso](https://developer.android.com/training/testing/espresso) / [Compose UI Test](https://developer.android.com/jetpack/compose/testing)  
- **Build:** [Gradle (KTS)](https://docs.gradle.org/current/userguide/kotlin_dsl.html)


---

## 📦 Package Structure
```
 githubusers
            ├── GithubUsersApp.kt
            ├── UserDetailsActivity.kt
            ├── UserListActivity.kt
            ├── data
            │   ├── db
            │   │   ├── AppDatabase.kt
            │   │   ├── UserDao.kt
            │   │   ├── UserDetailsEntity.kt
            │   │   ├── UserDetailsWithRepos.kt
            │   │   ├── UserEntity.kt
            │   │   └── UserRepositoryEntity.kt
            │   ├── mapper
            │   │   ├── UserDataMapperImpl.kt
            │   │   ├── UserDetailsMapperImpl.kt
            │   │   └── UserRepositoryMapperImpl.kt
            │   ├── model
            │   │   ├── UserDetailsDto.kt
            │   │   ├── UserRepositoryDto.kt
            │   │   └── UsersItemDto.kt
            │   ├── network
            │   │   └── ApiService.kt
            │   └── repository
            │       └── UsersRepository.kt
            ├── di
            │   ├── DatabaseModule.kt
            │   ├── MappersModule.kt
            │   ├── NetworkModule.kt
            │   └── RepositoryModule.kt
            ├── domain
            │   └── mapper
            │       ├── UserDataMapper.kt
            │       ├── UserDetailsMapper.kt
            │       ├── UserDetailsUiMapper.kt
            │       ├── UserRepositoryMapper.kt
            │       ├── UserRepositoryUiMapper.kt
            │       └── UserUiMapper.kt
            ├── ui
            │   ├── components
            │   │   ├── ErrorComposable.kt
            │   │   ├── LoadingComposable.kt
            │   │   ├── NoResultsComposable.kt
            │   │   ├── TopBarComposable.kt
            │   │   ├── UserDetailsContent.kt
            │   │   ├── UserDetailsInfo.kt
            │   │   ├── UserDetailsScreen.kt
            │   │   ├── UserListComposable.kt
            │   │   ├── UserListItemComposable.kt
            │   │   ├── UserListScreenComposable.kt
            │   │   ├── UserRepositoryList.kt
            │   │   └── UserRepositoryListItem.kt
            │   ├── mapper
            │   │   ├── UserDetailsUiMapperImpl.kt
            │   │   ├── UserRepositoryUiMapperImpl.kt
            │   │   └── UserUiMapperImpl.kt
            │   ├── model
            │   │   ├── UserDetailsUi.kt
            │   │   ├── UserRepositoryUi.kt
            │   │   └── UsersItemUi.kt
            │   ├── theme
            │   │   ├── Color.kt
            │   │   ├── Theme.kt
            │   │   └── Typography.kt
            │   ├── utils
            │   │   └── PreviewUtils.kt
            │   └── viewmodel
            │       ├── UserDetailsUiState.kt
            │       ├── UserDetailsViewModel.kt
            │       ├── UsersUiState.kt
            │       └── UsersViewModel.kt
            └── utils
                ├── AppConstants.kt
                ├── NetworkUtils.kt
                └── Result.kt
```

