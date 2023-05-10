# ChatApp

## Table of Contents

- [About](#about)
- [Description](#description)
- [Architecture](#architecture)
    - [Presentation Layer](#presentation-layer)
    - [Business Logic Layer](#business-logic-layer)
    - [Data Layer](#data-layer)
- [Libraries](#libraries)

## About

This is a split screen chat application, that allows users to simulate a chat between two people.
The application is built using Kotlin and Android SDK. It uses the MVVM architecture and the
repository pattern. It uses the Android Architecture Components, Koin, Coroutines, Flow and Room.

## Description

The application has one screen, which is split into two halves.
Each of them is the chat screen, where the user can see the messages of the chat and send a new
message.
The user can send a message by typing it in the text field and pressing the send button and the
message is sent only if the text field is not empty.
The user can see the messages of the chat, which are displayed in the chat screen in the order they
were sent.
The messages sent by the user are displayed on the right side of the screen and the messages sent by
the other person are displayed on the left side of the screen.
The user can see the messages of the chat, even if the application is closed and reopened and the
screen is rotated.

## Architecture

The application uses the MVVM architecture and the repository pattern. The MVVM architecture is used
to separate the UI from the business logic and the repository pattern is used to separate the
business logic from the data layer.
The application is divided into three layers: the presentation layer, the business logic layer and
the data layer.

### Presentation Layer

The presentation layer is the UI layer of the application. It is responsible for displaying the data
to the user and handling the user input. It contains the activities, fragments and the viewmodels.

### Business Logic Layer

The business logic layer is responsible for the business logic of the application. It contains the
repositories and the models.

### Data Layer

The data layer is responsible for the data of the application. It contains the data sources, the
entities and the mappers.

## Libraries

The application uses the following libraries:

- [AndroidX](https://developer.android.com/jetpack/androidx) - AndroidX is a major improvement to
  the original Android Support Library. AndroidX packages fully replace the Support Library by
  providing feature parity and new libraries.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) -
  Android architecture components are a collection of libraries that help you design robust,
  testable, and maintainable apps. Start with classes for managing your UI component lifecycle and
  handling data persistence.

- [Koin](https://insert-koin.io/) - A pragmatic lightweight dependency injection framework for
  Kotlin developers.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - A coroutine is a
  concurrency design pattern that you can use on Android to simplify code that executes
  asynchronously.
- [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - A flow is an asynchronous
  version of a Sequence, a type of collection whose values are lazily produced.
- [Room](https://developer.android.com/topic/libraries/architecture/room) - The Room persistence
  library provides an abstraction layer over SQLite to allow for more robust database access while
  harnessing the full power of SQLite.
