import android.app.Application
import androidx.lifecycle.AndroidViewModel import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData import androidx.lifecycle.viewModelScope
import com.example.doapp.db.UserRepository
import kotlinx.coroutines.Dispatchers import kotlinx.coroutines.launch
import javax.security.auth.Subject

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val cRepository: UserRepository
    init{
        cRepository = UserRepository(application) }
    val allSubjects: LiveData<List<Subject>> = cRepository.allSubjects.asLiveData()
    fun insertSubject(subject: Subject) = viewModelScope.launch(Dispatchers.IO) { cRepository.insert(subject)
    }
    fun updateSubject(subject: Subject) = viewModelScope.launch(Dispatchers.IO) { cRepository.update(subject)
    }
    fun deleteSubject(subject: Subject) = viewModelScope.launch(Dispatchers.IO) { cRepository.delete(subject)
    }
}