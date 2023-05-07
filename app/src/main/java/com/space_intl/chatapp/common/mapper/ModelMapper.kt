package com.space_intl.chatapp.common.mapper

interface ModelMapper<in MODEL_A, out MODEL_B> {
    operator fun invoke(model: MODEL_A): MODEL_B
}