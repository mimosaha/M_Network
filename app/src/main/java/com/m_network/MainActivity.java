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
    }

    private AndroidMeshManager androidMeshManager;

    private void configureMesh() {
        androidMeshManager = AndroidMeshManager
                .getInstance(this, this, null, "M_Net_1");
    }

    @Override
    public void meshStateChanged(MeshId meshId, int state) {
        if (state == MeshStateListener.SUCCESS) {
            try {
                androidMeshManager.bind(14674);
                Log.v("MIMO_SAHA: ", "PeerId: " + meshId);

            } catch (RightMeshException e) {
                e.printStackTrace();
            }
        }
    }
}
