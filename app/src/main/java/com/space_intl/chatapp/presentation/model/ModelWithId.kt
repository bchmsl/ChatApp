package com.space_intl.chatapp.presentation.model

abstract class ModelWithId<T: ModelWithId<T>>{
    abstract val id: Int
}

