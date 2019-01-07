package com.m_network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.left.rightmesh.android.AndroidMeshManager;
import io.left.rightmesh.id.MeshId;
import io.left.rightmesh.mesh.MeshManager;
import io.left.rightmesh.mesh.MeshStateListener;
import io.left.rightmesh.util.RightMeshException;

import static io.left.rightmesh.mesh.MeshManager.PEER_CHANGED;
import static io.left.rightmesh.mesh.MeshManager.REMOVED;

public class MainActivity extends AppCompatActivity implements MeshStateListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureMesh();
        // Add a comment
    }

    private AndroidMeshManager androidMeshManager;

    private void configureMesh() {
        try {
            androidMeshManager = AndroidMeshManager
                    .getInstance(this, this, null, "M_Net_1");
            androidMeshManager.resume();
        } catch (RightMeshException.RightMeshServiceDisconnectedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void meshStateChanged(MeshId meshId, int state) {
        if (state == MeshStateListener.SUCCESS) {
            try {
                androidMeshManager.bind(10623);
                Log.v("MIMO_SAHA: ", "PeerId: " + meshId);

            } catch (RightMeshException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            androidMeshManager.stop();
        } catch (RightMeshException.RightMeshServiceDisconnectedException e) {
            e.printStackTrace();
        }
    }
}
