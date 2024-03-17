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

import java.util.ArrayList;

public class Map {
    TiledMap map;
    OrthographicCamera camera;
    OrthogonalTiledMapRenderer mapRenderer;
    Rectangle worldBorder;
    Rectangle cameraBorder;
    ArrayList<RectangleMapObject> buildingObjects = new ArrayList<>();

    int width, height;

    public Map(String path, int width, int height) {
        this.width = width;
        this.height = height;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        map = new TmxMapLoader().load(path);
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        processCollisionLayer();
        processCameraLayer();
        processBuildingLayer();
    }

    private void processBuildingLayer() {
        MapLayer buildingLayer = map.getLayers().get("buildingLayer");

        if (buildingLayer == null) {
            return;
        }

        for (MapObject obj : buildingLayer.getObjects()) {
            // might be useless but keep just in case
            if(obj instanceof RectangleMapObject){
                buildingObjects.add((RectangleMapObject) obj);
                //System.out.println(((RectangleMapObject)obj).getProperties().get("type"));
            }
        }
    }

    //this function and one below can be made into one function
    private void processCollisionLayer() {
        MapLayer collisionLayer = map.getLayers().get("collisionLayer");

        if (collisionLayer == null) {
            return;
        }

        for (MapObject obj : collisionLayer.getObjects()) {
            //System.out.println(obj.getOpacity());
            if (obj.getName().equals("worldBorder")) {
                if (obj instanceof RectangleMapObject) {

                    // use classes in object properties to determine whether collision object is world border or not

                    worldBorder = ((RectangleMapObject) obj).getRectangle();
                    //RectangleMapObject test = ((RectangleMapObject) obj);
                    //System.out.println("test");
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<RectangleMapObject> getBuildingObjects() { return buildingObjects; }

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
