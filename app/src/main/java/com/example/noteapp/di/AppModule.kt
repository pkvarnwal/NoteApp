package com.example.noteapp.di

import android.content.Context
import androidx.room.Room
import com.example.noteapp.data.repository.NoteRepositoryImpl
import com.example.noteapp.data.local.db.NoteDao
import com.example.noteapp.data.local.db.NoteDatabase
import com.example.noteapp.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
       return Room.databaseBuilder(
           context,
           NoteDatabase::class.java,
           "notes.db"
       )
           .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase) : NoteDao {
        return noteDatabase.notesDao()
    }

    @Provides
    fun provideNoteRepository(db: NoteDatabase) : NoteRepository {
        return NoteRepositoryImpl(db)
    }
}