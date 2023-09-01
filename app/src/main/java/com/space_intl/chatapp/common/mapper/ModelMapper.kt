package com.space_intl.chatapp.common.mapper

/**
 * Interface for model mapper. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <MODEL_A> the model input type
 * @param <MODEL_B> the model return type
 */
interface ModelMapper<in MODEL_A, out MODEL_B> {
    operator fun invoke(model: MODEL_A): MODEL_B
}
