package com.example.jetpackcompose.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcompose.app.screens.login_signup.SignUpScreen
import com.example.jetpackcompose.app.screens.login_signup.SignInScreen
import com.example.jetpackcompose.app.screens.MainScreen
import com.example.jetpackcompose.app.features.apiService.LogAPI.SignInViewModel
import com.example.jetpackcompose.app.features.apiService.LogAPI.SignInViewModelFactory
import com.example.jetpackcompose.app.features.apiService.ReadNotificationTransaction.PostExpenseNotiTransaction
import com.example.jetpackcompose.app.features.apiService.ReadNotificationTransaction.PostIncomeNotiTransaction
import com.example.jetpackcompose.app.features.editFeatures.EditExpenseTransaction
import com.example.jetpackcompose.app.features.editFeatures.EditFixedExpenseTransaction
import com.example.jetpackcompose.app.features.editFeatures.EditIncomeExpenseTransaction
import com.example.jetpackcompose.app.features.editFeatures.EditIncomeTransaction
import com.example.jetpackcompose.app.screens.CalendarScreen
import com.example.jetpackcompose.app.screens.anual_sceens.InputFixedTab
import com.example.jetpackcompose.app.screens.OtherScreen
import com.example.jetpackcompose.app.screens.anual_sceens.AnualScreen
import com.example.jetpackcompose.app.screens.find_calendar.FindCalendarScreen
import com.example.jetpackcompose.app.features.apiService.ReadNotificationTransaction.TransactionNotificationScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppQuanLyChiTieu() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val signInViewModel: SignInViewModel = viewModel(
        factory = SignInViewModelFactory(context)
    )

    // Kiểm tra nếu Token đã được xác nhận hay không
    if (signInViewModel.isTokenCleared()) {
        NavHost(navController = navController, startDestination = "signup") {
            composable("signup") { SignUpScreen(navController) }
            composable("signin") { SignInScreen(navController) }
            composable("mainscreen") { MainScreen(navController) }
            composable("anual") { AnualScreen(navController) }
            composable("other") { OtherScreen(navController) }
            composable("inputfixedtab") { InputFixedTab(navController) }
            composable("calendar") { CalendarScreen(navController) }
            composable("transactionNotification") { TransactionNotificationScreen(navController) }

            // Chỉnh sửa giao dịch (truyền transactionId)
            composable(
                "editExpense/{transactionId}",
                arguments = listOf(navArgument("transactionId") { type = NavType.IntType })
            ) { backStackEntry ->
                // Lấy transactionId từ NavArgument
                val transactionId = backStackEntry.arguments?.getInt("transactionId") ?: 0
                EditExpenseTransaction(navController = navController, fixedTransactionId = transactionId)
            }

            composable(
                "editIncome/{transactionId}",
                arguments = listOf(navArgument("transactionId") { type = NavType.IntType })
            ) { backStackEntry ->
                // Lấy transactionId từ NavArgument
                val transactionId = backStackEntry.arguments?.getInt("transactionId") ?: 0
                EditIncomeTransaction(navController = navController, transactionId = transactionId)
            }

            composable(
                "editFixedExpense/{fixedTransactionId}",
                arguments = listOf(navArgument("fixedTransactionId") { type = NavType.IntType })
            ) { backStackEntry ->
                // Lấy transactionId từ NavArgument
                val fixedTransactionId = backStackEntry.arguments?.getInt("fixedTransactionId") ?: 0
                EditFixedExpenseTransaction(navController = navController, fixedTransactionId = fixedTransactionId)
            }

            composable(
                "editFixedIncome/{fixedTransactionId}",
                arguments = listOf(navArgument("fixedTransactionId") { type = NavType.IntType })
            ) { backStackEntry ->
                // Lấy transactionId từ NavArgument
                val fixedTransactionId = backStackEntry.arguments?.getInt("fixedTransactionId") ?: 0
                EditIncomeExpenseTransaction(navController = navController, fixedTransactionId = fixedTransactionId)
            }

            composable("postExpenseNotiTransaction/{amount}/{selectedDate}/{index}") { backStackEntry ->
                val amount = backStackEntry.arguments?.getString("amount")?.toLongOrNull() ?: 0L
                val selectedDate = backStackEntry.arguments?.getString("selectedDate") ?: ""
                val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0
                PostExpenseNotiTransaction(navController, amount, selectedDate, index)
            }
            composable("postIncomeNotiTransaction/{amount}/{selectedDate}/{index}") { backStackEntry ->
                val amount = backStackEntry.arguments?.getString("amount")?.toLongOrNull() ?: 0L
                val selectedDate = backStackEntry.arguments?.getString("selectedDate") ?: ""
                val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0
            }

        }
    } else {
        NavHost(navController = navController, startDestination = "mainscreen") {
            composable("signup") { SignUpScreen(navController) }
            composable("signin") { SignInScreen(navController) }
            composable("mainscreen") { MainScreen(navController) }
            composable("anual") { AnualScreen(navController) }
            composable("other") { OtherScreen(navController) }
            composable("inputfixedtab") { InputFixedTab(navController) }
            composable("calendar") { CalendarScreen(navController) }
            composable("findtransaction") { FindCalendarScreen(navController) }
            composable("transactionNotification") { TransactionNotificationScreen(navController) }


            // Chỉnh sửa giao dịch (truyền transactionId)
            composable(
                "editExpense/{transactionId}",
                arguments = listOf(navArgument("transactionId") { type = NavType.IntType })
            ) { backStackEntry ->
                // Lấy transactionId từ NavArgument
                val transactionId = backStackEntry.arguments?.getInt("transactionId") ?: 0
                EditExpenseTransaction(navController = navController, fixedTransactionId = transactionId)
            }
            composable(
                "editIncome/{transactionId}",
                arguments = listOf(navArgument("transactionId") { type = NavType.IntType })
            ) { backStackEntry ->
                // Lấy transactionId từ NavArgument
                val transactionId = backStackEntry.arguments?.getInt("transactionId") ?: 0
                EditIncomeTransaction(navController = navController, transactionId = transactionId)
            }

            composable(
                "editFixedExpense/{fixedTransactionId}",
                arguments = listOf(navArgument("fixedTransactionId") { type = NavType.IntType })
            ) { backStackEntry ->
                val fixedTransactionId = backStackEntry.arguments?.getInt("fixedTransactionId") ?: 0
                EditFixedExpenseTransaction(navController = navController, fixedTransactionId = fixedTransactionId)
            }

            composable(
                "editFixedIncome/{fixedTransactionId}",
                arguments = listOf(navArgument("fixedTransactionId") { type = NavType.IntType })
            ) { backStackEntry ->
                val fixedTransactionId = backStackEntry.arguments?.getInt("fixedTransactionId") ?: 0
                EditIncomeExpenseTransaction(navController = navController, fixedTransactionId = fixedTransactionId)
            }

            composable("postExpenseNotiTransaction/{amount}/{selectedDate}/{index}") { backStackEntry ->
                val amount = backStackEntry.arguments?.getString("amount")?.toLongOrNull() ?: 0L
                val selectedDate = backStackEntry.arguments?.getString("selectedDate") ?: ""
                val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0
                PostExpenseNotiTransaction(navController, amount, selectedDate, index)
            }
            composable("postIncomeNotiTransaction/{amount}/{selectedDate}/{index}") { backStackEntry ->
                val amount = backStackEntry.arguments?.getString("amount")?.toLongOrNull() ?: 0L
                val selectedDate = backStackEntry.arguments?.getString("selectedDate") ?: ""
                val index = backStackEntry.arguments?.getString("index")?.toIntOrNull() ?: 0
                PostIncomeNotiTransaction(navController, amount, selectedDate, index)
            }
        }
    }
}




