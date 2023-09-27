package net.guardianconnect

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import net.guardianconnect.navigation.AppNavHost
import net.guardianconnect.ui.auth.AuthViewModel
import net.guardianconnect.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                AppNavHost(viewModel)
            }
        }
    }
}