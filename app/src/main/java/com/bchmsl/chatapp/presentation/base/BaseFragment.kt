package com.bchmsl.chatapp.presentation.base

import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.bchmsl.chatapp.service.Receiver

typealias Inflater<VB> = (inflater: LayoutInflater, container: ViewGroup, attachToRoot: Boolean) -> VB

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    private lateinit var vm: VM

    protected abstract val filter: IntentFilter
    protected var receiver: Receiver? = null

    abstract fun inflate(): Inflater<VB>
    abstract fun provideViewModel(): Class<VM>
    abstract fun setReceiver(): Receiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(requireActivity())[provideViewModel()]
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
        onBind()

    }

    abstract fun onBind()

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