package com.example.blink22.todo.di.module;

import android.content.Context;

import com.example.blink22.todo.data.DataManager;
import com.example.blink22.todo.data.TodoDataManager;
import com.example.blink22.todo.data.db.DbHelper;
import com.example.blink22.todo.data.db.FirestoreTodoDbHelper;
//import com.example.blink22.todo.data.db.TodoDbHelper;
import com.example.blink22.todo.di.ApplicationContext;
import com.example.blink22.todo.di.FirestoreDbHelper;
import com.example.blink22.todo.di.RealmDbHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module(includes = ContextModule.class)
public class ApplicationModule {

//    @Provides
//    @Singleton
//    public DataManager dataManager(@RealmDbHelper DbHelper dbHelper){
//        return new TodoDataManager(dbHelper);
//    }
//
    @Provides
    @Singleton
    public DataManager dataManager(@FirestoreDbHelper DbHelper dbHelper){
        return new TodoDataManager(dbHelper);
    }

//    @Provides
//    @Singleton
//    @RealmDbHelper
//    public DbHelper getRealmDbHelper(@ApplicationContext Context context){
//        return new TodoDbHelper(context);
//    }

    @Provides
    @Singleton
    @FirestoreDbHelper
    public DbHelper getFirestoreDbHelper(){
        return new FirestoreTodoDbHelper();
    }
}
