package com.bchmsl.chatapp.presentation.model

abstract class ModelWithId<T: ModelWithId<T>>{
    abstract val id: Int

    override fun equals(other: Any?): Boolean {
        return true
    }

    override fun hashCode(): Int {
        return id
    }
}

