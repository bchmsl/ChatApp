package com.bchmsl.chatapp.presentation.base

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.bchmsl.chatapp.common.extensions.log

typealias Inflater<VB> = (inflater: LayoutInflater, container: ViewGroup, attachToRoot: Boolean) -> VB

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel, BR : BroadcastReceiver>() :
    Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    private lateinit var vm: VM

    protected abstract val filter: IntentFilter
    protected lateinit var receiver: BR
    private val intent by lazy { Intent() }

    abstract fun inflate(): Inflater<VB>
    abstract fun provideViewModel(): Class<VM>
    abstract fun setReceiver(): BR

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = this.inflate().invoke(inflater, container!!, false)
        vm = ViewModelProvider(requireActivity())[provideViewModel()]
        receiver = setReceiver()
        requireActivity().registerReceiver(receiver, filter)
        log("onCreateView")
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBind(vm)
    }

    open fun onBind(vm: VM) {
        loadContent(vm)
        listeners(vm)
    }

    abstract fun listeners(vm: VM)
    abstract fun loadContent(vm: VM)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        log("onDestroyView")
        requireActivity().unregisterReceiver(receiver)
    }

    protected fun sendBroadcast(action: String) {
        intent.action = action
        requireActivity().sendBroadcast(intent)
    }
}