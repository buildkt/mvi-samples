# MVI Samples for buildkt/mvi
This repository provides a set of practical examples demonstrating how to use the buildkt/mvi library
to build modern Android applications following the Model-View-Intent (MVI) architecture.

The goal of these samples is to showcase real-world use cases, from simple counter apps to more complex 
list/detail screens with navigation and asynchronous data loading.

## About the Library
`buildkt/mvi` is a lightweight, Kotlin-first MVI library for Android designed to simplify state management and promote a 
clear separation of concerns. It helps you build predictable and testable UI by enforcing a unidirectional data 
flow.

## Samples Overview
This repository is structured as a multi-module Android project, where each sample demonstrates different features of the 
`buildkt/mvi` library.
1. Simple Counter (counter module)
A basic example that showcases the core MVI components:
- State: A single data class representing the screen's state.
- Intent: User actions that can modify the state.
- Reducer: A pure function that takes the current state and an intent to produce a new state.
- ViewModel: Wires everything together and exposes a StateFlow for the UI to observe.

2. List-Detail Navigation (list-detail module)
A more advanced sample that demonstrates:
- Navigation: How to handle navigation events as side effects.
- Side Effects: Managing asynchronous operations like fetching data from a repository (ProductRepository).
- Dependency Injection: Using Hilt to provide dependencies like ProductApiService.
- KSP Code Generation: Leveraging the library's KSP processor (@MviScaffolding) to reduce boilerplate for navigation and ViewModel setup.
- Pagination: The FakeProductApiService simulates a paginated API, showing how side effects can handle complex data loading logic.

3. Design System (design-system module)
A shared module containing reusable Jetpack Compose components (NotificationBadge, Chip, etc.) used across the different samples.
This demonstrates how to build a scalable UI layer that is independent of the application's business logic.

## Key Concepts Illustrated
- Unidirectional Data Flow (UDF): State flows down from the ViewModel to the UI, and events (Intents) flow up from the UI to the ViewModel.
- State Immutability: The state is immutable. The reducer creates a new state object for every change, ensuring predictability.
- Separation of Concerns:
  - UI (Compose): Responsible only for displaying state and sending intents.
  - ViewModel: Orchestrates the flow, delegating logic to the reducer and side effects.
  - Reducer: Contains synchronous business logic.
  - Side Effects: Handle asynchronous tasks like API calls, database access, or navigation.
- Testability: With logic separated into pure functions (reducers) and isolated side effects, testing becomes straightforward.

##  How to Run the Samples
1. Clone the repository: `git clone <repository-url>`
2. Open in Android Studio: Open the cloned directory in the latest version of Android Studio.
3. Build and Run: Let Gradle sync the dependencies. Select one of the app configurations (e.g., counter.app or list-detail.app) 
   from the run configuration dropdown and press the "Run" button.

## Exploring the Code
A good starting point is the list-detail sample:
1. ProductListPane.kt: The main UI screen, which observes state and sends intents like OnProductClick.
2. ProductListViewModel.kt: Shows how the ViewModel is configured with a reducer and side effects.
3. ProductNavigation.kt: Demonstrates how the library's KSP processor generates NavGraphBuilder extensions to simplify navigation setup.
4. ProductApiService.kt: The FakeProductApiService simulates network latency (delay(800)) and data pagination, providing a realistic source for the ProductRepository.

By exploring these files, you can trace the flow of data and events and understand how each part of the buildkt/mvi library works together.