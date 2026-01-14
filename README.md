# 📚 BookStore App

## 📌 Overview
BookStore is an Android application built using **Jetpack Compose** and **MVVM architecture**.  
The app fetches book data from a public API and displays **Popular** and **New** books with a clean, stable UI.

---

## 👩‍💻 My Experience
This project helped me understand:
- State management in Jetpack Compose
- MVVM architecture in a real app
- Handling UI recomposition and scroll issues
- Using RxJava `Single.zip` for parallel API calls

I focused on keeping the UI minimal and production-ready while ensuring stability and clean architecture.

---

## ✨ Features Implemented
- Popular & New books tabs
- Parallel API calls using RxJava `Single.zip`
- Book cover, title, and author display
- Proper loading state handling
- Error handling using Snackbar
- Smooth scrolling with preserved scroll state
- Clean and minimal UI (image on left, text on right)

---

## ⚠️ Challenges Faced
- Scroll position resetting due to recomposition
- Repeated API calls causing UI flickering
- Managing loading and error states correctly in Compose

These were resolved by:
- Using `rememberLazyListState`
- Preventing unnecessary API reloads in ViewModel
- Managing UI state using `StateFlow`

---

## 🧠 Assumptions Made
- Internet connection is required to fetch data
- Public Open Library API is available
- Basic error message is sufficient for failure cases

---

## 🛠 Tech Stack
- Kotlin
- Jetpack Compose
- MVVM Architecture
- Retrofit
- RxJava (Single.zip)
- Koin
- Coil
- StateFlow

---

## 🔗 API Used
Open Library API  
https://openlibrary.org/developers/api
