package com.example.blink22.todo.data.db;

import android.support.annotation.NonNull;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.util.Log;

import com.example.blink22.todo.Util.TodoCountingIdlingResource;
import com.example.blink22.todo.data.model.Todo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FirestoreTodoDbHelper implements DbHelper {

    private static final String TODO_COLLECTION_NAME = "todos";

    private FirebaseFirestore db;

    @Inject
    public FirestoreTodoDbHelper(){
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void getAllTodos(final OnTaskComplete callback) {
        TodoCountingIdlingResource.increment();
        db.collection(TODO_COLLECTION_NAME).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<Todo> result = new ArrayList<>();
                if(task.isSuccessful()){
                    for(DocumentSnapshot document : task.getResult()){
                        result.add(document.toObject(Todo.class));
                    }
                }
                callback.onSuccess(result);
                TodoCountingIdlingResource.decrement();
            }
        });
    }

    @Override
    public void getTodo(String id, final OnTaskComplete callback) {

        DocumentReference docRef = db.collection(TODO_COLLECTION_NAME).document(id);
        TodoCountingIdlingResource.increment();
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                List<Todo> result = new ArrayList<>();
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        result.add(document.toObject(Todo.class));
                        callback.onSuccess(result);
                    }
                }else{
                    callback.onFail();
                }
                TodoCountingIdlingResource.decrement();
            }
        });
    }

    @Override
    public void updateTodo(Todo todo) {
        TodoCountingIdlingResource.increment();
        db.collection(TODO_COLLECTION_NAME).document(todo.getId()).set(todo);
        TodoCountingIdlingResource.decrement();
    }

    @Override
    public void insertTodo(Todo todo) {
        TodoCountingIdlingResource.increment();
        db.collection(TODO_COLLECTION_NAME).document(todo.getId()).set(todo);
        TodoCountingIdlingResource.decrement();
    }

    @Override
    public void deleteTodo(String todoId) {
        TodoCountingIdlingResource.increment();
        db.collection(TODO_COLLECTION_NAME).document(todoId).delete();
        TodoCountingIdlingResource.decrement();
    }

    @Override
    public List<Todo> getAllDoneTodos() {
        return null;
    }
}
