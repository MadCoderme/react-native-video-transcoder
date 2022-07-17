package com.transcoder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.otaliastudios.transcoder.Transcoder;
import com.otaliastudios.transcoder.TranscoderOptions;
import com.otaliastudios.transcoder.TranscoderListener;

public abstract class MainActivity extends AppCompatActivity implements TranscoderListener {

    private TranscoderOptions.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mergeTracks(String outfilePath, String[] dataSources) {
        builder = Transcoder.into(outfilePath).setListener(this);

        for(int i = 0; i < dataSources.length; i++) builder.addDataSource(dataSources[i]);

        builder.transcode();
    }

    public void setRotation(int deg) {
        builder.setVideoRotation(deg);
    }

    public void setSpeed(float speed) {
        builder.setSpeed(speed);
    }

    @Override
    public void onTranscodeCompleted(int successCode) {
        if (successCode == Transcoder.SUCCESS_TRANSCODED) {
            LOG.i("Transcoding was successful!");
        } else if (successCode == Transcoder.SUCCESS_NOT_NEEDED) {
            LOG.i("Transcoding was not needed.");
        }
    }
}