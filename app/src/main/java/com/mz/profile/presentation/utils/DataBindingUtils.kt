package com.mz.profile.presentation.utils

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import coil.load
import com.mz.profile.R

const val DURATION_START = 2_000L

@BindingAdapter("isAvailable")
fun setTextWithNetworkAvailable(textView: TextView, isAvailable: Boolean)
{
   textView.setBackgroundColor(
      ContextCompat.getColor(
         textView.context,
         if (isAvailable) R.color.md_theme_light_primaryContainer else R.color.md_theme_light_onSurface
                            )
                              )
   textView.setTextColor(
      ContextCompat.getColor(
         textView.context,
         if (isAvailable) R.color.md_theme_light_onPrimaryContainer else R.color.md_theme_light_surface
                            )
                        )
   
   val rootView = textView.rootView as? ViewGroup
   val viewGroup = rootView?.findViewById<ConstraintLayout>(R.id.csl_main)
   
   if (viewGroup != null)
   {
      textView.slideAnimation(viewGroup)
   }
   
   textView.visibility = if (! isAvailable) View.VISIBLE else View.GONE
   
}

private fun TextView.slideAnimation(root: ViewGroup)
{
   val transition: Transition = Slide(Gravity.BOTTOM)
   transition.setDuration(DURATION_START)
   transition.addTarget(this)
   TransitionManager.beginDelayedTransition(root, transition)
}


/**
 * Uses the Picasso library to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?)
{
   
   imgUrl?.let {
      val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
      imgView.load(imgUri) {
         placeholder(R.drawable.ic_download)
      }
      
   }
}

