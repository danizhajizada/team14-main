package com.tecchtitans.eng1;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Polyline;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Map {
    TiledMap map;
    OrthographicCamera camera;
    OrthogonalTiledMapRenderer mapRenderer;
    Rectangle worldBorder;

    Rectangle cameraBorder;

    public Map(String path, int width, int height) {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        map = new TmxMapLoader().load(path);
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        processCollisionLayer();
        processCameraLayer();
    }

    private void processCollisionLayer() {
        MapLayer collisionLayer = map.getLayers().get("collisionLayer");

        if (collisionLayer == null) {
            return;
        }

        for (MapObject obj : collisionLayer.getObjects()) {
            //System.out.println(obj.getOpacity());
            if (obj.getName().equals("worldBorder")) {
                if (obj instanceof RectangleMapObject) {
                    worldBorder = ((RectangleMapObject) obj).getRectangle();
                }
            }
        }
    }

    private void processCameraLayer() {
        MapLayer cameraLayer = map.getLayers().get("cameraLayer");

        if (cameraLayer == null) {
            return;
        }

        for (MapObject obj : cameraLayer.getObjects()) {
            //System.out.println(obj.getOpacity());
            if (obj.getName().equals("cameraBorder")) {
                if (obj instanceof RectangleMapObject) {
                    cameraBorder = ((RectangleMapObject) obj).getRectangle();
                }
            }
        }
    }

    public Rectangle getCameraBorder() {
        return cameraBorder;
    }

    public Rectangle getWorldBorder() {
        return worldBorder;
    }

    public TiledMap getTiledMap() {
        return map;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void render() {
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();
    }
}
