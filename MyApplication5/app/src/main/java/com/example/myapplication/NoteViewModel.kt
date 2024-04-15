package com.mobiai.app.viewmodel

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobiai.app.db.Note
import com.mobiai.app.db.NoteDataSource
import com.mobiai.app.db.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel() : ViewModel() {
    private val repository = NoteRepository()
    private val _listNote = MediatorLiveData<List<Note>>()
    val listNote: LiveData<List<Note>>
        get() = _listNote


    fun getListNote() {
        viewModelScope.launch(Dispatchers.Main) {
            _listNote.addSource(repository.getListNote()) {
                _listNote.value = it
            }
        }
    }

    fun addNote(note: Note) {
        repository.addNote(note)
    }
}