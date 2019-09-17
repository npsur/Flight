package com.ns.flight.ui.flightlist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ns.flight.R
import net.glxn.qrgen.android.QRCode
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

val DURATION_REGEX = "(?=hr|min)|(?<=hr)".toRegex()

@BindingAdapter("flightTime")
fun setFlightTime(textView: TextView, flightTime: ZonedDateTime) {
    textView.text = DateTimeFormatter.ofPattern("HH:mm").format(flightTime)
}

@BindingAdapter("destinationCity")
fun setDestinationCity(textView: TextView, destinationCity: String) {
    val format = textView.resources.getString(R.string.flight_card_title, destinationCity)
    textView.text = format
}

@BindingAdapter("qrCode")
fun setQrCode(imageView: ImageView, from: String) {
    imageView.setImageBitmap(QRCode.from(from).bitmap())
}

@BindingAdapter("flightDetailTime")
fun setFlightDetailTime(textView: TextView, flightTime: ZonedDateTime) {
    textView.text = DateTimeFormatter.ofPattern("EEE',' d MMM hh:mm a").format(flightTime)
}

@BindingAdapter("flightDuration")
fun setFlightDuration(textView: TextView, duration: String) {
    textView.text = duration.replace(DURATION_REGEX, " ")
}

@BindingAdapter("gone")
fun setGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}