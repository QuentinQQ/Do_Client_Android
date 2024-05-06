import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.doapp.db.Repository
import com.example.doapp.db.userinfo.UserInfo
import com.example.doapp.db.users.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.security.auth.Subject


class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val cRepository: Repository
    init{
        cRepository = Repository(application) }

    /*-----Users-----*/
    val allUsers: LiveData<List<Users>> = cRepository.allUsers.asLiveData()
    fun insertUsers(users: Users) = viewModelScope.launch(Dispatchers.IO) { cRepository.insertUsers(users)
    }
    fun updateSubject(users: Users) = viewModelScope.launch(Dispatchers.IO) { cRepository.updateUsers(users)
    }
    fun deleteSubject(users: Users) = viewModelScope.launch(Dispatchers.IO) { cRepository.deleteUsers(users)
    }
}