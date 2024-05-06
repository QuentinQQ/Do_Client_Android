import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.doapp.db.UserInfo
import com.example.doapp.db.UserRepository
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val cRepository: UserRepository
    init{
        cRepository = UserRepository(application) }
    val allUsers: LiveData<List<UserInfo>> = cRepository.allUsers.asLiveData()
    fun insertUser(userInfo: UserInfo) = viewModelScope.launch(Dispatchers.IO) { cRepository.insertUser(userInfo)
    }
    fun updateUser(userInfo: UserInfo) = viewModelScope.launch(Dispatchers.IO) { cRepository.updateUser(userInfo)
    }
    fun deleteUser(userInfo: UserInfo) = viewModelScope.launch(Dispatchers.IO) { cRepository.deleteUser(userInfo)
    }
}