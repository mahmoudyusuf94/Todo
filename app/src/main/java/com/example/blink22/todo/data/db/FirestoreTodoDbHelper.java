package com.example.blink22.todo.data.db;

import android.support.annotation.NonNull;
import android.util.Log;

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
        db.collection(TODO_COLLECTION_NAME).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                Log.i("fuck", "from the insert method, Task completed !");
                List<Todo> result = new ArrayList<>();
                if(task.isSuccessful()){
                    for(DocumentSnapshot document : task.getResult()){
                        result.add(document.toObject(Todo.class));
                    }
                }
                Log.i("fuck", "From insert, result is => " +result.size());
                callback.onSuccess(result);
            }
        });
        Log.i("fuck", "isn't Complete :D");
    }

    @Override
    public void getTodo(String id, final OnTaskComplete callback) {

        DocumentReference docRef = db.collection(TODO_COLLECTION_NAME).document(id);

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
            }
        });
    }

    @Override
    public void updateTodo(Todo todo, final OnTaskComplete callback) {
        db.collection(TODO_COLLECTION_NAME).document(todo.getId()).set(todo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        callback.onSuccess(null);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFail();
                    }
                });
    }

    @Override
    public void insertTodo(Todo todo , final OnTaskComplete callback) {
        db.collection(TODO_COLLECTION_NAME).document(todo.getId()).set(todo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("fuck", "Db insertion succedded");
                        callback.onSuccess(null);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("fuck", ""+e);
                    }
                });
    }

    @Override
    public void deleteTodo(String todoId, final OnTaskComplete callback) {
        db.collection(TODO_COLLECTION_NAME).document(todoId).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        callback.onSuccess(null);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFail();
                    }
                });
    }

    @Override
    public List<Todo> getAllDoneTodos() {
        return null;
    }
}
