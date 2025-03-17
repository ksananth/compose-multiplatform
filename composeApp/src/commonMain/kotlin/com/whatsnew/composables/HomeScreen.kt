import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.whatsnew.Step
import com.whatsnew.composables.DoneScreen
import com.whatsnew.composables.DownloadScreen
import com.whatsnew.composables.PreviewScreen
import com.whatsnew.composables.ReleaseScreen
import com.whatsnew.form.WhatsNewFormScreen

@Composable
fun HomeScreen(
    currentRightContent: Step,
    onNavigate: (Step) -> Unit,
) {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(lightBlack),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    "Step 1",
                    color = white,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    "Enter Release Name",
                    color = white,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
                .background(primary),
            contentAlignment = Alignment.Center
        ) {
            Crossfade(
                targetState = currentRightContent,
                animationSpec = tween(durationMillis = 500) // Animation duration (500ms)
            ) { screen ->
                when (screen) {
                    Step.HOME -> ReleaseScreen(
                        onNavigate = { onNavigate(Step.FORM) },
                    )

                    Step.FORM -> WhatsNewFormScreen(
                        onNavigate = { onNavigate(Step.PREVIEW) }
                    )

                    Step.PREVIEW -> PreviewScreen(
                        onNavigate = { onNavigate(Step.DOWNLOAD) }
                    )

                    Step.DOWNLOAD -> DownloadScreen(
                        onNavigate = { onNavigate(Step.DONE) }
                    )

                    Step.DONE -> DoneScreen()
                }
            }
        }
    }
}




