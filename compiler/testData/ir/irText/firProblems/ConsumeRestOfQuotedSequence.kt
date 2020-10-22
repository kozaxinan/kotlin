// WITH_RUNTIME
// FULL_JDK
import java.io.Reader

fun Reader.consumeRestOfQuotedSequence(sb: StringBuilder, quote: Char) {
    var ch = nextChar()
    while (ch != null && ch != quote) {
        if (ch == BACKSLASH) nextChar()?.let { sb.append(it) } else sb.append(ch)
        ch = nextChar()
    }
}

private fun Reader.nextChar(): Char? =
    read().takeUnless { it == -1 }?.toChar()

private const val BACKSLASH = '\\'

