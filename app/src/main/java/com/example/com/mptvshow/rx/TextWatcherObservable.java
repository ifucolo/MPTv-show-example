package com.example.com.mptvshow.rx;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;

public class TextWatcherObservable implements ObservableOnSubscribe<CharSequence>, Disposable {

    private EditText editText;
    private TextWatcher textWatcher;

    public TextWatcherObservable(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void subscribe(final ObservableEmitter<CharSequence> emitter) throws Exception {
        emitter.setDisposable(this);

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emitter.onNext(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        editText.addTextChangedListener(textWatcher);
    }

    @Override
    public void dispose() {
        editText.removeTextChangedListener(textWatcher);
        editText = null;
        textWatcher = null;
    }

    @Override
    public boolean isDisposed() {
        return true;
    }
}
