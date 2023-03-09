package com.brujiyaseer.shoppingapp.core.utils

import android.app.Activity
import android.util.Patterns
import android.view.View
import com.brujiyaseer.shoppingapp.R
import com.google.android.material.snackbar.Snackbar

const val DEFAULT_THROTTLE_DURATION_IN_MILLIS = 300L

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun Activity.snackBar(msg: String, action: (() -> Unit)? = null) {
    Snackbar.make(
        findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG
    ).also {
        it.setAction(
            getString(R.string.ok)
        ) { action?.invoke() }
    }.show()
}

inline fun <V : View> V.onClick(
    throttleDuration: Long = DEFAULT_THROTTLE_DURATION_IN_MILLIS, crossinline listener: () -> Unit
): V {
    setOnClickListener(SafeClickListener(throttleDuration) { listener.invoke() })
    return this
}

class SafeClickListener(
    throttleDuration: Long,
    private val listener: View.OnClickListener,
) : View.OnClickListener {
    private val doubleClickPreventer = DoubleClickPreventer(throttleDuration)

    override fun onClick(v: View) {
        if (doubleClickPreventer.prevent()) {
            return
        }
        listener.onClick(v)
    }
}

private class DoubleClickPreventer(
    private val throttleDuration: Long
) {
    private var lastClickTime = 0L

    fun prevent(): Boolean {
        val currentTime = System.currentTimeMillis()
        val spentTime = currentTime - lastClickTime
        if (spentTime in 1 until throttleDuration) {
            return true
        }
        lastClickTime = currentTime
        return false
    }
}
