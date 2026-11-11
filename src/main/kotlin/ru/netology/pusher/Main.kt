package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream

fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .setDatabaseUrl(DB_URL)
        .build()

    FirebaseApp.initializeApp(options)

    val postMessage = Message.builder()
        .putData("action", "POST")
        .putData("content", """
            {
                "userId": 1,
                "userName": "Alexander",
                "postId": 2,
                "postContent": "Это текст нового поста, который может занимать несколько строк. Он будет показан в раскрывающемся уведомлении."
            }
        """.trimIndent())
        .setToken(TOKEN)
        .build()

    val likeMessage = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """
            {
                "userId": 1,
                "userName": "Alexander",
                "postId": 2,
                "postAuthor": "Netology."
            }
        """.trimIndent())
        .setToken(TOKEN)
        .build()

    val postResponse = FirebaseMessaging.getInstance().send(postMessage)
    println("Successfully sent message: $postResponse")

    val likeResponse = FirebaseMessaging.getInstance().send(likeMessage)
    println("Successfully sent message: $likeResponse")
}