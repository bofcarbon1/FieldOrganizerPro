package com.example.fieldorganizerprov2.services

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy // Useful extension function

class RxJavaService {

//var selectedContact = Observable<Contact>.create(ObservableOnSubscribe<Contact>)

// ... inside an Activity or Fragment

    private val disposables = CompositeDisposable()

    fun subscribeToData(observable: Observable<String>) {
        val disposable = observable
            .observeOn(AndroidSchedulers.mainThread()) // Receive data on the main UI thread
            .subscribeBy(
                onNext = { data ->
                    // Handle the data emission (e.g., update UI text)
                    println("Received: $data")
                },
                onError = { throwable ->
                    // Handle the error (e.g., show an error message)
                    throwable.printStackTrace()
                },
                onComplete = {
                    // Handle the completion event
                    println("Stream completed")
                }
            )

        // Add the disposable to a CompositeDisposable for lifecycle management
        disposables.add(disposable)
    }

    // Remember to dispose of subscriptions in onDestroy() or onStop() to prevent memory leaks
    //override fun onDestroy() {
    //    super.onDestroy()
    //    disposables.dispose()
    //}
}
