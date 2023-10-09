package com.stu.fitconnect.network.authentication

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseAuthWebException
import com.stu.fitconnect.utils.AccountAlreadyExistsException
import com.stu.fitconnect.utils.AuthException
import com.stu.fitconnect.utils.AuthWeakPasswordException
import com.stu.fitconnect.utils.BackendException
import com.stu.fitconnect.utils.ConnectionException
import com.stu.fitconnect.utils.InvalidUserException
import com.stu.fitconnect.utils.TooManyRequestsException
import com.stu.fitconnect.utils.UnknownException
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

    override fun getCurrentUId(): String {
        return auth.currentUser?.uid ?: throw AuthException(UID_EXCEPTION_MES)
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

    companion object {
        const val UID_EXCEPTION_MES = "Auth error. Cant get current User Id"
    }
}