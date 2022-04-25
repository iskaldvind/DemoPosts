package studio.iskaldvind.demoposts.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import studio.iskaldvind.demoposts.repository.remote.IRepository
import studio.iskaldvind.demoposts.repository.remote.IRepositoryAPI
import studio.iskaldvind.demoposts.repository.PostsPageSource
import studio.iskaldvind.demoposts.repository.Repository
import studio.iskaldvind.demoposts.repository.local.RoomDB
import studio.iskaldvind.demoposts.viewmodel.MainViewModel

val application = module {
    single { IRepositoryAPI.create() }
    single<IRepository> { Repository(api = get()) }
    single { PostsPageSource(repository = get()) }
    single { Room.databaseBuilder(get(), RoomDB::class.java, "RoomDB").build() }
    single { get<RoomDB>().postDao() }
}

val main = module {
    viewModel { MainViewModel(pagingSource = get()) }
}