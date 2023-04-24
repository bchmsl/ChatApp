package com.bchmsl.chatapp.presentation.model

abstract class ModelWithId<T: ModelWithId<T>>{
    abstract val id: Int
}

