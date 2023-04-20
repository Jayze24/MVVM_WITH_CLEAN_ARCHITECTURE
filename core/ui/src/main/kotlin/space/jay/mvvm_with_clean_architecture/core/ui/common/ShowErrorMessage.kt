package space.jay.mvvm_with_clean_architecture.core.ui.common

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import space.jay.mvvm_with_clean_architecture.core.common.log.Log
import space.jay.mvvm_with_clean_architecture.core.common.wrapper.ErrorMessage

@Composable
fun ShowErrorMessage(
    snackBar : SnackbarHostState,
    listErrorMessage : List<ErrorMessage>,
    onClickRetry : () -> Unit,
    onDismissErrorMessage : (id : Long) -> Unit
) {
    if (listErrorMessage.isNotEmpty()) {
        Log.send(listErrorMessage)
        val errorMessage = remember(listErrorMessage.first().id) { listErrorMessage.first() }

        LaunchedEffect(errorMessage.id, snackBar) {
            val result = snackBar.showSnackbar(
                errorMessage.message,
                actionLabel = "retry"
            )

            if (result == SnackbarResult.ActionPerformed) {
                onClickRetry()
            }
            onDismissErrorMessage(errorMessage.id)
        }
    }
}