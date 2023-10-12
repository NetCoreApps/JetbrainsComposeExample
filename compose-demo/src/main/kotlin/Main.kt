import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch
import net.servicestack.client.JsonServiceClient
import java.security.cert.X509Certificate
import javax.imageio.ImageIO
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


@Composable
fun App() {
    trustSelfSignedSSL()
    val client = JsonServiceClient("https://localhost:5001")
    var textFieldState by remember { mutableStateOf(TextFieldValue()) }
    var searchResult by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    // Function to call ServiceStack API
    fun callService() {
        coroutineScope.launch {
            val request = SearchFiles()
            request.pattern = textFieldState.text
            val response: SearchFilesResponse = client.get(request)
            searchResult = response.results.joinToString("\n")
        }
    }

    callService()

    val lightBlue = Color(0xFF2196F3)

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.Top  // Aligns children to the top
    ) {
        // Light blue banner with white title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(lightBlue),  // Use custom light blue color
            contentAlignment = Alignment.Center
        ) {
            Text("My Application", color = Color.White, style = TextStyle(fontSize = 24.sp))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),  // Padding on the top
            contentAlignment = Alignment.Center  // Centers the content horizontally
        ) {
            Text("Search Files", color = Color.Black,
                modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                style = TextStyle(fontSize = 16.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),  // Padding on the top, start, and end
            contentAlignment = Alignment.Center  // Centers the content horizontally
        ) {
            // Search Box
            BasicTextField(
                value = textFieldState,
                onValueChange = {
                    textFieldState = it
                    callService()
                },
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.8f)
                    .height(40.dp)
                    .padding(top = 4.dp)
                    .border(2.dp, lightBlue, RoundedCornerShape(4.dp))
                    .clip(RoundedCornerShape(4.dp))
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),  // Padding on the top, start, and end
            contentAlignment = Alignment.Center  // Centers the content horizontally
        ) {

            // Search Result
            Text(
                text = searchResult,
                color = Color.Blue,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

fun trustSelfSignedSSL() {
    val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate>? {
            return null
        }

        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {
        }

        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {
        }
    })

    try {
        val sc = SSLContext.getInstance("SSL")
        sc.init(null, trustAllCerts, java.security.SecureRandom())
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun main() = application {
    val classLoader = Thread.currentThread().contextClassLoader
    val iconStream = classLoader.getResourceAsStream("icon.png")  // Replace 'icon.png' with your actual file name
    val iconImage: ImageBitmap = ImageIO.read(iconStream).toComposeImageBitmap() // Replace with your actual image path

    Window(
        onCloseRequest = ::exitApplication,
        title = "Compose Demo",
        icon = BitmapPainter(iconImage)
    ) {
        App()
    }
}
