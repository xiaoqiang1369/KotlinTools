import java.util.concurrent.Executors

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread.
 */
fun runOnNewThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}