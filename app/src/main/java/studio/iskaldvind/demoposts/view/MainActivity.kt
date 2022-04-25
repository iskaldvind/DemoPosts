package studio.iskaldvind.demoposts.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.coroutines.launch
import studio.iskaldvind.demoposts.R.layout.main_activity
import studio.iskaldvind.demoposts.databinding.MainActivityBinding
import studio.iskaldvind.demoposts.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(main_activity) {

    private val viewModel: MainViewModel by viewModel()
    private val binding: MainActivityBinding by viewBinding()
    private val adapter by lazy { PostsAdapter(context = this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recycler.adapter = adapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.posts.collectLatest(adapter::submitData)
            }
        }
    }
}