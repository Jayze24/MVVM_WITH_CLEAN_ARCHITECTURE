package space.jay.mvvm_with_clean_architecture.core.common

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Test
import space.jay.mvvm_with_clean_architecture.core.common.delay.DelayMessage
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class TestDelayMessage {

    @Test
    fun delayMessage_string() = runTest {
        val message1 = "first message"
        val message2 = "second message"
        val message3 = "third message"

        val delayMessage = DelayMessage<String>(1_000)

        delayMessage.flowResult.test {
            delayMessage.setMessage(message1)
            delay(1.seconds)
            assertThat(expectMostRecentItem()).isEqualTo(message1)

            delayMessage.setMessage(message2)
            delayMessage.setMessage(message3)
            expectNoEvents()
            delay(1.seconds)
            assertThat(expectMostRecentItem()).isEqualTo(message3)

            delayMessage.setMessage(message2, isDirect = true)
            assertThat(expectMostRecentItem()).isEqualTo(message2)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun delayMessage_int() = runTest {
        val message1 = 1
        val message2 = 2
        val message3 = 3

        val delayMessage = DelayMessage<Int>()
        delayMessage.flowResult.test {
            expectNoEvents()
            delayMessage.setMessage(message1)
            delay(300)
            expectNoEvents()
            delay(300)
            assertThat(expectMostRecentItem()).isEqualTo(message1)

            delayMessage.setMessage(message2, true)
            delayMessage.setMessage(message3, true)
            assertThat(expectMostRecentItem()).isEqualTo(message3)

            cancelAndIgnoreRemainingEvents()
        }

        delayMessage.flowResult.test {
            assertThat(expectMostRecentItem()).isEqualTo(message3)
            cancelAndIgnoreRemainingEvents()
        }
    }
}