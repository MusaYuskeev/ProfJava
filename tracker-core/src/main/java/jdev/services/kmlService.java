package jdev.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.micromata.opengis.kml.v_2_2_0.*;
import jdev.dto.PointDTO;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

@Service
public class kmlService {
//    parseKML("tracker-core/src/main/resources/track3.klm");

    private Logger log = Logger.getLogger(gpsService.class.getName());
    private BlockingDeque<String> queue = new LinkedBlockingDeque<>(100);

    public void parseKML(String pathname) throws IOException, InterruptedException {
        File file = new File(pathname);
        Kml unmarshal = Kml.unmarshal(file);
        Feature feature = unmarshal.getFeature();
        parseFeature(feature);
    }


    /**
     * test for multiFolder kml
     */
    private void parseFeature(Feature feature) throws JsonProcessingException, InterruptedException {
        if (feature != null) {
            if (feature instanceof Document) {

                Document document = (Document) feature;
                List<Feature> featureList = document.getFeature();
                for (Feature documentFeature : featureList) {
                    if (documentFeature instanceof Placemark) {
                        parsePlacemark((Placemark) documentFeature);
                    } else if (documentFeature instanceof Folder) {
                        getFeatureList(((Folder) documentFeature).getFeature());
                    }
                }
            }
        }
    }


    /**
     * recursive finding placemarks, deeper into folders
     */
    private void getFeatureList(List<Feature> features) throws JsonProcessingException, InterruptedException {
        for (Feature f : features) {
            if (f instanceof Folder) {
                getFeatureList(((Folder) f).getFeature());
            } else if (f instanceof Placemark) {
                parsePlacemark((Placemark) f);
            }
        }
    }


    private void parsePlacemark(Placemark placemark) throws JsonProcessingException, InterruptedException {
        Geometry geometry = placemark.getGeometry();
        if (geometry != null) {
            if (geometry instanceof Polygon) {
                Polygon polygon = (Polygon) geometry;
                Boundary outerBoundaryIs = polygon.getOuterBoundaryIs();
                if (outerBoundaryIs != null) {
                    LinearRing linearRing = outerBoundaryIs.getLinearRing();
                    if (linearRing != null) {
                        List<Coordinate> coordinates = linearRing.getCoordinates();
                        if (coordinates != null) {
                            for (Coordinate coordinate : coordinates) {
                                parseCoordinate(coordinate);
                            }
                        }
                    }
                }
            } else if (geometry instanceof LineString) {
                LineString lineString = (LineString) geometry;
                List<Coordinate> coordinates = lineString.getCoordinates();
                if (coordinates != null) {
                    for (Coordinate coordinate : coordinates) {
                        parseCoordinate(coordinate);
                    }
                }
            }
        }
    }

    private void parseCoordinate(Coordinate coordinate) throws JsonProcessingException, InterruptedException {
        if (coordinate != null) {

            PointDTO point = new PointDTO();
            point.setLat(coordinate.getLatitude());
            point.setLon(coordinate.getLongitude());
            point.setTime(System.currentTimeMillis());
            point.setAzimuth(coordinate.getAltitude());
            log.info("get new point from KML " + point.toJson());
            queue.put(point.toJson());

        }
    }

}