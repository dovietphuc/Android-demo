package com.example.collection;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.collection.model.Image;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.local.ReferenceSet;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireStoreHelper {
    public static void addUser(String uid, String email){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        HashMap<String, Object> user = new HashMap<>();
        user.put("email", email);

        db.collection("UserInfo")
                .document(uid)
                .set(user);
    }

    public static void addImageInfo(Image image){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docs = db.collection("ImageInfo")
                    .document();
            docs.set(image)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            // ImageInfo/jabhcjas839/
                            HashMap<String, Object> imageMap = new HashMap<>();
                            imageMap.put("image_pref", docs);

                            db.collection("UserInfo")
                                    .document(firebaseUser.getUid())
                                    .collection("ImagePref")
                                    .add(imageMap)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            uploadImage(image);
                                        }
                                    });
                        }
                    });
        }
    }

    public static LiveData<List<Image>> getAllImages(){
        MutableLiveData<List<Image>> liveData = new MutableLiveData<>(new ArrayList<>());

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("UserInfo")
                    .document(firebaseUser.getUid())
                    .collection("ImagePref")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value,
                                            @Nullable FirebaseFirestoreException error) {
                            if(value != null) {
                                for (DocumentSnapshot document : value.getDocuments()) {
                                    DocumentReference imagePref =
                                            (DocumentReference)document.get("image_pref");
                                    imagePref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if(task.isSuccessful()) {
                                                int id = task.getResult().get("id", int.class);
                                                String data = task.getResult().get("data", String.class);
                                                String name = task.getResult().get("name", String.class);
                                                Image image = new Image(id, name, data);
                                                List<Image> images = liveData.getValue();
                                                images.add(image);
                                                liveData.setValue(images);
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    });
        }
        return liveData;
    }

    public static void uploadImage(Image image){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            String folderPath = image.getData()
                    .substring(0, image.getData().lastIndexOf('/'));

            Uri file = Uri.fromFile(new File(image.getData()));

            storage.getReference(firebaseUser.getUid())
                    .child(folderPath)
                    .putFile(file)
                    .addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if(task.isSuccessful()){
                                Log.d("PhucDVb", "onComplete: success");
                            } else {
                                Log.d("PhucDVb", "onComplete: ");
                            }
                        }
                    });
        }
    }
}
