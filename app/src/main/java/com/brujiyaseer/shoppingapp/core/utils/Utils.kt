package com.brujiyaseer.shoppingapp.core.utils

import android.app.Activity
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import com.brujiyaseer.shoppingapp.MainActivity
import com.brujiyaseer.shoppingapp.R
import com.google.android.material.snackbar.Snackbar


fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

//fun replaceFragment(fragment: Fragment, addStack: Boolean = true) {
//    /* Функция расширения для AppCompatActivity, позволяет устанавливать фрагменты */
//    if (addStack) {
//        APP_ACTIVITY.supportFragmentManager.beginTransaction()
//            .addToBackStack(null)
//            .replace(
//                R.id.containerFragment,
//                fragment
//            ).commit()
//    } else {
//        APP_ACTIVITY.supportFragmentManager.beginTransaction()
//            .replace(
//                R.id.containerFragment,
//                fragment
//            ).commit()
//    }

//}
fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun Activity.snackBar(msg: String, action: (() -> Unit)? = null) {
    Snackbar.make(
        findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG
    ).also {
        it.setAction(
            getString(R.string.ok)
        ) { action?.invoke() }
    }.show()
}