package dev.patrick.dncassignment.base

import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun TextInputEditText.queryTextChangeStateFlow(): StateFlow<String> {
    val query = MutableStateFlow("")

    addTextChangedListener { newText ->
        query.value = newText.toString()
    }

    return query
}