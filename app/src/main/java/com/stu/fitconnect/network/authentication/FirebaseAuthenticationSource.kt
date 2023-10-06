package com.stu.fitconnect.network.authentication

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseAuthWebException
import com.stu.fitconnect.AccountAlreadyExistsException
import com.stu.fitconnect.AuthException
import com.stu.fitconnect.AuthWeakPasswordException
import com.stu.fitconnect.BackendException
import com.stu.fitconnect.ConnectionException
import com.stu.fitconnect.InvalidUserException
import com.stu.fitconnect.TooManyRequestsException
import com.stu.fitconnect.UnknownException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FirebaseAuthenticationSource @Inject constructor(
    private val auth: FirebaseAuth
): AuthenticationSource {

    override suspend fun signIn(email: String, password: String) {
        saveFirebaseExecute {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
            } catch (exception: FirebaseTooManyRequestsException) {
                throw TooManyRequestsException(exception.message ?: "")
            } catch (exception: FirebaseAuthInvalidCredentialsException) {
                throw InvalidUserException(exception.message ?: "")
            }
        }
    }

    override suspend fun signUp(email: String, password: String) {
        saveFirebaseExecute {
            try {
                auth.signInWithEmailAndPassword(email, password).await()
            } catch (exception: FirebaseAuthWeakPasswordException) {
                throw AuthWeakPasswordException(exception.message ?: "")
            } catch (exception: FirebaseAuthEmailException) {
                throw AccountAlreadyExistsException(exception.message ?: "")
            }
        }
    }

    override fun logOut() {
        auth.signOut()
    }

    override fun isSignedId(): Boolean {
        return auth.currentUser != null
    }

    override fun getCurrentUId(): String? {
        return auth.currentUser?.uid
    }

    override suspend fun deleteCurrentUser() {
        auth.currentUser?.delete()?.await()
    }

    private suspend fun <T> saveFirebaseExecute(block: suspend () -> T) : T {
        return try {
            block()
        } catch (exception: FirebaseNetworkException) {
            throw ConnectionException(exception.message ?: "")
        } catch (exception: FirebaseAuthWebException) {
            throw BackendException(exception.message ?: "")
        } catch (exception: FirebaseAuthException) {
            throw AuthException(exception.message ?: "")
        } catch (exception: Exception) {
            throw UnknownException(exception.message ?: "")
        }
    }
}