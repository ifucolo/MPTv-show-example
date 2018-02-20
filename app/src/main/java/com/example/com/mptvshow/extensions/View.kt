package com.example.com.mptvshow.extensions

import android.view.View
import android.view.ViewGroup

inline fun View.visible(visible: Boolean = false) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

inline fun View.hide() {
    visibility = View.GONE
}

inline fun View.show() {
    visibility = View.VISIBLE
}

inline fun View.invisible() {
    visibility = View.INVISIBLE
}

inline fun View.isVisible() = visibility == View.VISIBLE

inline fun View.setMarginTop(margin: Int) {
    val marginLayout = layoutParams as? ViewGroup.MarginLayoutParams ?: return

    marginLayout.topMargin = margin
}

inline fun View.setMarginBottom(margin: Int) {
    val marginLayout = layoutParams as? ViewGroup.MarginLayoutParams ?: return

    marginLayout.bottomMargin = margin
}

inline fun View.setMarginLeft(margin: Int) {
    val marginLayout = layoutParams as? ViewGroup.MarginLayoutParams ?: return

    marginLayout.leftMargin = margin
}

inline fun View.setMarginRight(margin: Int) {
    val marginLayout = layoutParams as? ViewGroup.MarginLayoutParams ?: return

    marginLayout.rightMargin = margin
}

inline fun View.setHeight(height: Int) {
    layoutParams.height = height
}