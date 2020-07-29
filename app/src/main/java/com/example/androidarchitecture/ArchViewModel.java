package com.example.androidarchitecture;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ArchViewModel extends ViewModel {
    private MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();
    private Disposable disposable;
    private final static String TAG = "ArchViewModel";
    private Boolean flag = true;

    public MutableLiveData<Integer> getMutableLiveData() {
        return mutableLiveData;
    }

    public synchronized void increase() {
        if (flag) {
            flag = false;
            Observable.interval(1000, TimeUnit.MILLISECONDS)
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                            Log.d(TAG, "onSubscribe");
                        }

                        @Override
                        public void onNext(Long aLong) {
                            Integer value = mutableLiveData.getValue();
                            if (value == null) {
                                mutableLiveData.postValue(1);
                            } else {
                                mutableLiveData.postValue(value + 1);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "onError:", e);
                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG, "onComplete");
                        }
                    });
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
        flag = true;
    }
}
