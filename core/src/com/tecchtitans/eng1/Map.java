package com.tecchtitans.eng1;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

/**
 * Class that is used for storing information about a map that can be displayed
 * by a screen. Includes a TiledMap, a world border, a camera border, building
 * objects placed on the map, an orthographic camera and a tiled map renderer.
 */
public class Map {
    TiledMap map;
    OrthographicCamera camera;
    OrthogonalTiledMapRenderer mapRenderer;
    Rectangle worldBorder;
    Rectangle cameraBorder;
    ArrayList<RectangleMapObject> buildingObjects;

    int width, height;

    /**
     * Instantiates a Map with a desired Tiled map internal path input as a string.
     * Initialises a camera with the desired width and height. Initialises a map
     * renderer for the desired map. Initialises an array to stored building objects
     * from the map in. The collision layer, camera layer, and building layers
     * from the map are processed to set the world border, camera border, and
     * building objects respectively.
     * @param path - String of an internal file path to a map in a .tmx format.
     * @param width - Width of the map in pixels, represented as an Integer.
     * @param height - Height of the map in pixels, represented as an Integer.
     */
    public Map(String path, int width, int height) {
        this.width = width;
        this.height = height;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);

        map = new TmxMapLoader().load(path);
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        buildingObjects = new ArrayList<RectangleMapObject>();

        processCollisionLayer();
        processCameraLayer();
        processBuildingLayer();
    }

    /**
     * Checks for object instances of buildings in the buildingLayer from
     * the current TiledMap, then adds them to an internal field.
     */
    private void processBuildingLayer() {
        MapLayer buildingLayer = map.getLayers().get("buildingLayer");

        if (buildingLayer == null) {
            return;
        }

        for (MapObject obj : buildingLayer.getObjects()) {
            if(obj instanceof RectangleMapObject){
                buildingObjects.add((RectangleMapObject) obj);
                //System.out.println(((RectangleMapObject)obj).getProperties().get("type"));
            }
        }
    }

    /**
     * Checks the collision layer for an instance of a worldBorder.
     * If found, the world border is set to the rectangle defined
     * in this object.
     */
    private void processCollisionLayer() {
        RectangleMapObject worldBorderObject = getRectangleObjectFromLayer("collisionLayer", "worldBorder");
        if(worldBorderObject != null) { worldBorder = worldBorderObject.getRectangle(); }
    }

    /**
     * Checks the camera layer for an instance of a cameraBorder.
     * If found, the camera border is set to the rectangle defined
     * in this object.
     */
    private void processCameraLayer() {
        RectangleMapObject cameraBorderObject = getRectangleObjectFromLayer("cameraLayer", "cameraBorder");
        if(cameraBorderObject != null) { cameraBorder = cameraBorderObject.getRectangle(); }
    }

    /**
     * Checks a specified map layer for an instance of a desired rectangle object.
     * If found, the object is returned. Else, null is returned.
     * @param layerName - Name of desired layer to search from the map, given as a String.
     * @param objectName - Name of the desired rectangle object, given as a String.
     * @return the desired object as a RectangleMapObject if found, else return null.
     */
    private RectangleMapObject getRectangleObjectFromLayer(String layerName, String objectName) {
        MapLayer mapLayer = map.getLayers().get(layerName);

        if (mapLayer == null) {
            return null;
        }

        for (MapObject obj : mapLayer.getObjects()) {
            //System.out.println(obj.getOpacity());
            if (obj.getName().equals(objectName)) {
                if (obj instanceof RectangleMapObject) {
                    return (RectangleMapObject) obj;
                }
            }
        }

        return null;
    }

    /**
     * Returns the map's width.
     * @return number of pixels in width of map, given as an int.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the map's height.
     * @return number of pixels in height of map, given as an int.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the building objects found in the map.
     * @return building objects as an array of RectangleMapObjects.
     //* @see RectangleMapObject
     */
    public ArrayList<RectangleMapObject> getBuildingObjects() { return buildingObjects; }

    /**
     * Returns the camera's display border.
     * @return the camera's border as a Rectangle.
     //* @see Rectangle
     */
    public Rectangle getCameraBorder() {
        return cameraBorder;
    }

    /**
     * Returns the world border.
     * @return the world border as a Rectangle.
     //* @see Rectangle
     */
    public Rectangle getWorldBorder() {
        return worldBorder;
    }

    /**
     * Returns the respective tiled map for this instance.
     * @return the map as a TiledMap.
     //* @see TiledMap
     */
    public TiledMap getTiledMap() {
        return map;
    }

    /**
     * Returns the orthographic camera being used by the map.
     * @return orthographic camera as an OrthographicCamera.
     //* @see OrthographicCamera
     */
    public OrthographicCamera getCamera() {
        return camera;
    }

    /**
     * Renders the current map by calling the camera's update method,
     * setting the map renderer's view camera to this updated camera,
     * and then calling the map renderer's update method.
     //* @see OrthographicCamera
     //* @see OrthogonalTiledMapRenderer
     */
    public void render() {
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();
    }
}
