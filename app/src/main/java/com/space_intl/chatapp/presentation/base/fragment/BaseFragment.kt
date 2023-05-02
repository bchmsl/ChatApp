package com.space_intl.chatapp.presentation.base.fragment

import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.space_intl.chatapp.presentation.base.adapter.AdapterListener
import com.space_intl.chatapp.service.Receiver
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass

typealias Inflater<VB> = (inflater: LayoutInflater, container: ViewGroup, attachToRoot: Boolean) -> VB

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    protected val userId = tag.toString()

    protected val listener = object: AdapterListener {
        override fun getUserId(): String = userId
    }

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    abstract val viewModelClass: KClass<VM>
    private val vm: VM by viewModelForClass(clazz = viewModelClass)

    protected abstract val filter: IntentFilter
    protected var receiver: Receiver? = null

    abstract fun inflate(): Inflater<VB>
    abstract fun setReceiver(): Receiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiver = setReceiver()
        requireActivity().registerReceiver(receiver, filter)
    }

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

    abstract fun onBindViewModel(vm: VM)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        requireActivity().unregisterReceiver(receiver)
    }

    protected fun sendBroadcast(action: String) {
        receiver?.sendBroadcast(action)
    }
}