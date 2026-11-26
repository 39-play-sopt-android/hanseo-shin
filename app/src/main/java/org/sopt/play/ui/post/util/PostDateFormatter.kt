package org.sopt.play.ui.post.util

private val postDatePattern = Regex("""\d{4}-\d{2}-\d{2}""")

fun String.toPostDisplayDate(): String {
    val date = substringBefore('T')

    return if (postDatePattern.matches(date)) {
        date.replace('-', '.')
    } else {
        "-"
    }
}