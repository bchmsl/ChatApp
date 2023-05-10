package com.space_intl.chatapp.presentation.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass

typealias Inflater<VB> = (inflater: LayoutInflater, container: ViewGroup, attachToRoot: Boolean) -> VB

/**
 * Base fragment for all fragments.
 * @param VB The ViewBinding class for the fragment.
 * @param VM The ViewModel class for the fragment.
 */
abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    /**
     * The view model class for the fragment.
     * @see ViewModel
     */
    protected abstract val viewModelClass: KClass<VM>

    /**
     * The view model for the fragment.
     * @see viewModelForClass
     */
    private val vm: VM by viewModelForClass(clazz = viewModelClass)

    abstract fun inflate(): Inflater<VB>
    abstract fun onBindViewModel(vm: VM)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = this.inflate().invoke(inflater, container!!, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBindViewModel(vm)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
