package com.umc.one_person_households_platform.view.groupbuyingdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.umc.one_person_households_platform.databinding.FragmentGroupBuyingDetailMoreWriterBinding

class GroupBuyingDetailMoreWriterFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentGroupBuyingDetailMoreWriterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGroupBuyingDetailMoreWriterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}