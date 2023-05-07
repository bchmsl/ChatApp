package com.space_intl.chatapp.common.mapper

interface ModelMapper<in MODEL_A, out MODEL_B> {
    fun mapModel(model: MODEL_A): MODEL_B
}